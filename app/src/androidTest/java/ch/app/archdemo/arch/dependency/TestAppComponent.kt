package ch.app.archdemo.arch.dependency

import android.app.Application
import ch.app.archdemo.arch.dependency.app.FragmentBuilderModule
import ch.app.archdemo.arch.dependency.data.MoshiModule
import ch.app.archdemo.arch.dependency.data.RepositoryModule
import ch.app.archdemo.arch.dependency.data.TestRetrofitModule
import ch.app.archdemo.arch.dependency.viewmodel.ViewModelBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelBuilderModule::class,
        FragmentBuilderModule::class,
        RepositoryModule::class,
        TestRetrofitModule::class,
        MoshiModule::class
    ]
)
interface TestAppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindApplication(application: Application): Builder

        fun build(): TestAppComponent
    }

    fun inject(app: Application)
}