package ch.app.archdemo.arch.recyclerview

import androidx.recyclerview.widget.DiffUtil
import java.util.*

class DiffCallback : DiffUtil.ItemCallback<IViewHolder>() {

    override fun areItemsTheSame(oldItem: IViewHolder, newItem: IViewHolder): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: IViewHolder, newItem: IViewHolder): Boolean {
        return Objects.equals(oldItem, newItem)
    }
}