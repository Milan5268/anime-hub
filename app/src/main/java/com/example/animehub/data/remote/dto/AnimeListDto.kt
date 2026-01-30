package com.example.animehub.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TopAnimeResponse(
    val data: List<AnimeDto>
)

data class AnimeDto(
    @SerializedName("mal_id")
    val id: Int,

    val title: String,

    val episodes: Int?,

    val score: Double?,

    val images: ImagesDto
)

data class ImagesDto(
    val jpg: JpgDto
)

data class JpgDto(
    @SerializedName("large_image_url")
    val imageUrl: String?
)

