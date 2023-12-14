package com.example.stresscoping.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StressCopingEntity::class], version = 1)
abstract class StressCopingDatabase : RoomDatabase() {
    abstract fun stressCopingDao(): StressCopingDao
}