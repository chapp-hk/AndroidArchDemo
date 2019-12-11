package ch.app.archdemo.arch.viewpager

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter

@SuppressLint("WrongConstant")
class ViewPagerAdapter(private val viewPagerContainer: IViewPagerContainer) :
    FragmentStatePagerAdapter(viewPagerContainer.getViewPagerFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return viewPagerContainer.getItem(position)
    }

    override fun getCount(): Int {
        return viewPagerContainer.getCount()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return viewPagerContainer.getPageTitle(position)
    }
}