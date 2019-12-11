package ch.app.archdemo.app.timeline.list

import ch.app.archdemo.arch.router.IInteractor
import ch.app.domain.timeline.TimelineModel

interface ITimelineListInteractor : IInteractor {

    fun onItemClick(timelineModel: TimelineModel)
}