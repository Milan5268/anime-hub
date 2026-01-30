package com.example.animehub.domain.model

data class AnimeDetail(
    val title: String,
    val synopsis: String,
    val genres: String,
    val cast: String,
    val episodes: String,
    val rating: String,
    val trailerUrl: String?,
    val posterUrl: String?
)

