package ch.app.domain.master

import io.reactivex.Single

interface IMasterRepository {

    fun getMasterList(): Single<List<MasterModel>>
}