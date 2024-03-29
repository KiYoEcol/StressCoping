package com.example.stresscoping.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.stresscoping.SingleLiveData
import com.example.stresscoping.database.getStressCopingDatabase
import com.example.stresscoping.model.StressCopingListItemModel
import com.example.stresscoping.model.StressCopingModel
import com.example.stresscoping.repository.StressCopingRepository
import com.example.stresscoping.view.StressCopingAddDialogFragment
import com.example.stresscoping.view.StressCopingDeleteDialogFragment
import com.example.stresscoping.view.StressCopingEditDialogFragment
import com.example.stresscoping.view.StressCopingsDeleteDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StressCopingListViewModel(application: Application) : AndroidViewModel(application),
    StressCopingAddDialogFragment.Listener, StressCopingDeleteDialogFragment.Listener,
    StressCopingEditDialogFragment.Listener, StressCopingsDeleteDialogFragment.Listener {
    private val repository = StressCopingRepository(getStressCopingDatabase(application))
    val stressCopingListItems: LiveData<List<StressCopingListItemModel>> =
        MediatorLiveData<List<StressCopingListItemModel>>().apply {
            addSource(repository.allFlow.asLiveData()) { originalList ->
                val convertedList = originalList.map { stressCoping ->
                    StressCopingListItemModel(
                        stressCoping = stressCoping,
                        type = StressCopingListItemModel.Type.Body,
                        isVisibleCheckBox = listState.value == StressCopingListState.Delete,
                        isCheck = false,
                        isVisibleEdit = listState.value != StressCopingListState.Delete,
                        isVisibleDelete = listState.value != StressCopingListState.Delete
                    )
                }.toMutableList()
                convertedList.add(
                    StressCopingListItemModel(
                        null,
                        StressCopingListItemModel.Type.Footer
                    )
                )
                value = convertedList
            }
        }
    private val _showEditDialog = SingleLiveData<StressCopingModel>()
    val showEditDialog: LiveData<StressCopingModel> = _showEditDialog
    private val _showDeleteDialog = SingleLiveData<StressCopingModel>()
    val showDeleteDialog: LiveData<StressCopingModel> = _showDeleteDialog
    private val _refreshRecyclerViewAdapter = SingleLiveData<Unit>()
    val refreshRecyclerViewAdapter: LiveData<Unit> = _refreshRecyclerViewAdapter
    private val _stressCopingListState =
        MutableLiveData<StressCopingListState>(StressCopingListState.Column)
    val stressCopingListState: LiveData<StressCopingListState> = _stressCopingListState
    private val _refreshRecyclerViewAdapterByPosition = SingleLiveData<Int>()
    val refreshRecyclerViewAdapterByPosition: LiveData<Int> = _refreshRecyclerViewAdapterByPosition
    private val _listState = MutableLiveData<StressCopingListState>()
    val listState: LiveData<StressCopingListState> = _listState

    init {
        _listState.value = StressCopingListState.Column
    }

    fun onClickItem(position: Int, stressCopingListItem: StressCopingListItemModel) {
        if (stressCopingListState.value == StressCopingListState.Delete) {
            stressCopingListItem.isCheck = !stressCopingListItem.isCheck
            _refreshRecyclerViewAdapterByPosition.postValue(position)
        }
    }

    fun changeListStateColumn() {
        stressCopingListItems.value?.forEach { stressCopingListItem ->
            if (stressCopingListItem.type == StressCopingListItemModel.Type.Body) {
                stressCopingListItem.isCheck = false
                stressCopingListItem.isVisibleCheckBox = false
                stressCopingListItem.isVisibleEdit = true
                stressCopingListItem.isVisibleDelete = true
            }
        }
        _stressCopingListState.postValue(StressCopingListState.Column)
        _refreshRecyclerViewAdapter.postValue(Unit)
        _listState.postValue(StressCopingListState.Column)
    }

    fun changeListStateToDelete() {
        stressCopingListItems.value?.forEach { stressCopingListItem ->
            if (stressCopingListItem.type == StressCopingListItemModel.Type.Body) {
                stressCopingListItem.isVisibleCheckBox = true
                stressCopingListItem.isVisibleEdit = false
                stressCopingListItem.isVisibleDelete = false
            }
        }
        _stressCopingListState.postValue(StressCopingListState.Delete)
        _refreshRecyclerViewAdapter.postValue(Unit)
        _listState.postValue(StressCopingListState.Delete)
    }

    fun onLongClickItem(stressCopingListItemModel: StressCopingListItemModel) {
        stressCopingListItemModel.isCheck = true
        changeListStateToDelete()
    }

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

    override fun deleteStressCopings() {
        val deletedItems = stressCopingListItems.value?.filter { it.isCheck }
        if (deletedItems != null) {
            val stressCopings = deletedItems.map { it.stressCoping }.filterNotNull().toTypedArray()
            viewModelScope.launch(Dispatchers.IO) {
                repository.deletes(*stressCopings)
                changeListStateColumn()
            }
        }
    }
}
