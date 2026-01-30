package com.example.animehub.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.animehub.domain.model.Anime
import com.example.animehub.domain.model.Resource
import com.example.animehub.ui.home.viewmodel.AnimeHubViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: AnimeHubViewModel = hiltViewModel(),
    onAnimeClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isOnline by viewModel.networkState.collectAsStateWithLifecycle()

    val snackBarHostState = remember { SnackbarHostState() }

    // Track previous network state
    var previousNetworkState by remember { mutableStateOf(isOnline) }

    // React ONLY when network state changes
    LaunchedEffect(isOnline) {
        if (isOnline != previousNetworkState) {
            if (!isOnline) {
                snackBarHostState.showSnackbar(
                    message = "You're offline",
                    duration = SnackbarDuration.Short
                )
            } else {
                snackBarHostState.showSnackbar(
                    message = "Back online",
                    duration = SnackbarDuration.Short
                )
            }
            previousNetworkState = isOnline
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            TopAppBar(title = { Text("AnimeHub") })
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when (uiState) {
                is Resource.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is Resource.Success -> {
                    AnimeList(
                        animeList = (uiState as Resource.Success<List<Anime>>).data,
                        onAnimeClick = onAnimeClick
                    )
                }

                is Resource.Error -> {
                    Text(
                        text = (uiState as Resource.Error).message,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(
        viewModel = hiltViewModel(),
        onAnimeClick = {}
    )
}

