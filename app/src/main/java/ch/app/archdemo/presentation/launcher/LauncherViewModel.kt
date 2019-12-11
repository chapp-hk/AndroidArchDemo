package ch.app.archdemo.presentation.launcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ch.app.archdemo.app.launcher.LauncherAction
import ch.app.archdemo.arch.viewmodel.AbstractViewModel
import ch.app.domain.master.MasterModel
import ch.app.domain.master.MasterUsecase
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class LauncherViewModel @Inject
constructor(private val masterUseCase: MasterUsecase) : AbstractViewModel<LauncherAction>() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    fun getMasterList() {
        masterUseCase.getMasterList()
            .doOnSubscribe { resetStates() }
            .subscribe(this::onGetMasterListSuccess, this::onGetMasterListError)
            .addTo(compositeDisposable)
    }

    private fun resetStates() {
        _isLoading.postValue(true)
        _isError.postValue(false)
    }

    private fun onGetMasterListSuccess(list: List<MasterModel>) {
        _action.postValue(LauncherAction.ToTimeLine(list))
    }

    private fun onGetMasterListError(throwable: Throwable) {
        Timber.e(throwable)
        _isLoading.postValue(false)
        _isError.postValue(true)
    }
}