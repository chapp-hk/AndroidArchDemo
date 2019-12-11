package ch.app.archdemo.app.timeline.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import ch.app.archdemo.R
import ch.app.archdemo.arch.fragment.AbstractFragmentTest
import ch.app.archdemo.arch.recyclerview.RecyclerViewHolder
import org.hamcrest.CoreMatchers.allOf
import org.junit.Test

class TimelineListFragmentTest : AbstractFragmentTest() {

    @Test
    fun check_recyclerview_item_displayed() {
        activityRule.launchActivity(null)

        //scroll to position 30
        onView(allOf(withId(R.id.recyclerView), isCompletelyDisplayed()))
            .perform(scrollToPosition<RecyclerViewHolder>(30))

        //verify view displayed correctly
        onView(allOf(withId(R.id.tv_name), withText("men29"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.tv_num_likes), withText("32"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.tv_num_comments), withText("8"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.tv_price), withText("$84"))).check(matches(isDisplayed()))

        //scroll to position 50
        onView(allOf(withId(R.id.recyclerView), isCompletelyDisplayed()))
            .perform(scrollToPosition<RecyclerViewHolder>(50))

        //verify view displayed correctly with formatted currency
        onView(allOf(withId(R.id.tv_name), withText("women1"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.tv_num_likes), withText("33"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.tv_num_comments), withText("41"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.tv_price), withText("$6,100"))).check(matches(isDisplayed()))
    }
}