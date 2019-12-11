package ch.app.archdemo.presentation.timeline.pager

import ch.app.archdemo.app.timeline.pager.TimelinePagerAction
import ch.app.archdemo.arch.viewmodel.AbstractViewModel
import javax.inject.Inject

class TimelinePagerViewModel @Inject constructor() : AbstractViewModel<TimelinePagerAction>() {

    fun createNewItem() {
        _action.postValue(TimelinePagerAction.CreateItem)
    }
}