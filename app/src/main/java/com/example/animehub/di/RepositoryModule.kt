package com.example.animehub.di

import com.example.animehub.core.NetworkMonitor
import com.example.animehub.core.network.JikanApi
import com.example.animehub.data.local.dao.AnimeDao
import com.example.animehub.data.repository.AnimeHubRepositoryImpl
import com.example.animehub.domain.repository.AnimeHubRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAnimeHubRepository(
        apiService: JikanApi,
        animeDao: AnimeDao,
        networkMonitor: NetworkMonitor
    ): AnimeHubRepository {
        return AnimeHubRepositoryImpl(apiService, animeDao, networkMonitor)
    }
}