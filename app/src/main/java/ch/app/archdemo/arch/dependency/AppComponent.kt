package ch.app.archdemo.arch.dependency

import android.app.Application
import ch.app.archdemo.arch.dependency.app.FragmentBuilderModule
import ch.app.archdemo.arch.dependency.data.MoshiModule
import ch.app.archdemo.arch.dependency.data.RepositoryModule
import ch.app.archdemo.arch.dependency.data.RetrofitModule
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
        RetrofitModule::class,
        MoshiModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}