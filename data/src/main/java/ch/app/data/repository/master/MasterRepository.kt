package ch.app.data.repository.master

import androidx.room.EmptyResultSetException
import ch.app.domain.master.IMasterRepository
import ch.app.domain.master.MasterModel
import io.reactivex.Single

class MasterRepository(
    private val masterApi: MasterApi,
    private val masterDao: MasterDao
) : IMasterRepository {

    override fun getMasterList(): Single<List<MasterModel>> {
        return masterApi.getMasterList()
            .onErrorResumeNext { masterDao.getMasterList() }
            .doOnSuccess(this::refreshLocalMasterList)
            .flatMap(this::assertList)
            .map(this::mapEntityToModel)
    }

    private fun refreshLocalMasterList(list: List<MasterEntity>) {
        masterDao.deleteAll()
        masterDao.insertAll(list)
    }

    private fun assertList(list: List<MasterEntity>): Single<List<MasterEntity>> {
        return if (list.isEmpty()) {
            Single.error(EmptyResultSetException("Master table is empty"))
        } else {
            Single.just(list)
        }
    }

    private fun mapEntityToModel(list: List<MasterEntity>): List<MasterModel> {
        return list.map {
            MasterModel(
                category = it.name,
                url = it.data
            )
        }
    }
}