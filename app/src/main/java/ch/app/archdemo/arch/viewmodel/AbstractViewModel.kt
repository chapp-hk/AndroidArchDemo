package ch.app.archdemo.arch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class AbstractViewModel<A: Any> : ViewModel() {

    protected val _action = MutableLiveData<A>()
    val action: LiveData<A> = _action

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}