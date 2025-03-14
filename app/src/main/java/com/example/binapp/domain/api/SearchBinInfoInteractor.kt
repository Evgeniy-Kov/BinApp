package com.example.binapp.domain.api

import com.example.binapp.domain.model.BinInfo
import com.example.binapp.domain.model.Resource

interface SearchBinInfoInteractor {
    suspend fun searchBinInfo(number: String): Resource<BinInfo>
}