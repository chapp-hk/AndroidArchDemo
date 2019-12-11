package ch.app.archdemo.data

import ch.app.data.repository.master.MasterDao
import ch.app.data.repository.master.MasterEntity
import ch.app.archdemo.arch.data.AbstractDaoTest
import org.junit.Before
import org.junit.Test

class MasterDaoTest : AbstractDaoTest() {

    private lateinit var masterDao: MasterDao

    @Before
    override fun setUp() {
        super.setUp()
        masterDao = daoProvider.getMasterDao()
    }

    @Test
    fun insert_get_delete_test() {
        val masterEntityList = listOf(MasterEntity("mock name", "mock data"))

        masterDao.insertAll(masterEntityList)

        masterDao.getMasterList()
            .test()
            .assertValue { it == masterEntityList }

        masterDao.deleteAll()

        masterDao.getMasterList()
            .test()
            .assertValue { it.isEmpty() }
    }
}