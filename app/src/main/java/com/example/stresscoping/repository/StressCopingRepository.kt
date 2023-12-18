package com.example.stresscoping.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.stresscoping.database.StressCopingDatabase
import com.example.stresscoping.database.StressCopingEntity
import com.example.stresscoping.model.StressCopingModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StressCopingRepository(private val database: StressCopingDatabase) {
    val getAllLiveData: LiveData<List<StressCopingModel>> =
        database.stressCopingDao().getAllLiveData().map { list ->
            list.map { entity ->
                StressCopingModel(entity.id, entity.title)
            }
        }

    suspend fun insert(stressCoping: StressCopingModel) {
        withContext(Dispatchers.IO) {
            val entity = StressCopingEntity(stressCoping.id, stressCoping.title)
            database.stressCopingDao().insert(entity)
        }
    }

    suspend fun update(stressCoping: StressCopingModel) {
        withContext(Dispatchers.IO) {
            val entity = StressCopingEntity(stressCoping.id, stressCoping.title)
            database.stressCopingDao().update(entity)
        }
    }

    suspend fun delete(stressCoping: StressCopingModel) {
        withContext(Dispatchers.IO) {
            val entity = StressCopingEntity(stressCoping.id, stressCoping.title)
            database.stressCopingDao().delete(entity)
        }
    }
}