package ch.app.archdemo.arch.dependency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.app.archdemo.presentation.launcher.LauncherViewModel
import ch.app.archdemo.presentation.timeline.list.TimelineListViewModel
import ch.app.archdemo.presentation.timeline.pager.TimelinePagerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilderModule {

    @Binds
    internal abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LauncherViewModel::class)
    internal abstract fun bindsLauncherViewModel(viewModel: LauncherViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TimelinePagerViewModel::class)
    internal abstract fun bindsTimelinePagerViewModel(viewModel: TimelinePagerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TimelineListViewModel::class)
    internal abstract fun bindsTimelineListViewModel(viewModel: TimelineListViewModel): ViewModel
}