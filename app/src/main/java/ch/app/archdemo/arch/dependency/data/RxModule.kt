package ch.app.archdemo.arch.dependency.data

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.SchedulerSupport.CUSTOM
import io.reactivex.annotations.SchedulerSupport.IO
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class RxModule {

    @Provides
    @Singleton
    @Named(CUSTOM)
    fun provideAndroidScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @Singleton
    @Named(IO)
    fun provideIoScheduler(): Scheduler {
        return Schedulers.io()
    }
}