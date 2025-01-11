package com.example.binapp.di

import com.example.binapp.data.repository.SearchBinInfoRepositoryImpl
import com.example.binapp.domain.api.SearchBinInfoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<SearchBinInfoRepository> {
        SearchBinInfoRepositoryImpl(get())
    }
}