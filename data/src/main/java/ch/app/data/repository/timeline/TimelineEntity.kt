package ch.app.data.repository.timeline

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "timeline")
@JsonClass(generateAdapter = true)
data class TimelineEntity(

    @PrimaryKey
    @Json(name = "id")
    val id: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "status")
    val status: String,

    @Json(name = "num_likes")
    val numLikes: Int,

    @Json(name = "num_comments")
    val numComments: Int,

    @Json(name = "price")
    val price: Int,

    @Json(name = "photo")
    val photo: String,

    /**
     * [category] field not from API response
     */
    var category: String = ""
)