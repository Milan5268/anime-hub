package com.example.animehub.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animehub.data.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    //LIST
    @Query("SELECT * FROM anime ORDER BY rating DESC")
    fun observeAllAnime(): Flow<List<AnimeEntity>>

    //DETAIL
    @Query("SELECT * FROM anime WHERE id = :animeId")
    fun observeAnimeById(animeId: Int): Flow<AnimeEntity?>

    //INSERT / UPDATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeList(animeList: List<AnimeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeEntity)
}
