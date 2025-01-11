package com.example.binapp.domain.impl

import com.example.binapp.domain.api.SearchBinInfoInteractor
import com.example.binapp.domain.api.SearchBinInfoRepository
import com.example.binapp.domain.model.BinInfo
import com.example.binapp.domain.model.Resource

class SearchBinInfoInteractorImpl(
    private val repository: SearchBinInfoRepository
) : SearchBinInfoInteractor {
    override suspend fun searchBinInfo(number: String): Resource<BinInfo> {
        return repository.searchBinInfo(number)
    }
}