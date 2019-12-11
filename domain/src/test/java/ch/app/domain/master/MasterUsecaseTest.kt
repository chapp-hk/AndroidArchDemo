package ch.app.domain.master

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test

class MasterUsecaseTest {

    @MockK
    private lateinit var masterRepository: IMasterRepository

    private val mainScheduler = TestScheduler()
    private val ioScheduler = TestScheduler()

    private lateinit var masterUsecase: MasterUsecase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        masterUsecase = MasterUsecase(mainScheduler, ioScheduler, masterRepository)
    }

    @Test
    fun getMasterList() {
        val single = spyk(Single.just(listOf<MasterModel>()))
        every { masterRepository.getMasterList() } returns single
        every { single.subscribeOn(any()) } returns single
        every { single.observeOn(any()) } returns single

        masterUsecase.getMasterList()

        verify { masterRepository.getMasterList() }
        verify { single.subscribeOn(eq(ioScheduler)) }
        verify { single.observeOn(eq(mainScheduler)) }
    }
}