package com.example.stresscoping.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "stress_coping", indices = [Index(value = ["id"], unique = true)])
data class StressCopingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String
)
