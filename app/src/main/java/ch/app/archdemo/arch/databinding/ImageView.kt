package ch.app.archdemo.arch.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("image")
fun setImage(imageView: ImageView, imageRes: Int?) {
    if (null == imageRes) {
        imageView.setImageDrawable(null)
    } else {
        imageView.setImageResource(imageRes)
    }
}