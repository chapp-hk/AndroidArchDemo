package ch.app.domain.timeline.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import ch.app.domain.master.MasterModel
import ch.app.domain.timeline.ITimelineRepository
import ch.app.domain.timeline.TimelineModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TimelineDataSourceFactory @Inject
constructor(private val timelineRepository: ITimelineRepository) : DataSource.Factory<Unit, TimelineModel>() {

    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing
    private val _isRefreshing = MutableLiveData<Boolean>()

    val isInitialLoading: LiveData<Boolean>
        get() = _isInitialLoading
    private val _isInitialLoading = MutableLiveData<Boolean>()

    val isInitialError: LiveData<Boolean>
        get() = _isInitialError
    private val _isInitialError = MutableLiveData<Boolean>()

    private lateinit var masterModel: MasterModel
    private lateinit var compositeDisposable: CompositeDisposable

    override fun create(): DataSource<Unit, TimelineModel> {
        return TimelineDataSource(
            _isRefreshing,
            _isInitialLoading,
            _isInitialError,
            timelineRepository,
            masterModel,
            compositeDisposable
        )
    }

    fun setParam(masterModel: MasterModel): TimelineDataSourceFactory {
        this.masterModel = masterModel
        return this
    }

    fun setCompositeDisposable(compositeDisposable: CompositeDisposable): TimelineDataSourceFactory {
        this.compositeDisposable = compositeDisposable
        return this
    }
}