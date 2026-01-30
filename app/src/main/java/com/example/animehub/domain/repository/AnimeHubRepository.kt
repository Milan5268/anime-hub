package com.example.animehub.domain.repository

import com.example.animehub.domain.model.Anime
import com.example.animehub.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface AnimeHubRepository {
    fun getTopAnime(): Flow<Resource<List<Anime>>>
    fun getAnimeDetail(animeId: Int): Flow<Resource<Anime>>
}