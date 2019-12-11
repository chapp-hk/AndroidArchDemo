package ch.app.data.repository.timeline

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface TimelineApi {

    @GET
    fun getTimelineList(@Url url: String): Single<List<TimelineEntity>>
}