package ch.app.archdemo.arch.dependency.app

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    internal abstract fun bindsContext(application: Application): Context
}
