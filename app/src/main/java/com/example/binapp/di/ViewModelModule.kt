package com.example.binapp.di

import com.example.binapp.ui.history.HistoryViewModel
import com.example.binapp.ui.search.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        SearchViewModel(get(), get())
    }

    viewModel {
        HistoryViewModel(get())
    }
}