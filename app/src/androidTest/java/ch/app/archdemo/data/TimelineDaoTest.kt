package ch.app.archdemo.data

import ch.app.data.repository.timeline.TimelineDao
import ch.app.data.repository.timeline.TimelineEntity
import ch.app.archdemo.arch.data.AbstractDaoTest
import org.junit.Before
import org.junit.Test

class TimelineDaoTest : AbstractDaoTest() {

    private lateinit var timelineDao: TimelineDao

    @Before
    override fun setUp() {
        super.setUp()
        timelineDao = daoProvider.getTimelineDao()
    }

    @Test
    fun insert_get_delete_test() {
        val timelineEntityList = listOf(
            TimelineEntity(
                "mock id",
                "mock name",
                "mock status",
                24,
                89,
                33,
                "mock photo",
                "category"
            )
        )

        timelineDao.insertAll(timelineEntityList)

        timelineDao.getTimelineList("category")
            .test()
            .assertValue { it == timelineEntityList }

        timelineDao.deleteAll("category")

        timelineDao.getTimelineList("category")
            .test()
            .assertValue { it.isEmpty() }
    }
}