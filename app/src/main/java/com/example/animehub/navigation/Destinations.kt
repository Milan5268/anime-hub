package com.example.animehub.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")

    object Detail : Screen("detail/{animeId}") {
        fun createRoute(animeId: Int): String =
            "detail/$animeId"
    }
}