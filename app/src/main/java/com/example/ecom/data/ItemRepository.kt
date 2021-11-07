package com.example.ecom.data

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData


/**
 * A repository class abstracts access to multiple data sources.
 *
 * The repository is not part of the Architecture Components libraries, but is a suggested best
 * practice for code separation and architecture. A Repository class provides a clean API for data
 * access to the rest of the application.
 */

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ItemRepository(application: Application) {

    private val itemDao = AppDatabase.getInstance(application).getItemDao()
    val allItemList: LiveData<List<Item>> = itemDao.getAllItems()

    fun getSearchItems(searchKeyword: String): LiveData<List<Item>> {
        return itemDao.getSearchItems(searchKeyword)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(item: Item) {
        itemDao.insert(item)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(itemList: List<Item>) {
        itemDao.insert(itemList)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(item: Item) {
        itemDao.update(item)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(item: Item) {
        itemDao.delete(item)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        itemDao.deleteAll()
    }


}