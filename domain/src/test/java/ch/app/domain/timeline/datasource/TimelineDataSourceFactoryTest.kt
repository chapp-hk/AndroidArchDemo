package ch.app.domain.timeline.datasource

import ch.app.domain.master.MasterModel
import ch.app.domain.timeline.ITimelineRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TimelineDataSourceFactoryTest {

    @MockK
    private lateinit var timelineRepository: ITimelineRepository

    private lateinit var timelineDataSourceFactory: TimelineDataSourceFactory

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        timelineDataSourceFactory = TimelineDataSourceFactory(timelineRepository)
    }

    @Test
    fun isRefreshing() {
        assertNotNull(timelineDataSourceFactory.isRefreshing)
    }

    @Test
    fun isInitialLoading() {
        assertNotNull(timelineDataSourceFactory.isInitialLoading)
    }

    @Test
    fun isInitialError() {
        assertNotNull(timelineDataSourceFactory.isInitialError)
    }

    @Test
    fun `create success`() {
        timelineDataSourceFactory.setParam(MasterModel("", "")).setCompositeDisposable(CompositeDisposable())

        assertTrue(timelineDataSourceFactory.create() is TimelineDataSource)
    }

    @Test(expected = UninitializedPropertyAccessException::class)
    fun `create error if param not set`() {
        timelineDataSourceFactory.setCompositeDisposable(CompositeDisposable()).create()
    }

    @Test(expected = UninitializedPropertyAccessException::class)
    fun `create error if CompositeDisposable not set`() {
        timelineDataSourceFactory.setParam(MasterModel("", "")).create()
    }
}