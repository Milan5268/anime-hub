package com.example.animehub.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.animehub.domain.model.Anime
import com.example.animehub.core.util.YouTubeUtil.extractYoutubeId
import com.example.animehub.core.util.YouTubeUtil.openYouTube

@Composable
fun AnimeDetailContent(
    anime: Anime,
    isOnline: Boolean,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Trailer OR Poster
        item {
            MediaSection(
                trailerUrl = anime.trailerUrl,
                posterUrl = anime.posterUrl,
                isOnline = isOnline
            )
        }

        // Title
        item {
            Text(
                text = anime.title,
                style = MaterialTheme.typography.headlineSmall
            )
        }

        // Rating & Episodes
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoChip(label = "⭐ Rating", value = anime.rating)
                InfoChip(label = "🎬 Episodes", value = anime.episodes)
            }
        }

        // Synopsis
        item {
            Section(title = "Synopsis", content = anime.synopsis)
        }

        // Genres
        item {
            Section(title = "Genres", content = anime.genres)
        }

        // Cast
        item {
            Section(title = "Main Cast", content = anime.cast)
        }
    }
}

@Composable
fun MediaSection(
    trailerUrl: String?,
    posterUrl: String?,
    isOnline: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        TrailerSection(trailerUrl, posterUrl, isOnline)
    }
}

@Composable
fun TrailerSection(
    youtubeUrl: String?,
    posterUrl: String?,
    isOnline: Boolean
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        PosterImage(posterUrl)

        if (youtubeUrl != null && isOnline) {
            IconButton(
                onClick = {
                    openYouTube(context, youtubeId = extractYoutubeId(youtubeUrl))
                },
                modifier = Modifier.align(Alignment.Center)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play Trailer",
                    tint = Color.White,
                    modifier = Modifier.size(64.dp)
                )
            }
        }
    }
}

@Composable
fun PosterImage(
    posterUrl: String?
) {
    if (posterUrl != null) {
        AsyncImage(
            model = posterUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No Preview",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun InfoChip(
    label: String,
    value: String
) {
    Column {
        Text(text = label, style = MaterialTheme.typography.labelSmall)
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun Section(
    title: String,
    content: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}





