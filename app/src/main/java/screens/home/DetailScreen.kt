package screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movies.ui.theme.MoviesTheme
import com.example.movies.widgets.FavoriteIcon
import com.example.movies.widgets.HorizontalScrollableImageView
import com.example.movies.widgets.MovieRow
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies
import models.FavoritesViewModel

@Composable
fun DetailScreen(navController: NavController = rememberNavController(), movieId: String?, viewModel: FavoritesViewModel) {

    val movie = filterMovie(movieId = movieId)

    Scaffold(
        topBar = {
            TopAppBar(elevation = 3.dp ) {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()    // go back to last screen
                        })

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(text = movie.title, style = MaterialTheme.typography.h6)
                }

            }
        }
    ) {
        MainContent(movie = movie, viewmodel = viewModel)
    }

}

@Composable
fun MainContent(movie: Movie, viewmodel: FavoritesViewModel) {

    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {

        Column {
            MovieRow(
                movie = movie,
                content = {
                    FavoriteIcon(movie = movie, favorite = viewmodel.checkMovie(movie),
                        onFavClick = { favmovie ->
                            if (!viewmodel.checkMovie(favmovie)) {
                                viewmodel.addMovie(favmovie)

                            } else {
                                viewmodel.removeMovie(favmovie)

                            }

                        })
                }
            )

            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            Text(text = "Movie Images", style = MaterialTheme.typography.h5, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())

            HorizontalScrollableImageView(movie = movie)

        }
    }


}


fun filterMovie(movieId: String?) : Movie {
    return getMovies().filter { movie -> movie.id == movieId }[0]
}