package ch.app.archdemo.arch.data

import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class TestRequestDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        val path = request.path?.toLowerCase()
        return when {
            null == path -> MockResponse()
            path.endsWith("men.json") -> MockResponse().setBody(getAssetString("men.json"))
            path.endsWith("women.json") -> MockResponse().setBody(getAssetString("women.json"))
            path.endsWith("all.json") -> MockResponse().setBody(getAssetString("all.json"))
            path.endsWith("master.json") -> MockResponse().setBody(getAssetString("master.json"))
            else -> MockResponse()
        }
    }

    private fun getAssetString(fileName: String): String {
        return InstrumentationRegistry.getInstrumentation()
            .context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}