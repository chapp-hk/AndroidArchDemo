package ch.app.archdemo.arch.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface IViewPagerContainer {

    fun getViewPagerFragmentManager(): FragmentManager

    fun getItem(position: Int): Fragment

    fun getCount(): Int

    fun getPageTitle(position: Int): CharSequence?
}