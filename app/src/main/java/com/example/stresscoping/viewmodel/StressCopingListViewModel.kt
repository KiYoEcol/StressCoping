package com.example.stresscoping.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import com.example.stresscoping.model.StressCopingModel

class StressCopingListViewModel : ViewModel() {
    private val stressCopingsRaw: MutableList<StressCopingModel> = mutableListOf(
        StressCopingModel(0, "サウナ"),
        StressCopingModel(1, "甘い物を食べる"),
        StressCopingModel(2, "寝る"),
        StressCopingModel(3, "散歩"),
        StressCopingModel(4, "読書"),
        StressCopingModel(5, "風呂")
    )
    private val _stressCopings =
        MutableLiveData<List<StressCopingModel>>(ArrayList(stressCopingsRaw))
    val stressCopings: LiveData<List<StressCopingModel>> = _stressCopings.distinctUntilChanged()

    fun addStressCoping(stressCoping: StressCopingModel) {
        stressCopingsRaw.add(stressCoping)
        _stressCopings.postValue(ArrayList(stressCopingsRaw))
    }

    fun onClickItem(stressCoping: StressCopingModel) {}
}