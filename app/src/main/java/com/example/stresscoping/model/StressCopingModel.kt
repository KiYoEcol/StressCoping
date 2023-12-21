package com.example.stresscoping.model

import com.google.gson.Gson

data class StressCopingModel(
    val id: Int,
    val title: String
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        val KEY_STRESS_COPING_MODEL = "key_stress_coping_model"
        fun fromJson(json: String): StressCopingModel {
            return Gson().fromJson(json, StressCopingModel::class.java)
        }
    }
}
