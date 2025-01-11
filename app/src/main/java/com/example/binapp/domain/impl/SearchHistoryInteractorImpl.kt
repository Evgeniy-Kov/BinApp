package com.example.binapp.domain.impl

import com.example.binapp.domain.api.SearchHistoryInteractor
import com.example.binapp.domain.api.SearchHistoryRepository
import com.example.binapp.domain.model.BinInfo
import kotlinx.coroutines.flow.Flow

class SearchHistoryInteractorImpl(
    private val repository: SearchHistoryRepository
) : SearchHistoryInteractor {
    override suspend fun insertBinInfo(binInfo: BinInfo) {
        repository.insertBinInfo(binInfo)
    }

    override suspend fun deleteBinInfo(binInfo: BinInfo) {
        repository.deleteBinInfo(binInfo)
    }

    override fun getAllBinInfo(): Flow<List<BinInfo>> {
        return repository.getAllBinInfo()
    }
}