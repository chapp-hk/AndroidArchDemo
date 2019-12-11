package ch.app.archdemo.arch.dependency.data

import ch.app.data.repository.master.MasterApi
import ch.app.data.repository.timeline.TimelineApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun providesMasterApi(retrofit: Retrofit): MasterApi {
        return retrofit.create(MasterApi::class.java)
    }

    @Provides
    @Singleton
    internal fun providesTimelineApi(retrofit: Retrofit): TimelineApi {
        return retrofit.create(TimelineApi::class.java)
    }
}