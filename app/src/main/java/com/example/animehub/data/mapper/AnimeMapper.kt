package com.example.animehub.data.mapper

import com.example.animehub.data.local.entity.AnimeEntity
import com.example.animehub.data.remote.dto.AnimeDetailDto
import com.example.animehub.domain.model.Anime

object AnimeMapper {

    fun AnimeDetailDto.toEntity(): AnimeEntity =
        AnimeEntity(
            id = id,
            title = title,
            episodes = episodes,
            rating = score,
            posterUrl = images.jpg.imageUrl,
            synopsis = synopsis,
            genres = genres.joinToString(", ") { it.name },
            cast = characters?.joinToString(", ") { it.name },
            trailerUrl = trailer?.embedUrl
        )

    fun AnimeEntity.toDetailDomain(): Anime =
        Anime(
            id = id,
            title = title,
            synopsis = synopsis ?: "No description available",
            genres = genres ?: "Unknown",
            cast = cast ?: "Unknown",
            episodes = episodes?.toString() ?: "N/A",
            rating = rating?.toString() ?: "N/A",
            trailerUrl = trailerUrl,
            posterUrl = posterUrl
        )

}