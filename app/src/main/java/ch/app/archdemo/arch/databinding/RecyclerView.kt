package ch.app.archdemo.arch.databinding

import android.graphics.Color
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.fondesa.recyclerviewdivider.RecyclerViewDivider
import ch.app.archdemo.arch.recyclerview.IViewHolder
import ch.app.archdemo.arch.recyclerview.RecyclerViewAdapter

@BindingAdapter("items")
fun initRecyclerView(recyclerView: RecyclerView, list: PagedList<IViewHolder>?) {
    if (null == recyclerView.adapter) {
        recyclerView.adapter = RecyclerViewAdapter(recyclerView.context)
    }

    (recyclerView.adapter as? RecyclerViewAdapter)?.submitList(list)
}

@BindingAdapter("dividerSize")
fun setItemDecoration(recyclerView: RecyclerView, dividerSize: Int) {
    RecyclerViewDivider.with(recyclerView.context)
        .size((dividerSize * recyclerView.context.resources.displayMetrics.density).toInt())
        .color(Color.TRANSPARENT)
        .build()
        .addTo(recyclerView)
}