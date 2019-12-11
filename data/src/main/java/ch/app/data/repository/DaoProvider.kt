package ch.app.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import ch.app.data.repository.master.MasterEntity
import ch.app.data.repository.timeline.TimelineEntity
import ch.app.data.repository.master.MasterDao
import ch.app.data.repository.timeline.TimelineDao

@Database(
    entities = [
        MasterEntity::class,
        TimelineEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DaoProvider : RoomDatabase() {

    abstract fun getMasterDao(): MasterDao

    abstract fun getTimelineDao(): TimelineDao
}
