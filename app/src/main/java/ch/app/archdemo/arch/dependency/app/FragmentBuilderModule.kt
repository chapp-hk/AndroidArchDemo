package ch.app.archdemo.arch.dependency.app

import ch.app.archdemo.app.launcher.LauncherFragment
import ch.app.archdemo.app.timeline.list.TimelineListFragment
import ch.app.archdemo.app.timeline.pager.TimelinePagerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun contributeLauncherFragment(): LauncherFragment

    @ContributesAndroidInjector
    internal abstract fun contributeTimelinePagerFragment(): TimelinePagerFragment

    @ContributesAndroidInjector
    internal abstract fun contributeTimelineListFragment(): TimelineListFragment
}