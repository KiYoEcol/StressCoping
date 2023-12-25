package com.example.stresscoping.database

import android.content.Context
import androidx.room.Room

private lateinit var STRESS_COPING_DATABASE_INSTANCE: StressCopingDatabase
fun getStressCopingDatabase(context: Context): StressCopingDatabase{
    synchronized(StressCopingDatabase::class.java){
        if (!::STRESS_COPING_DATABASE_INSTANCE.isInitialized){
            STRESS_COPING_DATABASE_INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                StressCopingDatabase::class.java,
                "stress_coping"
            ).createFromAsset("predatabase_stress_coping.db")
                .build()
        }
    }
    return STRESS_COPING_DATABASE_INSTANCE
}