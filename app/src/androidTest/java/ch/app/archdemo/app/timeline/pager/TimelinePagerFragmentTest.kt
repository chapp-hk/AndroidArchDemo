package ch.app.archdemo.app.timeline.pager

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import ch.app.archdemo.R
import ch.app.archdemo.arch.fragment.AbstractFragmentTest
import org.junit.Test

class TimelinePagerFragmentTest : AbstractFragmentTest() {

    @Test
    fun swipe_viewpager_for_all_categories() {
        activityRule.launchActivity(null)

        onView(withId(R.id.viewPager))
            .check(matches(hasDescendant(withText("All"))))
            .perform(swipeLeft())
            .check(matches(hasDescendant(withText("Men"))))
            .perform(swipeLeft())
            .check(matches(hasDescendant(withText("Women"))))
            .perform(swipeRight())
            .check(matches(hasDescendant(withText("Men"))))
            .perform(swipeRight())
            .check(matches(hasDescendant(withText("All"))))
    }

    @Test
    fun check_floating_action_button_is_displayed() {
        activityRule.launchActivity(null)

        onView(withId(R.id.floatingActionButton))
            .check(matches(isDisplayed()))
    }
}