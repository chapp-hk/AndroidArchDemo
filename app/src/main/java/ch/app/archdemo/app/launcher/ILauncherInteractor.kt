package ch.app.archdemo.app.launcher

import ch.app.archdemo.arch.router.IInteractor
import ch.app.domain.master.MasterModel

interface ILauncherInteractor : IInteractor {

    fun toTimeline(array: List<MasterModel>)
}