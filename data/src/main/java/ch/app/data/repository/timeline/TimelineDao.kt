package ch.app.data.repository.timeline

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface TimelineDao {

    @Query("SELECT * FROM timeline WHERE category = :category")
    fun getTimelineList(category: String): Single<List<TimelineEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<TimelineEntity>)

    @Query("DELETE FROM timeline WHERE category = :category")
    fun deleteAll(category: String)
}