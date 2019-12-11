package ch.app.archdemo.arch.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import ch.app.archdemo.BR
import ch.app.archdemo.arch.router.AbstractRouter
import ch.app.archdemo.arch.router.IInteractor
import ch.app.archdemo.arch.viewmodel.AbstractViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class AbstractFragment<B : ViewDataBinding> : DaggerFragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
    protected abstract val viewModel: AbstractViewModel<out Any>
    protected abstract val router: AbstractRouter<out IInteractor, out Any>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<B>(inflater, getLayoutRes(), container, false)
            .also { setBinding(it) }.root
    }

    protected open fun setBinding(binding: B) {
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
    }

    /**
     * Wrapper function for mock
     */
    fun navController(): NavController {
        return findNavController()
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int
}