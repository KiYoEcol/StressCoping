package com.example.stresscoping

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged

class StressCopingListViewModel : ViewModel() {
    private val stressCopingsRaw: MutableList<StressCopingModel> = mutableListOf(
        StressCopingModel("サウナ"),
        StressCopingModel("甘い物を食べる"),
        StressCopingModel("寝る"),
        StressCopingModel("散歩"),
        StressCopingModel("読書"),
        StressCopingModel("風呂")
    )
    private val _stressCopings = MutableLiveData<List<StressCopingModel>>(ArrayList(stressCopingsRaw))
    val stressCopings: LiveData<List<StressCopingModel>> = _stressCopings.distinctUntilChanged()

    fun addStressCoping(stressCoping: StressCopingModel) {
        stressCopingsRaw.add(stressCoping)
        _stressCopings.postValue(ArrayList(stressCopingsRaw))
    }

    fun onClickItem(stressCoping: StressCopingModel) {}
}