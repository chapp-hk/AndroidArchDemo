package ch.app.domain.timeline.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import ch.app.domain.master.MasterModel
import ch.app.domain.timeline.ITimelineRepository
import ch.app.domain.timeline.TimelineModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test

class TimelineDataSourceTest {

    @MockK
    private lateinit var isRefreshing: MutableLiveData<Boolean>
    @MockK
    private lateinit var isInitialLoading: MutableLiveData<Boolean>
    @MockK
    private lateinit var isInitialError: MutableLiveData<Boolean>
    @MockK
    private lateinit var timelineRepository: ITimelineRepository
    @MockK
    private lateinit var masterModel: MasterModel
    @MockK
    private lateinit var timelineModel: TimelineModel
    @MockK
    private lateinit var compositeDisposable: CompositeDisposable
    @MockK
    private lateinit var loadInitialParams: ItemKeyedDataSource.LoadInitialParams<Unit>
    @MockK
    private lateinit var loadInitialCallback: ItemKeyedDataSource.LoadInitialCallback<TimelineModel>
    @MockK
    private lateinit var loadParams: ItemKeyedDataSource.LoadParams<Unit>
    @MockK
    private lateinit var loadCallback: ItemKeyedDataSource.LoadCallback<TimelineModel>

    private lateinit var timelineDataSource: TimelineDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        every { isRefreshing.postValue(any()) } returns Unit
        every { isInitialLoading.postValue(any()) } returns Unit
        every { isInitialError.postValue(any()) } returns Unit
        every { masterModel.url } returns "mock url"
        every { masterModel.category } returns "mock category"
        every { loadInitialCallback.onResult(any()) } returns Unit
        every { compositeDisposable.add(any()) } returns true

        timelineDataSource = TimelineDataSource(
            isRefreshing,
            isInitialLoading,
            isInitialError,
            timelineRepository,
            masterModel,
            compositeDisposable
        )
    }

    @Test
    fun `loadInitial success`() {
        every { timelineRepository.getTimelineList(any(), any()) } returns Single.just(listOf())

        timelineDataSource.loadInitial(loadInitialParams, loadInitialCallback)

        verify { timelineRepository.getTimelineList("mock url", "mock category") }
        verify(exactly = 2) { isRefreshing.postValue(eq(false)) }
        verifySequence {
            isInitialLoading.postValue(eq(true))
            isInitialLoading.postValue(eq(false))
        }
        verify(exactly = 2) { isInitialError.postValue(eq(false)) }
        verify { loadInitialCallback.onResult(any()) }

    }

    @Test
    fun `loadInitial error`() {
        every { timelineRepository.getTimelineList(any(), any()) } returns Single.error(Throwable())

        timelineDataSource.loadInitial(loadInitialParams, loadInitialCallback)

        verify { timelineRepository.getTimelineList("mock url", "mock category") }
        verify(exactly = 2) { isRefreshing.postValue(eq(false)) }
        verifySequence {
            isInitialLoading.postValue(eq(true))
            isInitialLoading.postValue(eq(false))
        }
        verifySequence {
            isInitialError.postValue(eq(false))
            isInitialError.postValue(eq(true))
        }
    }

    @Test
    fun `loadAfter without action`() {
        timelineDataSource.loadAfter(loadParams, loadCallback)

        verify { loadParams wasNot called }
        verify { loadCallback wasNot called }
    }

    @Test
    fun `loadBefore without action`() {
        timelineDataSource.loadBefore(loadParams, loadCallback)

        verify { loadParams wasNot called }
        verify { loadCallback wasNot called }
    }

    @Test
    fun `getKey without action`() {
        timelineDataSource.getKey(timelineModel)

        verify { timelineModel wasNot called }
    }

    @Test
    fun invalidate() {
        timelineDataSource.invalidate()

        verify { isRefreshing.postValue(eq(true)) }
    }
}