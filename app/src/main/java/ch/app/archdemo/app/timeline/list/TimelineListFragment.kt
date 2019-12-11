package ch.app.archdemo.app.timeline.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import ch.app.archdemo.R
import ch.app.archdemo.arch.Contants.KEY_RECYCLERVIEW_STATE
import ch.app.archdemo.arch.fragment.AbstractFragment
import ch.app.archdemo.databinding.TimelineListFragmentBinding
import ch.app.archdemo.presentation.timeline.list.TimelineListRouter
import ch.app.archdemo.presentation.timeline.list.TimelineListViewModel
import ch.app.domain.timeline.TimelineModel
import kotlinx.android.synthetic.main.timeline_list_fragment.*

class TimelineListFragment : AbstractFragment<TimelineListFragmentBinding>(),
    ITimelineListInteractor {

    override val viewModel: TimelineListViewModel by viewModels { viewModelFactory }
    override val router =
        TimelineListRouter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        router.observe(this, viewModel.action)
        viewModel.initWithArgs(arguments)
        recyclerView.layoutManager?.onRestoreInstanceState(savedInstanceState?.getParcelable(KEY_RECYCLERVIEW_STATE))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_RECYCLERVIEW_STATE, recyclerView.layoutManager?.onSaveInstanceState())
    }

    override fun getLayoutRes(): Int {
        return R.layout.timeline_list_fragment
    }

    override fun onItemClick(timelineModel: TimelineModel) {
        //pending implementation
    }
}