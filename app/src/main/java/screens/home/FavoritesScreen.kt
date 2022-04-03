package screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movies.widgets.MovieRow
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies

@Composable
fun favorites(navController: NavController = rememberNavController()) {

    val favs = filterFavorites()

    Scaffold(
        topBar = {
            TopAppBar(elevation = 3.dp) {
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
        MainContent(navController = navController, favs)
    }
}

@Composable
fun MainContent(navController: NavController, movieList: List<Movie>) {

    LazyColumn {
        items(movieList) { name ->
            MovieRow(name) { movieId ->
                navController.navigate(route = "detailscreen/$movieId")

            }
        }
    }
}
fun filterFavorites(): List<Movie> {
    return getMovies().take(2)
}