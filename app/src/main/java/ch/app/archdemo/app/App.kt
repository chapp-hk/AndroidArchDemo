package ch.app.archdemo.app

import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import ch.app.archdemo.BuildConfig
import ch.app.archdemo.arch.dependency.DaggerAppComponent
import ch.app.archdemo.arch.util.OpenForTesting
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber


@OpenForTesting
class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        initSdk()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    private fun initSdk() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Fresco.initialize(this, ImagePipelineConfig.newBuilder(this).setDownsampleEnabled(true).build())
    }
}