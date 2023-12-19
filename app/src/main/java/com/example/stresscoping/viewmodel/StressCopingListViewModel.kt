package com.example.stresscoping.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.stresscoping.database.getStressCopingDatabase
import com.example.stresscoping.model.StressCopingModel
import com.example.stresscoping.repository.StressCopingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StressCopingListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = StressCopingRepository(getStressCopingDatabase(application))
    val stressCopings: LiveData<List<StressCopingModel>> = repository.allFlow.asLiveData()

    fun addStressCoping(stressCoping: StressCopingModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(stressCoping)
        }
    }

    fun onClickItem(stressCoping: StressCopingModel) {}
}