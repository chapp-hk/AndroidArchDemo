package ch.app.archdemo.arch.recyclerview

import androidx.annotation.LayoutRes

interface IViewHolder {

    @LayoutRes
    fun getLayoutId(): Int
}