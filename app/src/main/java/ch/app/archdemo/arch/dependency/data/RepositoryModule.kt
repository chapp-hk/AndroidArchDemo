package ch.app.archdemo.arch.dependency.data

import ch.app.data.repository.master.MasterApi
import ch.app.data.repository.master.MasterDao
import ch.app.data.repository.master.MasterRepository
import ch.app.data.repository.timeline.TimelineApi
import ch.app.data.repository.timeline.TimelineDao
import ch.app.data.repository.timeline.TimelineRepository
import ch.app.domain.master.IMasterRepository
import ch.app.domain.timeline.ITimelineRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApiModule::class, DaoModule::class, RxModule::class, NetworkModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    internal fun providesMasterRepository(masterApi: MasterApi, masterDao: MasterDao): IMasterRepository {
        return MasterRepository(masterApi, masterDao)
    }

    @Provides
    @Singleton
    internal fun providesTimelineRepository(timelineApi: TimelineApi, timelineDao: TimelineDao): ITimelineRepository {
        return TimelineRepository(timelineApi, timelineDao)
    }
}