package com.example.stresscoping.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stresscoping.StressCopingModel

class StressCopingViewModel : ViewModel() {
    private val stressCopingModels: ArrayList<StressCopingModel> = arrayListOf(
        StressCopingModel("サウナ"),
        StressCopingModel("甘い物を食べる"),
        StressCopingModel("寝る"),
        StressCopingModel("散歩"),
        StressCopingModel("読書"),
        StressCopingModel("風呂")
    )
    val textStressCoping: MutableLiveData<String> = MutableLiveData()

    init {
        textStressCoping.value = "ボタンを押してください"
    }

    fun clickChoose() {
        stressCopingModels.shuffle()
        val model = stressCopingModels.first()
        textStressCoping.postValue(model.stressCoping)
    }
}