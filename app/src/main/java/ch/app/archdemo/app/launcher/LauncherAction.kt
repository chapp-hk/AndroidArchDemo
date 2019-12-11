package ch.app.archdemo.app.launcher

import ch.app.domain.master.MasterModel

sealed class LauncherAction {
    data class ToTimeLine(val masters: List<MasterModel>) : LauncherAction()
}