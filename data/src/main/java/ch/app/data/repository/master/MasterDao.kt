package ch.app.data.repository.master

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface MasterDao {

    @Query("SELECT * FROM master")
    fun getMasterList(): Single<List<MasterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<MasterEntity>)

    @Query("DELETE FROM master")
    fun deleteAll()
}