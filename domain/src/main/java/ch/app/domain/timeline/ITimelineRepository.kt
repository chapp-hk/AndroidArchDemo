package ch.app.domain.timeline

import io.reactivex.Single

interface ITimelineRepository {

    fun getTimelineList(url: String, category: String): Single<List<TimelineModel>>
}