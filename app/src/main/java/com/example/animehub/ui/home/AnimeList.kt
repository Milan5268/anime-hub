package com.example.animehub.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.animehub.domain.model.Anime

@Composable
fun AnimeList(
    animeList: List<Anime>,
    onAnimeClick: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(animeList, key = { it.id }) { anime ->
            AnimeCard(
                anime = anime,
                onClick = { onAnimeClick(anime.id) }
            )
        }
    }
}
