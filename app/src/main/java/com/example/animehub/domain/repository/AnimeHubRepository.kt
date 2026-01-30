package com.example.animehub.domain.repository

import com.example.animehub.data.remote.dto.TopAnimeResponse
import com.example.animehub.domain.model.Anime
import com.example.animehub.domain.model.AnimeDetail
import com.example.animehub.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface AnimeHubRepository {
    fun getTopAnime(): Flow<Resource<List<Anime>>>
    fun getAnimeDetail(animeId: Int): Flow<Resource<AnimeDetail>>
}