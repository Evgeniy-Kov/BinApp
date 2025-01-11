package com.example.binapp.di

import com.example.binapp.domain.api.SearchBinInfoInteractor
import com.example.binapp.domain.api.SearchHistoryInteractor
import com.example.binapp.domain.impl.SearchBinInfoInteractorImpl
import com.example.binapp.domain.impl.SearchHistoryInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    factory<SearchBinInfoInteractor> {
        SearchBinInfoInteractorImpl(get())
    }

    factory<SearchHistoryInteractor> {
        SearchHistoryInteractorImpl(get())
    }
}