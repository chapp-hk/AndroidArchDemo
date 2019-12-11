package ch.app.archdemo.arch.dependency.data

import android.app.Application
import androidx.room.Room
import ch.app.data.repository.DaoProvider
import ch.app.data.repository.master.MasterDao
import ch.app.data.repository.timeline.TimelineDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DaoModule {

    private val databaseName = "DaoProvider.db"

    @Provides
    @Singleton
    internal fun providesDatabase(application: Application): DaoProvider {
        return Room.databaseBuilder(
            application.applicationContext,
            DaoProvider::class.java, databaseName
        ).build()
    }

    @Provides
    @Singleton
    internal fun providesMasterDao(daoProvider: DaoProvider): MasterDao {
        return daoProvider.getMasterDao()
    }

    @Provides
    @Singleton
    internal fun providesTimelineDao(daoProvider: DaoProvider): TimelineDao {
        return daoProvider.getTimelineDao()
    }
}