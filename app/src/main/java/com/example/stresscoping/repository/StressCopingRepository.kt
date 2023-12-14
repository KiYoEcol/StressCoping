package com.example.stresscoping.repository

import com.example.stresscoping.database.StressCopingDatabase
import com.example.stresscoping.database.StressCopingEntity
import com.example.stresscoping.model.StressCopingModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext

class StressCopingRepository(private val database: StressCopingDatabase) {
    val observeAll: Flow<List<StressCopingModel>> =
        database.stressCopingDao().observeAll().transform {
            it.map {
                StressCopingModel(it.id, it.title)
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