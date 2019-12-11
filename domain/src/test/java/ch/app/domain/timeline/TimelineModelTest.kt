package ch.app.domain.timeline

import org.junit.Assert.assertEquals
import org.junit.Test

class TimelineModelTest {

    private val timelineModel = TimelineModel(
        "mock id",
        "mock name",
        "mock status",
        8,
        12,
        1000,
        "mock photo",
        "mock category"
    )

    @Test
    fun getId() {
        assertEquals("mock id", timelineModel.id)
    }

    @Test
    fun getName() {
        assertEquals("mock name", timelineModel.name)
    }

    @Test
    fun getStatus() {
        assertEquals("mock status", timelineModel.status)
    }

    @Test
    fun getNumLikes() {
        assertEquals(8, timelineModel.numLikes)
    }

    @Test
    fun getNumComments() {
        assertEquals(12, timelineModel.numComments)
    }

    @Test
    fun getPrice() {
        assertEquals(1000, timelineModel.price)
    }

    @Test
    fun getPhoto() {
        assertEquals("mock photo", timelineModel.photo)
    }

    @Test
    fun getCategory() {
        assertEquals("mock category", timelineModel.category)
    }
}