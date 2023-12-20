package com.example.stresscoping.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.stresscoping.database.getStressCopingDatabase
import com.example.stresscoping.model.StressCopingModel
import com.example.stresscoping.repository.StressCopingRepository
import com.example.stresscoping.view.StressCopingAddDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StressCopingListViewModel(application: Application) : AndroidViewModel(application),
    StressCopingAddDialogFragment.Listener {
    private val repository = StressCopingRepository(getStressCopingDatabase(application))
    val stressCopings: LiveData<List<StressCopingModel>> = repository.allFlow.asLiveData()

    override fun onClickOkOnAddDialog(title: String) {
        addStressCoping(title)
    }

    fun onClickItem(stressCoping: StressCopingModel) {}

    private fun addStressCoping(title: String) {
        if (title.isNotBlank())
            viewModelScope.launch(Dispatchers.IO) {
                val model = StressCopingModel(0, title)
                repository.insert(model)
            }
    }
}
