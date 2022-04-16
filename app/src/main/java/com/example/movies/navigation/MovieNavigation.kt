package com.example.movies.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcompose.screen.HomeScreen
import models.FavoritesViewModel
import screens.home.DetailScreen
import screens.home.FavoritesScreen




@Composable
fun MovieNavigation() {

    val navController = rememberNavController()
    val FavoritesViewModel: FavoritesViewModel = viewModel()

    NavHost(navController = navController, startDestination = "homescreen") {
        composable("homescreen") { HomeScreen(navController = navController, viewModel = FavoritesViewModel) }

        composable(
            route = "detailscreen/{movieId}", arguments = listOf(navArgument("movieId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(navController = navController, movieId = backStackEntry.arguments?.getString("movieId"), viewModel = FavoritesViewModel

            )

        }
        composable(route = "FavoritesScreen") {
            FavoritesScreen(navController = navController, viewModel = FavoritesViewModel)

        }
    }


}



