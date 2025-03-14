package com.example.binapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.binapp.data.db.entity.BinInfoEntity

@Database(version = 1, entities = [BinInfoEntity::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun binInfoDao(): BinInfoDao
}