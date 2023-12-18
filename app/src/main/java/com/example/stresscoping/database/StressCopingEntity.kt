package com.example.stresscoping.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stress_coping")
data class StressCopingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String
)
