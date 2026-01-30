package com.example.animehub.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.animehub.data.local.entity.AnimeEntity

@Database(
    entities = [AnimeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AnimeDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao
}
