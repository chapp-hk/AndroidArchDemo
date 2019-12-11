package ch.app.data.repository.master

import org.junit.Assert.assertEquals
import org.junit.Test

class MasterEntityTest {

    private val masterEntity = MasterEntity("mock name", "mock data")

    @Test
    fun getName() {
        assertEquals("mock name", masterEntity.name)
    }

    @Test
    fun getData() {
        assertEquals("mock data", masterEntity.data)
    }
}