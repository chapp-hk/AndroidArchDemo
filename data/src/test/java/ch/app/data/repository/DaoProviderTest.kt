package ch.app.data.repository

import org.junit.Assert.assertNotNull
import org.junit.Test

class DaoProviderTest {

    private val daoProvider = DaoProvider_Impl()

    @Test
    fun getMasterDao() {
        assertNotNull(daoProvider.getMasterDao())
    }

    @Test
    fun getTimelineDao() {
        assertNotNull(daoProvider.getTimelineDao())
    }
}