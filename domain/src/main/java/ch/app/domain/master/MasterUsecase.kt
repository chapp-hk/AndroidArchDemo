package ch.app.domain.master

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.annotations.SchedulerSupport
import javax.inject.Inject
import javax.inject.Named

class MasterUsecase @Inject
constructor(
    @Named(SchedulerSupport.CUSTOM) private val mainScheduler: Scheduler,
    @Named(SchedulerSupport.IO) private val ioScheduler: Scheduler,
    private val masterRepository: IMasterRepository
) {

    fun getMasterList(): Single<List<MasterModel>> {
        return masterRepository.getMasterList()
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
    }
}