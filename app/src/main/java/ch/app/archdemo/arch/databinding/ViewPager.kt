package ch.app.archdemo.arch.databinding

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import ch.app.archdemo.arch.viewpager.IViewPagerContainer
import ch.app.archdemo.arch.viewpager.ViewPagerAdapter

@BindingAdapter("viewPagerContainer")
fun setAdapter(viewPager: ViewPager, viewPagerContainer: IViewPagerContainer) {
    if (null == viewPager.adapter) {
        viewPager.adapter = ViewPagerAdapter(viewPagerContainer)
    }
}