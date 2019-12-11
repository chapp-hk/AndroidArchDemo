package ch.app.domain.timeline

data class TimelineModel(

    val id: String,

    val name: String,

    val status: String,

    val numLikes: Int,

    val numComments: Int,

    val price: Int,

    val photo: String,

    val category: String
)