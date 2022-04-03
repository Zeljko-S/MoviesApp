package com.example.movies.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcompose.screen.HomeScreen
import screens.home.DetailScreen
import screens.home.favorites


@Composable
fun MovieNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "homescreen") {
        composable("homescreen") { HomeScreen(navController = navController) }
        composable(route = "FavoritesScreen") {
            favorites(navController = navController)

        }
        composable(
            route = "detailscreen/{movieId}", arguments = listOf(navArgument("movieId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(
                navController = navController,
                movieId = backStackEntry.arguments?.getString("movieId")
            )
        }
    }


}



