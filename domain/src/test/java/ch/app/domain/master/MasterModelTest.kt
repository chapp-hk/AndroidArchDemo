package ch.app.domain.master

import org.junit.Assert.assertEquals

class MasterModelTest {

    private val masterModel = MasterModel("mock category", "mock url")

    @org.junit.Test
    fun getCategory() {
        assertEquals("mock category", masterModel.category)
    }

    @org.junit.Test
    fun getUrl() {
        assertEquals("mock url", masterModel.url)
    }
}