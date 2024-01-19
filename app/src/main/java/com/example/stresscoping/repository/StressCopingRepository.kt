package com.example.stresscoping.repository

import com.example.stresscoping.database.StressCopingDatabase
import com.example.stresscoping.database.StressCopingEntity
import com.example.stresscoping.model.StressCopingModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class StressCopingRepository(private val database: StressCopingDatabase) {
    val allFlow: Flow<List<StressCopingModel>> =
        database.stressCopingDao().getAllFlow().map { list ->
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

    suspend fun deletes(vararg stressCopings: StressCopingModel) {
        withContext(Dispatchers.IO) {
            val entities = stressCopings.map { StressCopingEntity(it.id, it.title) }.toTypedArray()
            database.stressCopingDao().deletes(*entities)
        }
    }
}