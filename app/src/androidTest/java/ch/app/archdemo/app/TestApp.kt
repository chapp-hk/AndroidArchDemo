package ch.app.archdemo.app

import ch.app.archdemo.arch.dependency.DaggerTestAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class TestApp : App() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerTestAppComponent.builder()
            .bindApplication(this)
            .build()
            .also { it.inject(this) }
    }
}