package ch.app.archdemo.presentation.launcher

import ch.app.archdemo.app.launcher.ILauncherInteractor
import ch.app.archdemo.app.launcher.LauncherAction
import ch.app.archdemo.arch.router.AbstractRouter

class LauncherRouter : AbstractRouter<ILauncherInteractor, LauncherAction>() {

    override fun route(action: LauncherAction, interactor: ILauncherInteractor) {
        when (action) {
            is LauncherAction.ToTimeLine -> interactor.toTimeline(action.masters)
        }
    }
}