package ch.app.archdemo.arch.databinding

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["context", "displayHomeAsUp"], requireAll = true)
fun setup(toolbar: Toolbar, context: Context, displayHomeAsUp: Boolean) {
    (context as? AppCompatActivity)?.also {
        it.setSupportActionBar(toolbar)
        it.supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUp)
        it.supportActionBar?.setDisplayShowHomeEnabled(displayHomeAsUp)
    }
}