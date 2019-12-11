package ch.app.archdemo.arch.databinding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibility")
fun setVisibility(view: View, isVisible: Boolean?) {
    view.visibility = if (true == isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}