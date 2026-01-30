package com.example.animehub.data.mapper

import com.example.animehub.data.local.entity.AnimeEntity
import com.example.animehub.data.remote.dto.AnimeDetailDto
import com.example.animehub.data.remote.dto.AnimeDto
import com.example.animehub.domain.model.Anime
import com.example.animehub.domain.model.AnimeDetail

object AnimeMapper {
    fun AnimeDto.toEntity(): AnimeEntity =
        AnimeEntity(
            id = id,
            title = title,
            episodes = episodes,
            rating = score,
            posterUrl = images.jpg.imageUrl
        )

    fun AnimeEntity.toDomain(): Anime =
        Anime(
            id = id,
            title = title,
            episodesText = episodes?.toString() ?: "N/A",
            ratingText = rating?.toString() ?: "N/A",
            posterUrl = posterUrl
        )

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

    fun AnimeEntity.toDetailDomain(): AnimeDetail =
        AnimeDetail(
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