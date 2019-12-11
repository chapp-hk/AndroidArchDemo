package ch.app.data.repository.master

import ch.app.domain.master.MasterModel
import io.mockk.MockKAnnotations
import io.mockk.called
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class MasterRepositoryTest {

    @MockK
    private lateinit var masterApi: MasterApi
    @MockK
    private lateinit var masterDao: MasterDao

    private val listEntity = listOf(MasterEntity("mock name", "mock data"))
    private val listModel = listOf(MasterModel("mock name", "mock data"))

    private lateinit var masterRepository: MasterRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        every { masterDao.deleteAll() } returns Unit
        every { masterDao.insertAll(any()) } returns Unit

        masterRepository = MasterRepository(masterApi, masterDao)
    }

    @Test
    fun `success when calling getMasterList() with non empty api response`() {
        every { masterApi.getMasterList() } returns Single.just(listEntity)

        masterRepository.getMasterList()
            .test()
            .assertSubscribed()
            .assertComplete()
            .assertValue { it == listModel }

        verify { masterApi.getMasterList() }
        verify { masterDao.deleteAll() }
        verify { masterDao.insertAll(eq(listEntity)) }
        verify { masterDao.getMasterList() wasNot called }
    }

    @Test
    fun `success call masterDao getMasterList() when calling getMasterList() with api error`() {
        every { masterApi.getMasterList() } returns Single.error(Throwable())
        every { masterDao.getMasterList() } returns Single.just(listEntity)

        masterRepository.getMasterList()
            .test()
            .assertSubscribed()
            .assertComplete()
            .assertValue { it == listModel }

        verify { masterApi.getMasterList() }
        verify { masterDao.getMasterList() }
        verify { masterDao.deleteAll() }
        verify { masterDao.insertAll(eq(listEntity)) }
    }

    @Test
    fun `error when api response error and dao is empty`() {
        every { masterApi.getMasterList() } returns Single.error(Throwable())
        every { masterDao.getMasterList() } returns Single.just(emptyList())

        masterRepository.getMasterList()
            .test()
            .assertSubscribed()
            .assertError { it.message == "Master table is empty" }

        verify { masterApi.getMasterList() }
        verify { masterDao.getMasterList() }
        verify { masterDao.deleteAll() }
        verify { masterDao.insertAll(eq(emptyList())) }
    }
}