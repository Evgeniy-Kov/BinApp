package com.example.binapp.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binapp.domain.api.SearchHistoryInteractor
import com.example.binapp.domain.model.BinInfo
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val searchHistoryInteractor: SearchHistoryInteractor,
) : ViewModel() {

    private val _screenState = MutableLiveData<HistoryScreenState>()
    val screenState: LiveData<HistoryScreenState>
        get() = _screenState

    private val _data = MutableLiveData<List<BinInfo>>()
    val data: LiveData<List<BinInfo>>
        get() = _data

    init {
        getSearchHistory()
    }

    fun getSearchHistory() {
        viewModelScope.launch {

            searchHistoryInteractor.getAllBinInfo().collect {
                _data.value = it
                _screenState.value = if (it.isEmpty()) {
                    HistoryScreenState.Empty
                } else {
                    HistoryScreenState.Content
                }
            }
        }
    }

    fun deleteBinInfoFromHistory(binInfo: BinInfo) {
        viewModelScope.launch {
            searchHistoryInteractor.deleteBinInfo(binInfo)
        }
    }

}