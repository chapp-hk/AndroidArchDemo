package ch.app.data.repository.timeline

import org.junit.Assert.assertEquals
import org.junit.Test

class TimelineEntityTest {

    private val timelineEntity = TimelineEntity(
        id = "mock id",
        name = "mock name",
        status = "mock status",
        numLikes = 29,
        numComments = 13,
        price = 18,
        photo = "mock photo"
    )

    @Test
    fun getId() {
        assertEquals("mock id", timelineEntity.id)
    }

    @Test
    fun getName() {
        assertEquals("mock name", timelineEntity.name)
    }

    @Test
    fun getStatus() {
        assertEquals("mock status", timelineEntity.status)
    }

    @Test
    fun getNumLikes() {
        assertEquals(29, timelineEntity.numLikes)
    }

    @Test
    fun getNumComments() {
        assertEquals(13, timelineEntity.numComments)
    }

    @Test
    fun getPrice() {
        assertEquals(18, timelineEntity.price)
    }

    @Test
    fun getPhoto() {
        assertEquals("mock photo", timelineEntity.photo)
    }

    @Test
    fun `set and get category`() {
        timelineEntity.category = "mock category"
        assertEquals("mock category", timelineEntity.category)
    }
}