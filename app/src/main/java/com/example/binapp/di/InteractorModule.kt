package com.example.binapp.di

import com.example.binapp.domain.api.SearchBinInfoInteractor
import com.example.binapp.domain.impl.SearchBinInfoInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    factory<SearchBinInfoInteractor> {
        SearchBinInfoInteractorImpl(get())
    }
}