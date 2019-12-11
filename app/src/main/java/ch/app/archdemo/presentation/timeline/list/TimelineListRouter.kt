package ch.app.archdemo.presentation.timeline.list

import ch.app.archdemo.app.timeline.list.ITimelineListInteractor
import ch.app.archdemo.app.timeline.list.TimelineListAction
import ch.app.archdemo.arch.router.AbstractRouter

class TimelineListRouter : AbstractRouter<ITimelineListInteractor, TimelineListAction>() {

    override fun route(action: TimelineListAction, interactor: ITimelineListInteractor) {
        when (action) {
            is TimelineListAction.OnItemClick -> interactor.onItemClick(action.timelineModel)
        }
    }
}