package com.example.animehub.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animehub.core.NetworkMonitor
import com.example.animehub.data.remote.dto.TopAnimeResponse
import com.example.animehub.domain.model.Anime
import com.example.animehub.domain.model.Resource
import com.example.animehub.domain.repository.AnimeHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeHubViewModel @Inject constructor(
    private val repository: AnimeHubRepository,
    private val networkMonitor: NetworkMonitor
): ViewModel() {

    // UI data state
    val uiState: StateFlow<Resource<List<Anime>>> =
        repository.getTopAnime()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = Resource.Loading
            )

    // Network state
    val networkState: StateFlow<Boolean> =
        networkMonitor.isOnline
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                true
            )

}