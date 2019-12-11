package ch.app.archdemo.arch.fragment

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import ch.app.data.repository.DaoProvider
import ch.app.archdemo.app.MainActivity
import ch.app.archdemo.arch.data.TestRequestDispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.QueueDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class AbstractFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    private val webServer = MockWebServer()

    @Before
    open fun setUp() {
        webServer.start(8080)
        mockWebserverSuccess()
    }

    @After
    fun tearDown() {
        webServer.shutdown()
    }

    protected fun clearDatabase() {
        Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext,
            DaoProvider::class.java,
            "DaoProvider.db"
        ).build().apply {
            getMasterDao().deleteAll()
            getTimelineDao().deleteAll("category")
        }
    }

    protected fun mockWebserverSuccess() {
        webServer.dispatcher = TestRequestDispatcher()
    }

    protected fun mockWebserverError(body: String) {
        webServer.dispatcher =
            QueueDispatcher().apply { enqueueResponse(MockResponse().setResponseCode(500).setBody(body)) }
    }
}