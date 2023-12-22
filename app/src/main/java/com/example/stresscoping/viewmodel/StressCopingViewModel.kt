package com.example.stresscoping.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.stresscoping.R
import com.example.stresscoping.database.getStressCopingDatabase
import com.example.stresscoping.model.StressCopingModel
import com.example.stresscoping.repository.StressCopingRepository

class StressCopingViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: StressCopingRepository =
        StressCopingRepository(getStressCopingDatabase(application))
    val stressCopings: LiveData<List<StressCopingModel>> = repository.allFlow.asLiveData()
    private val _textStressCoping = MutableLiveData<String>()
    val textStressCoping: LiveData<String> = _textStressCoping

    init {
        _textStressCoping.value = application.getString(R.string.first_text_on_stress_coping)
    }

    fun clickChoose() {
        val stressCopings = this@StressCopingViewModel.stressCopings.value
        if (!stressCopings.isNullOrEmpty()) {
            val stressCoping = stressCopings.random()
            _textStressCoping.postValue(stressCoping.title)
        } else {
            _textStressCoping.postValue(getApplication<Application>().getString(R.string.empty_text_on_stress_coping))
        }
    }
}