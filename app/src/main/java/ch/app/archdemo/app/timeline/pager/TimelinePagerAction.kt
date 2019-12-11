package ch.app.archdemo.app.timeline.pager

sealed class TimelinePagerAction {
    object CreateItem : TimelinePagerAction()
}