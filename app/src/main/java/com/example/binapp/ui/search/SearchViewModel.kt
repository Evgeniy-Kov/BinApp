package com.example.binapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binapp.domain.api.SearchBinInfoInteractor
import com.example.binapp.domain.api.SearchHistoryInteractor
import com.example.binapp.domain.model.BinInfo
import com.example.binapp.domain.model.Resource
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchInteractor: SearchBinInfoInteractor,
    private val searchHistoryInteractor: SearchHistoryInteractor,
) : ViewModel() {

    private val _screenState = MutableLiveData<SearchScreenState>()
    val screenState: LiveData<SearchScreenState>
        get() = _screenState

    fun getBinInfo(number: String) {
        viewModelScope.launch {
            val result = searchInteractor.searchBinInfo(number)
            successResult(result)
        }
    }

    private fun saveToHistory(binInfo: BinInfo) {
        viewModelScope.launch {
            searchHistoryInteractor.insertBinInfo(binInfo)
        }
    }

    private fun successResult(resource: Resource<BinInfo>) {
        when (resource) {
            is Resource.Success -> {
                saveToHistory(resource.data)
                _screenState.value = SearchScreenState.Content(resource.data)
            }

            is Resource.Error -> {
                _screenState.value = SearchScreenState.Error(resource.message)
            }
        }
    }
}