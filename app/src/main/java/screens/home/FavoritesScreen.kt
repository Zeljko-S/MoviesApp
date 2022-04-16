package screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movies.widgets.MovieRow
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies
import models.FavoritesViewModel

@Composable
fun FavoritesScreen(navController: NavController = rememberNavController(), viewModel: FavoritesViewModel = viewModel()) {

    val movies = viewModel.favorite

    Scaffold(
        topBar = {
            TopAppBar(elevation = 3.dp ) {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(text = "My Favorite Movies", style = MaterialTheme.typography.h6)
                }

            }
        }
    ) {
        MainContent(movies = movies, navController = navController, viewModel = viewModel)
    }
}

@Composable
fun MainContent(movies: List<Movie>, navController: NavController, viewModel: FavoritesViewModel) {


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        LazyColumn {
            items(movies) { name ->
                MovieRow(name,
                    onItemClick = { movieId ->
                        //Log.d("MainContent","My callback value: $movieId")
                        navController.navigate(route = "detailscreen/$movieId")
                    }

                )
            }
        }
    }

}