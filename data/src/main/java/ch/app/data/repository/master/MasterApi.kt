package ch.app.data.repository.master

import io.reactivex.Single
import retrofit2.http.GET

interface MasterApi {

    @GET("master.json")
    fun getMasterList(): Single<List<MasterEntity>>
}