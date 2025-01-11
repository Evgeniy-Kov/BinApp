package com.example.binapp.ui.history

sealed interface HistoryScreenState {
    data object Content : HistoryScreenState
    data object Empty : HistoryScreenState
}