package com.example.jetpackcompose.screen

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movies.ui.theme.MoviesTheme
import com.example.movies.widgets.MovieRow
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(navController: NavController = rememberNavController()) {

    var showFavorites by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Homescreen") },
                actions = {
                    IconButton(onClick = { showFavorites = !showFavorites }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }

                    DropdownMenu(
                        expanded = showFavorites,
                        onDismissRequest = { showFavorites = false }) {
                        DropdownMenuItem(onClick = { navController.navigate(route = "FavoritesScreen") }) {
                            Row() {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "my favorites",
                                    modifier = Modifier.padding(4.dp)
                                )
                                Text(
                                    text = "Favorites",
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .width(100.dp)
                                )

                            }
                        }
                    }
                }
            )
        }
    ) {
        MainContent(navController = navController)
    }
}

@ExperimentalAnimationApi
@Composable
fun MainContent(navController: NavController, movieList: List<Movie> = getMovies()) {

    LazyColumn {
        items(movieList) { name ->
            MovieRow(name) { movieId ->
                //Log.d("MainContent","My callback value: $movieId")
                navController.navigate(route = "detailscreen/$movieId")

            }
        }
    }
}