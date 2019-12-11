package ch.app.domain.timeline.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import ch.app.domain.master.MasterModel
import ch.app.domain.timeline.ITimelineRepository
import ch.app.domain.timeline.TimelineModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class TimelineDataSource(
    private val isRefreshing: MutableLiveData<Boolean>,
    private val isInitialLoading: MutableLiveData<Boolean>,
    private val isInitialError: MutableLiveData<Boolean>,
    private val timelineRepository: ITimelineRepository,
    private val masterModel: MasterModel,
    private val compositeDisposable: CompositeDisposable
) : ItemKeyedDataSource<Unit, TimelineModel>() {

    override fun loadInitial(params: LoadInitialParams<Unit>, callback: LoadInitialCallback<TimelineModel>) {
        timelineRepository.getTimelineList(masterModel.url, masterModel.category)
            .doOnSubscribe { resetInitialStates() }
            .doOnSuccess { onLoadInitialSuccess() }
            .subscribe(callback::onResult, this::onLoadInitialError)
            .addTo(compositeDisposable)
    }

    override fun loadAfter(params: LoadParams<Unit>, callback: LoadCallback<TimelineModel>) {

    }

    override fun loadBefore(params: LoadParams<Unit>, callback: LoadCallback<TimelineModel>) {

    }

    override fun getKey(item: TimelineModel) {

    }

    override fun invalidate() {
        super.invalidate()
        isRefreshing.postValue(true)
    }

    private fun resetInitialStates() {
        isRefreshing.postValue(false)
        isInitialLoading.postValue(true)
        isInitialError.postValue(false)
    }

    private fun onLoadInitialSuccess() {
        isRefreshing.postValue(false)
        isInitialLoading.postValue(false)
        isInitialError.postValue(false)
    }

    private fun onLoadInitialError(throwable: Throwable) {
        Timber.e(throwable)
        isRefreshing.postValue(false)
        isInitialLoading.postValue(false)
        isInitialError.postValue(true)
    }
}