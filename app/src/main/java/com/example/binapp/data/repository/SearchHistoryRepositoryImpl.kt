package com.example.binapp.data.repository

import com.example.binapp.data.db.AppDatabase
import com.example.binapp.data.db.converter.BinInfoDbConverter
import com.example.binapp.data.db.entity.BinInfoEntity
import com.example.binapp.domain.api.SearchHistoryRepository
import com.example.binapp.domain.model.BinInfo
import kotlinx.coroutines.flow.flow

class SearchHistoryRepositoryImpl(
    private val database: AppDatabase,
    private val converter: BinInfoDbConverter
) : SearchHistoryRepository {
    override suspend fun insertBinInfo(binInfo: BinInfo) {
        database.binInfoDao().insertBinInfo(converter.map(binInfo))
    }

    override suspend fun deleteBinInfo(binInfo: BinInfo) {
        database.binInfoDao().deleteBinInfo(converter.map(binInfo))
    }

    override fun getAllBinInfo() = flow {
        database.binInfoDao().getAllBinInfo().collect { entities ->
            emit(convertFromBinInfoEntity(entities))
        }
    }

    private fun convertFromBinInfoEntity(entities: List<BinInfoEntity>): List<BinInfo> {
        return entities.map { entity -> converter.map(entity) }
    }
}