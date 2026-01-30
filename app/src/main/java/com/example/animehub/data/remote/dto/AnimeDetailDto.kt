package com.example.animehub.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AnimeDetailResponse(
    val data: AnimeDetailDto
)

data class AnimeDetailDto(
    @SerializedName("mal_id")
    val id: Int,

    val title: String,

    val synopsis: String?,

    val episodes: Int?,

    val score: Double?,

    val genres: List<GenreDto>,

    val trailer: TrailerDto?,

    val images: ImagesDto,

    val characters: List<CharacterDto>?
)

data class ImagesDto(
    val jpg: JpgDto
)

data class JpgDto(
    @SerializedName("large_image_url")
    val imageUrl: String?
)

data class GenreDto(
    val name: String
)

data class TrailerDto(
    @SerializedName("embed_url")
    val embedUrl: String?
)

data class CharacterDto(
    val name: String
)

