package com.example.stresscoping.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.stresscoping.R
import com.example.stresscoping.database.getStressCopingDatabase
import com.example.stresscoping.model.StressCopingModel
import com.example.stresscoping.repository.StressCopingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StressCopingViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: StressCopingRepository =
        StressCopingRepository(getStressCopingDatabase(application))
    val stressCopings: LiveData<List<StressCopingModel>> = repository.allFlow.asLiveData()
    private val _textStressCoping = MutableLiveData<String>()
    val textStressCoping: LiveData<String> = _textStressCoping
    private var isChangeTextStressCoping = false
    private val _btnStressCoping = MutableLiveData<String>()
    val btnStressCoping: LiveData<String> = _btnStressCoping

    init {
        _textStressCoping.value = application.getString(R.string.first_text_on_stress_coping)
        _btnStressCoping.value = application.getString(R.string.btn_start)
    }

    fun clickStart() {
        val stressCopings = this@StressCopingViewModel.stressCopings.value
        if (!stressCopings.isNullOrEmpty()) {
            viewModelScope.launch(Dispatchers.Main) {
                _btnStressCoping.postValue(getApplication<Application>().getString(R.string.btn_stop))
                isChangeTextStressCoping = true
                while (isChangeTextStressCoping) {
                    val stressCoping = stressCopings.random()
                    _textStressCoping.postValue(stressCoping.title)
                    delay(100)
                }
            }
        } else {
            _textStressCoping.postValue(getApplication<Application>().getString(R.string.empty_text_on_stress_coping))
        }
    }

    fun clickStop() {
        isChangeTextStressCoping = false
        _btnStressCoping.postValue(getApplication<Application>().getString(R.string.btn_start))
    }

    fun stopStressCopingIfChangingText() {
        val stressCopings = this@StressCopingViewModel.stressCopings.value
        if (!stressCopings.isNullOrEmpty() && isChangeTextStressCoping) {
            isChangeTextStressCoping = false
            val stressCoping = stressCopings.random()
            _textStressCoping.postValue(stressCoping.title)
        }
        _btnStressCoping.postValue(getApplication<Application>().getString(R.string.btn_start))
    }
}