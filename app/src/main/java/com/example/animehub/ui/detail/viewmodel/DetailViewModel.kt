package com.example.animehub.ui.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animehub.domain.model.Anime
import com.example.animehub.domain.model.AnimeDetail
import com.example.animehub.domain.model.Resource
import com.example.animehub.domain.repository.AnimeHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    repository: AnimeHubRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val animeId: Int = checkNotNull(
        savedStateHandle["animeId"]
    )

    val uiState: StateFlow<Resource<AnimeDetail>> =
        repository.getAnimeDetail(animeId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = Resource.Loading
            )
}
