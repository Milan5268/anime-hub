package com.example.animehub.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeEntity(

    @PrimaryKey
    val id: Int,

    val title: String,

    val episodes: Int?,

    val rating: Double?,

    val posterUrl: String?,

    val synopsis: String? = null,

    val genres: String? = null,

    val cast: String? = null,

    val trailerUrl: String? = null
)
