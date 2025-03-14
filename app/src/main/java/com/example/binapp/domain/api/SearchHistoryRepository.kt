package com.example.binapp.domain.api

import com.example.binapp.domain.model.BinInfo
import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {
    suspend fun insertBinInfo(binInfo: BinInfo)

    suspend fun deleteBinInfo(binInfo: BinInfo)

    fun getAllBinInfo(): Flow<List<BinInfo>>
}