package ch.app.archdemo.app.timeline.pager

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import ch.app.archdemo.BR
import ch.app.archdemo.R
import ch.app.archdemo.app.timeline.pager.TimelinePagerFragmentArgs
import ch.app.archdemo.app.timeline.list.TimelineListFragment
import ch.app.archdemo.arch.Contants.KEY_DATA
import ch.app.archdemo.arch.fragment.AbstractFragment
import ch.app.archdemo.arch.viewpager.IViewPagerContainer
import ch.app.archdemo.databinding.TimelinePagerFragmentBinding
import ch.app.archdemo.presentation.timeline.pager.TimelinePagerRouter
import ch.app.archdemo.presentation.timeline.pager.TimelinePagerViewModel
import ch.app.domain.master.MasterModel

class TimelinePagerFragment :
    AbstractFragment<TimelinePagerFragmentBinding>(),
    ITimelinePagerInteractor,
    IViewPagerContainer {

    override val viewModel: TimelinePagerViewModel by viewModels { viewModelFactory }
    override val router =
        TimelinePagerRouter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        router.observe(this, viewModel.action)
    }

    override fun setBinding(binding: TimelinePagerFragmentBinding) {
        super.setBinding(binding)
        binding.setVariable(BR.viewPagerContainer, this)
    }

    override fun getLayoutRes(): Int {
        return R.layout.timeline_pager_fragment
    }

    override fun toCreateNewItem() {
        //pending for implementation
    }

    override fun getViewPagerFragmentManager(): FragmentManager {
        return childFragmentManager
    }

    override fun getItem(position: Int): Fragment {
        return TimelineListFragment().apply {
            arguments = bundleOf(KEY_DATA to getMasterList()[position])
        }
    }

    override fun getCount(): Int {
        return getMasterList().size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return getMasterList()[position].category
    }

    private fun getMasterList(): List<MasterModel> {
        return TimelinePagerFragmentArgs.fromBundle(requireArguments()).masterList.asList()
    }
}