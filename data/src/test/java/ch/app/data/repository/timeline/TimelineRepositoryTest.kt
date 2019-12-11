package ch.app.data.repository.timeline

import ch.app.domain.timeline.TimelineModel
import io.mockk.MockKAnnotations
import io.mockk.called
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class TimelineRepositoryTest {

    @MockK
    private lateinit var timelineApi: TimelineApi
    @MockK
    private lateinit var timelineDao: TimelineDao

    private val listEntity = listOf(
        TimelineEntity(
            "mock id",
            "mock name",
            "mock status",
            10,
            32,
            9090,
            "mock photo"
        )
    )

    private val listModel = listOf(
        TimelineModel(
            "mock id",
            "mock name",
            "mock status",
            10,
            32,
            9090,
            "mock photo",
            "category"
        )
    )

    private lateinit var timelineRepository: TimelineRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        every { timelineDao.deleteAll(any()) } returns Unit
        every { timelineDao.insertAll(any()) } returns Unit

        timelineRepository = TimelineRepository(timelineApi, timelineDao)
    }

    @Test
    fun `success when calling getTimelineList() with non empty api response`() {
        every { timelineApi.getTimelineList(any()) } returns Single.just(listEntity)

        timelineRepository.getTimelineList("url", "category")
            .test()
            .assertSubscribed()
            .assertComplete()
            .assertValue { it == listModel }

        verify { timelineApi.getTimelineList(eq("url")) }
        verify { timelineDao.deleteAll(eq("category")) }
        verify { timelineDao.insertAll(eq(listEntity)) }
        verify { timelineDao.getTimelineList(any()) wasNot called }
    }

    @Test
    fun `success call timelineDao getTimelineList() when calling getTimelineList() with api error`() {
        every { timelineApi.getTimelineList(any()) } returns Single.error(Throwable())
        every { timelineDao.getTimelineList(any()) } returns Single.just(listEntity)

        timelineRepository.getTimelineList("url", "category")
            .test()
            .assertSubscribed()
            .assertComplete()
            .assertValue { it == listModel }

        verify { timelineApi.getTimelineList(eq("url")) }
        verify { timelineDao.getTimelineList(eq("category")) }
        verify { timelineDao.deleteAll(eq("category")) }
        verify { timelineDao.insertAll(eq(listEntity)) }
    }

    @Test
    fun `error when api response error and dao is empty`() {
        every { timelineApi.getTimelineList(any()) } returns Single.error(Throwable())
        every { timelineDao.getTimelineList(any()) } returns Single.just(emptyList())

        timelineRepository.getTimelineList("url", "category")
            .test()
            .assertSubscribed()
            .assertError { it.message == "Timeline table is empty" }

        verify { timelineApi.getTimelineList(eq("url")) }
        verify { timelineDao.getTimelineList(eq("category")) }
        verify { timelineDao.deleteAll(eq("category")) }
        verify { timelineDao.insertAll(eq(emptyList())) }
    }
}