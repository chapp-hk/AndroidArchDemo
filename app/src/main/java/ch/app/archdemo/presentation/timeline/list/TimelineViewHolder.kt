package ch.app.archdemo.presentation.timeline.list

import ch.app.domain.timeline.TimelineModel
import ch.app.archdemo.R
import ch.app.archdemo.arch.recyclerview.IViewHolder
import ch.app.archdemo.presentation.timeline.Status.SOLD_OUT
import java.text.DecimalFormat

class TimelineViewHolder(
    val timelineModel: TimelineModel,
    val onClick: (TimelineModel) -> Unit
) : IViewHolder {

    override fun getLayoutId(): Int {
        return R.layout.timeline_list_item
    }

    fun getSoldOutBadge(): Int? {
        return when (timelineModel.status) {
            SOLD_OUT -> R.drawable.badge_soldout
            else -> null
        }
    }

    fun getNumLikes(): String {
        return timelineModel.numLikes.toString()
    }

    fun getNumComments(): String {
        return timelineModel.numComments.toString()
    }

    fun getPrice(): String {
        return DecimalFormat("#,###").format(timelineModel.price)
    }
}