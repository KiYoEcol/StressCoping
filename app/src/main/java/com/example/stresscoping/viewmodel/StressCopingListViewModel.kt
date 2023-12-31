package com.example.stresscoping.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.stresscoping.SingleLiveData
import com.example.stresscoping.database.getStressCopingDatabase
import com.example.stresscoping.model.StressCopingModel
import com.example.stresscoping.repository.StressCopingRepository
import com.example.stresscoping.view.StressCopingAddDialogFragment
import com.example.stresscoping.view.StressCopingDeleteDialogFragment
import com.example.stresscoping.view.StressCopingEditDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StressCopingListViewModel(application: Application) : AndroidViewModel(application),
    StressCopingAddDialogFragment.Listener, StressCopingDeleteDialogFragment.Listener,
    StressCopingEditDialogFragment.Listener {
    private val repository = StressCopingRepository(getStressCopingDatabase(application))
    val stressCopings: LiveData<List<StressCopingModel>> = repository.allFlow.asLiveData()
    private val _showEditDialog = SingleLiveData<StressCopingModel>()
    val showEditDialog: LiveData<StressCopingModel> = _showEditDialog
    private val _showDeleteDialog = SingleLiveData<StressCopingModel>()
    val showDeleteDialog: LiveData<StressCopingModel> = _showDeleteDialog

    fun onClickEditButton(stressCoping: StressCopingModel) {
        _showEditDialog.postValue(stressCoping)
    }

    fun onClickDeleteButton(stressCoping: StressCopingModel) {
        _showDeleteDialog.postValue(stressCoping)
    }

    override fun onClickOkOnAddDialog(title: String) {
        addStressCoping(title)
    }

    override fun onClickUpdateOnEditDialog(stressCoping: StressCopingModel) {
        updateStressCoping(stressCoping)
    }

    override fun onClickDeleteOnDeleteDialog(model: StressCopingModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(model)
        }
    }

    private fun addStressCoping(title: String) {
        if (title.isNotBlank())
            viewModelScope.launch(Dispatchers.IO) {
                val model = StressCopingModel(0, title)
                repository.insert(model)
            }
    }

    private fun updateStressCoping(stressCoping: StressCopingModel) {
        if (stressCoping.title.isNotBlank())
            viewModelScope.launch(Dispatchers.IO) {
                repository.update(stressCoping)
            }
    }
}
