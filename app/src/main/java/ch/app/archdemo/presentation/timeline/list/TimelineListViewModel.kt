package ch.app.archdemo.presentation.timeline.list

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import ch.app.archdemo.app.timeline.list.TimelineListAction
import ch.app.archdemo.arch.Contants.KEY_DATA
import ch.app.archdemo.arch.recyclerview.IViewHolder
import ch.app.archdemo.arch.viewmodel.AbstractViewModel
import ch.app.domain.master.MasterModel
import ch.app.domain.timeline.TimelineModel
import ch.app.domain.timeline.datasource.TimelineDataSourceFactory
import io.reactivex.Scheduler
import io.reactivex.annotations.SchedulerSupport
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject
import javax.inject.Named

class TimelineListViewModel @Inject
constructor(
    @Named(SchedulerSupport.CUSTOM) private val mainScheduler: Scheduler,
    @Named(SchedulerSupport.IO) private val ioScheduler: Scheduler,
    private val timelineDataSourceFactory: TimelineDataSourceFactory
) : AbstractViewModel<TimelineListAction>() {

    private val _list = MutableLiveData<PagedList<IViewHolder>>()
    val list: LiveData<PagedList<IViewHolder>> = _list

    fun initWithArgs(args: Bundle?) {
        args?.getParcelable<MasterModel>(KEY_DATA)?.let {
            timelineDataSourceFactory.setParam(it).setCompositeDisposable(compositeDisposable)
            getTimelineList()
        }
    }

    fun getIsRefreshing(): LiveData<Boolean> {
        return timelineDataSourceFactory.isRefreshing
    }

    fun getIsInitialLoading(): LiveData<Boolean> {
        return timelineDataSourceFactory.isInitialLoading
    }

    fun getIsError(): LiveData<Boolean> {
        return timelineDataSourceFactory.isInitialError
    }

    fun refresh() {
        _list.value?.dataSource?.invalidate()
    }

    private fun getTimelineList() {
        if (null == _list.value) {
            RxPagedListBuilder(timelineDataSourceFactory.map(this::mapToViewHolder), 1)
                .setFetchScheduler(ioScheduler)
                .setNotifyScheduler(mainScheduler)
                .buildObservable()
                .subscribe(_list::postValue)
                .addTo(compositeDisposable)
        }
    }

    private fun mapToViewHolder(timelineModel: TimelineModel): IViewHolder {
        return TimelineViewHolder(
            timelineModel,
            this::onItemClick
        )
    }

    private fun onItemClick(timelineModel: TimelineModel) {
        _action.postValue(TimelineListAction.OnItemClick(timelineModel))
    }
}