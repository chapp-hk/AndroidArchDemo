package ch.app.archdemo.presentation.timeline.list

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ch.app.archdemo.arch.recyclerview.IViewHolder
import ch.app.domain.master.MasterModel
import ch.app.domain.timeline.datasource.TimelineDataSourceFactory
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TimelineListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var timelineDataSourceFactory: TimelineDataSourceFactory
    @MockK
    private lateinit var dataSourceFactory: DataSource.Factory<Unit, IViewHolder>
    @MockK
    private lateinit var bundle: Bundle

    private var mainScheduler = TestScheduler()
    private var ioScheduler = TestScheduler()

    private lateinit var timelineListViewModel: TimelineListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        timelineListViewModel = spyk(
            TimelineListViewModel(
                mainScheduler,
                ioScheduler,
                timelineDataSourceFactory
            ),
            recordPrivateCalls = true
        )
    }

    @Test
    fun initWithArgs() {
        val masterModel = MasterModel("mock category", "mock url")
        every { bundle.getParcelable<MasterModel>(any()) } returns masterModel
        every { timelineDataSourceFactory.setParam(any()) } returns timelineDataSourceFactory
        every { timelineDataSourceFactory.setCompositeDisposable(any()) } returns timelineDataSourceFactory
        every { timelineDataSourceFactory.map<IViewHolder>(any()) } returns dataSourceFactory

        timelineListViewModel.initWithArgs(bundle)

        verify { timelineListViewModel["getTimelineList"]() }
        verify { timelineDataSourceFactory.setParam(eq(masterModel)) }
        verify { timelineDataSourceFactory.setCompositeDisposable(any()) }
    }

    @Test
    fun getIsRefreshing() {
        every { timelineDataSourceFactory.isRefreshing } returns MutableLiveData()

        timelineListViewModel.getIsRefreshing()

        verify { timelineDataSourceFactory.isRefreshing }
    }

    @Test
    fun getIsInitialLoading() {
        every { timelineDataSourceFactory.isInitialLoading } returns MutableLiveData()

        timelineListViewModel.getIsInitialLoading()

        verify { timelineDataSourceFactory.isInitialLoading }
    }

    @Test
    fun getIsError() {
        every { timelineDataSourceFactory.isInitialError } returns MutableLiveData()

        timelineListViewModel.getIsError()

        verify { timelineDataSourceFactory.isInitialError }
    }
}