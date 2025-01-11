package com.example.binapp.domain.api

import com.example.binapp.domain.model.BinInfo
import com.example.binapp.domain.model.Resource

interface SearchBinInfoRepository {
    suspend fun searchBinInfo(number: String): Resource<BinInfo>
}