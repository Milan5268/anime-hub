package com.example.animehub.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.animehub.navigation.Screen
import com.example.animehub.ui.detail.DetailScreen
import com.example.animehub.ui.home.HomeScreen

@Composable
fun AnimeNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(
                onAnimeClick = { animeId ->
                    navController.navigate(
                        Screen.Detail.createRoute(animeId)
                    )
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("animeId") {
                    type = NavType.IntType
                }
            )
        ) {
            DetailScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
