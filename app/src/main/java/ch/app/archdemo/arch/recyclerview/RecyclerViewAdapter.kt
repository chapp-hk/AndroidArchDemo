package ch.app.archdemo.arch.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagedListAdapter
import ch.app.archdemo.BR

class RecyclerViewAdapter(private val context: Context) :
    PagedListAdapter<IViewHolder, RecyclerViewHolder>(DiffCallback()) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.getLayoutId() ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(getBinding(viewType, parent))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.binding.apply {
            lifecycleOwner = context as? LifecycleOwner
            setVariable(BR.viewHolder, getItem(position))
            executePendingBindings()
        }
    }

    private fun getBinding(@LayoutRes layoutRes: Int, parent: ViewGroup): ViewDataBinding {
        return DataBindingUtil.inflate(layoutInflater, layoutRes, parent, false)
    }
}