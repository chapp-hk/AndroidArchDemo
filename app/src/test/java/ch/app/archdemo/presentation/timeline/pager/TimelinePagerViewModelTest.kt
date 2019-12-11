package ch.app.archdemo.presentation.timeline.pager

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ch.app.archdemo.app.timeline.pager.TimelinePagerAction
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TimelinePagerViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: TimelinePagerViewModel

    @Before
    fun setUp() {
        viewModel = TimelinePagerViewModel()
    }

    @Test
    fun createNewItem() {
        viewModel.createNewItem()
        assertEquals(TimelinePagerAction.CreateItem, viewModel.action.value)
    }
}