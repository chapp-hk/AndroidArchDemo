package ch.app.archdemo.app.launcher

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import ch.app.archdemo.R
import ch.app.archdemo.arch.fragment.AbstractFragmentTest
import org.junit.Test

class LauncherFragmentTest : AbstractFragmentTest() {

    @Test
    fun get_master_api_success_and_redirect_to_timeline() {
        activityRule.launchActivity(null)

        //check ViewPager is displayed
        onView(withId(R.id.viewPager)).check(matches(isDisplayed()))
    }

    @Test
    fun get_master_api_error_and_display_error_view() {
        clearDatabase()
        mockWebserverError("ERROR")

        activityRule.launchActivity(null)

        mockWebserverSuccess()

        //check error view is displayed and perform click
        onView(withId(R.id.tv_error))
            .check(matches(isDisplayed()))
            .perform(click())

        //check ViewPager is displayed
        onView(withId(R.id.viewPager)).check(matches(isDisplayed()))
    }
}