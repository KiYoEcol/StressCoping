package com.example.stresscoping.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface StressCopingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(stressCoping: StressCopingEntity)

    @Query("SELECT * FROM stress_coping")
    fun observeAll(): Flow<List<StressCopingEntity>>

    @Update
    fun update(stressCoping: StressCopingEntity)

    @Delete
    fun delete(stressCoping: StressCopingEntity)
}