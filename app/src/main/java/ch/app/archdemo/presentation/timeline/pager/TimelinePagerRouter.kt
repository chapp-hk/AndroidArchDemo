package ch.app.archdemo.presentation.timeline.pager

import ch.app.archdemo.app.timeline.pager.ITimelinePagerInteractor
import ch.app.archdemo.app.timeline.pager.TimelinePagerAction
import ch.app.archdemo.arch.router.AbstractRouter

class TimelinePagerRouter : AbstractRouter<ITimelinePagerInteractor, TimelinePagerAction>() {

    override fun route(action: TimelinePagerAction, interactor: ITimelinePagerInteractor) {
        when (action) {
            is TimelinePagerAction.CreateItem -> interactor.toCreateNewItem()
        }
    }
}