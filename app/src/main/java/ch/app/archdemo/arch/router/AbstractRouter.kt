package ch.app.archdemo.arch.router

import androidx.lifecycle.LiveData

abstract class AbstractRouter<I : IInteractor, T> {

    fun observe(interactor: I, action: LiveData<out T>) {
        action.observe({ interactor.lifecycle }, { route(it, interactor) })
    }

    protected abstract fun route(action: T, interactor: I)
}