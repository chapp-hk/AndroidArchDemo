package ch.app.data.repository.master

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "master")
@JsonClass(generateAdapter = true)
data class MasterEntity(

    @PrimaryKey
    @Json(name = "name")
    val name: String,

    @Json(name = "data")
    val data: String
)