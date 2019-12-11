package ch.app.archdemo.app.launcher

import android.os.Bundle
import androidx.fragment.app.viewModels
import ch.app.archdemo.R
import ch.app.archdemo.arch.fragment.AbstractFragment
import ch.app.archdemo.databinding.LauncherFragmentBinding
import ch.app.archdemo.presentation.launcher.LauncherRouter
import ch.app.archdemo.presentation.launcher.LauncherViewModel
import ch.app.domain.master.MasterModel

class LauncherFragment : AbstractFragment<LauncherFragmentBinding>(), ILauncherInteractor {

    override val viewModel: LauncherViewModel by viewModels { viewModelFactory }
    override val router = LauncherRouter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router.observe(this, viewModel.action)
        viewModel.getMasterList()
    }

    override fun getLayoutRes(): Int {
        return R.layout.launcher_fragment
    }

    override fun toTimeline(array: List<MasterModel>) {
        navController().navigate(LauncherFragmentDirections.actionLauncherToTimelinePager(array.toTypedArray()))
    }
}