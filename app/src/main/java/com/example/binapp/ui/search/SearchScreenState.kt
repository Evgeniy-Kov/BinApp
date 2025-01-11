package com.example.binapp.ui.search

import com.example.binapp.domain.model.BinInfo

sealed class SearchScreenState(
    val data: BinInfo?,
    val errorMessage: String?
) {
    data object Loading : SearchScreenState(null, null)
    data class Content(val content: BinInfo) : SearchScreenState(content, null)
    data class Error(val message: String) : SearchScreenState(null, message)
}