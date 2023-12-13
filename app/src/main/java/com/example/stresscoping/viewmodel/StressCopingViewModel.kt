package com.example.stresscoping.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stresscoping.model.StressCopingModel

class StressCopingViewModel : ViewModel() {
    private val stressCopingModels: ArrayList<StressCopingModel> = arrayListOf(
        StressCopingModel(0, "サウナ"),
        StressCopingModel(1, "甘い物を食べる"),
        StressCopingModel(2, "寝る"),
        StressCopingModel(3, "散歩"),
        StressCopingModel(4, "読書"),
        StressCopingModel(5, "風呂")
    )
    val textStressCoping: MutableLiveData<String> = MutableLiveData()

    init {
        textStressCoping.value = "ボタンを押してください"
    }

    fun clickChoose() {
        stressCopingModels.shuffle()
        val model = stressCopingModels.first()
        textStressCoping.postValue(model.title)
    }
}