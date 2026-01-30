package com.example.animehub.domain.model

data class Anime(
    val id: Int,
    val title: String,
    val episodesText: String,
    val ratingText: String,
    val posterUrl: String?
)
