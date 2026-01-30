package com.example.animehub.data.repository

import com.example.animehub.core.NetworkMonitor
import com.example.animehub.core.network.JikanApi
import com.example.animehub.data.local.dao.AnimeDao
import com.example.animehub.data.mapper.AnimeMapper.toDetailDomain
import com.example.animehub.data.mapper.AnimeMapper.toDomain
import com.example.animehub.data.mapper.AnimeMapper.toEntity
import com.example.animehub.domain.model.Anime
import com.example.animehub.domain.model.AnimeDetail
import com.example.animehub.domain.model.Resource
import com.example.animehub.domain.repository.AnimeHubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class AnimeHubRepositoryImpl @Inject constructor (
    private val apiService: JikanApi,
    private val dao: AnimeDao,
    private val networkMonitor: NetworkMonitor
): AnimeHubRepository {
    override fun getTopAnime(): Flow<Resource<List<Anime>>> {
        val localFlow =
            dao.observeAllAnime()
                .map { entities ->
                    Resource.Success(entities.map { it.toDomain() }) as Resource<List<Anime>>
                }

        val refreshFlow =
            networkMonitor.isOnline
                .filter { it } // only when ONLINE
                .onEach {
                    runCatching {
                        apiService.getTopAnime()
                    }.onSuccess { response ->
                        dao.insertAnimeList(response.data.map { it.toEntity() })
                    }
                }
                .map { Resource.Loading as Resource<List<Anime>> }

        return merge(
            localFlow,
            refreshFlow
        )
    }

    override fun getAnimeDetail(animeId: Int): Flow<Resource<AnimeDetail>> {

        val localFlow =
            dao.observeAnimeById(animeId)
                .filterNotNull()
                .map { entity ->
                    Resource.Success(entity.toDetailDomain()) as Resource<AnimeDetail>
                }

        val refreshFlow =
            networkMonitor.isOnline
                .filter { it } // only when ONLINE
                .onEach {
                    runCatching {
                        apiService.getAnimeDetail(animeId)
                    }.onSuccess { response ->
                        dao.insertAnime(response.data.toEntity())
                    }
                }
                .map { Resource.Loading as Resource<AnimeDetail> }

        return merge(
            localFlow,
            refreshFlow
        )
    }

}