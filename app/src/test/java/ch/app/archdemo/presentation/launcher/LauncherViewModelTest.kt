package ch.app.archdemo.presentation.launcher

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ch.app.domain.master.MasterUsecase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LauncherViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var masterUseCase: MasterUsecase

    private lateinit var viewModel: LauncherViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = LauncherViewModel(masterUseCase)
    }

    @Test
    fun `getMasterList success`() {
        every { masterUseCase.getMasterList() } returns Single.just(listOf())

        viewModel.getMasterList()

        verify { masterUseCase.getMasterList() }
        assertEquals(true, viewModel.isLoading.value)
        assertEquals(false, viewModel.isError.value)
        assertNotNull(viewModel.action.value)
    }

    @Test
    fun `getMasterList error`() {
        every { masterUseCase.getMasterList() } returns Single.error(Throwable())

        viewModel.getMasterList()

        verify { masterUseCase.getMasterList() }
        assertEquals(false, viewModel.isLoading.value)
        assertEquals(true, viewModel.isError.value)
        assertNull(viewModel.action.value)
    }
}