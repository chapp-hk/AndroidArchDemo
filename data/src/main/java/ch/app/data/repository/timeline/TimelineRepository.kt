package ch.app.data.repository.timeline

import androidx.room.EmptyResultSetException
import ch.app.domain.timeline.ITimelineRepository
import ch.app.domain.timeline.TimelineModel
import io.reactivex.Single

class TimelineRepository(
    private val timelineApi: TimelineApi,
    private val timelineDao: TimelineDao
) : ITimelineRepository {

    override fun getTimelineList(url: String, category: String): Single<List<TimelineModel>> {
        return timelineApi.getTimelineList(url)
            .onErrorResumeNext { timelineDao.getTimelineList(category) }
            .map { setCategory(it, category) }
            .doOnSuccess { refreshLocalTimelineList(it, category) }
            .flatMap(this::assertList)
            .map(this::mapEntityToModel)
    }

    private fun setCategory(list: List<TimelineEntity>, targetCategory: String): List<TimelineEntity> {
        return list.map {
            it.apply {
                category = targetCategory
            }
        }
    }

    private fun refreshLocalTimelineList(list: List<TimelineEntity>, category: String) {
        timelineDao.deleteAll(category)
        timelineDao.insertAll(list)
    }

    private fun assertList(list: List<TimelineEntity>): Single<List<TimelineEntity>> {
        return if (list.isEmpty()) {
            Single.error(EmptyResultSetException("Timeline table is empty"))
        } else {
            Single.just(list)
        }
    }

    private fun mapEntityToModel(list: List<TimelineEntity>): List<TimelineModel> {
        return list.map {
            TimelineModel(
                id = it.id,
                name = it.name,
                status = it.status,
                numLikes = it.numLikes,
                numComments = it.numComments,
                price = it.price,
                photo = it.photo,
                category = it.category
            )
        }
    }
}