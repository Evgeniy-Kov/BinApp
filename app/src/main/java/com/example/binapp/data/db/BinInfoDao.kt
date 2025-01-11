package com.example.binapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.binapp.data.db.entity.BinInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BinInfoDao {

    @Insert(entity = BinInfoEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBinInfo(binInfo: BinInfoEntity)

    @Delete
    suspend fun deleteBinInfo(binInfo: BinInfoEntity)

    @Query("SELECT * FROM bin_info_table")
    fun getAllBinInfo(): Flow<List<BinInfoEntity>>
}