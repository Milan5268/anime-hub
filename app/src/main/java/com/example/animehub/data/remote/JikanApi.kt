package com.example.animehub.data.remote

import com.example.animehub.data.remote.dto.AnimeDetailResponse
import com.example.animehub.data.remote.dto.TopAnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface JikanApi {

    companion object {
        const val ANIME_DETAIL = "v4/anime/{id}"
        const val ANIME_TOP_LIST = "v4/top/anime"
    }

    @GET(ANIME_TOP_LIST)
    suspend fun getTopAnime(): TopAnimeResponse

    @GET(ANIME_DETAIL)
    suspend fun getAnimeDetail(
        @Path("id") id: Int
    ): AnimeDetailResponse
}