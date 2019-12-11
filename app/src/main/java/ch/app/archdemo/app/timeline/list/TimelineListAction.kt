package ch.app.archdemo.app.timeline.list

import ch.app.domain.timeline.TimelineModel

sealed class TimelineListAction {
    data class OnItemClick(val timelineModel: TimelineModel) : TimelineListAction()
}