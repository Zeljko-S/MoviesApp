package com.example.movies.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(
    movie: Movie,
    onItemClick: (String) -> Unit = {},
    content: @Composable () -> Unit = {},

) {

    var arrow by remember { mutableStateOf(false) }
    var visible by remember { mutableStateOf(false) }


    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(
                if (!visible) 130.dp
                else 350.dp
            )
            .animateContentSize()
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp

    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
            ) {

                AsyncImage(
                    model = movie.images[0],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )

            }

            Column(

            ) {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}")
                Text(text = "Released: ${movie.year}")

                Surface(
                    modifier = Modifier
                        .clickable { arrow = !arrow; visible = !visible }
                        .padding(10.dp)) {

                    if (!arrow) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "arrowup"
                        )
                    } else {

                        AnimatedVisibility(
                            visible = visible,

                            )
                        {
                            Column {

                                Text(text = "Plot: ${movie.plot}")
                                Divider()
                                Text(text = "Genre: ${movie.genre}")
                                Text(text = "Actors: ${movie.actors}")
                                Text(text = "Rating: ${movie.rating}")

                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "arrowdown"
                                )
                            }
                        }


                    }
                }
            }
            content()
        }
    }
}
@Composable
fun FavoriteIcon(movie: Movie, onFavClick: (Movie) -> Unit = {}, favorite: Boolean) {

    Surface(
        modifier = Modifier
            .padding(10.dp)
    ) {

        Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = {
                onFavClick(movie)
            }) {
                if (!favorite) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorites",
                        tint = Color.Cyan
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorites",
                        tint = Color.Cyan
                    )
                }
            }

        }

    }
}


@Composable
fun HorizontalScrollableImageView(movie: Movie = getMovies()[0]) {
    LazyRow{
        items(movie.images){ image ->

            Card(modifier = Modifier
                .padding(12.dp)
                .size(240.dp),
                elevation = 4.dp) {

                AsyncImage(
                    model = image,
                    contentDescription = "movie image"
                )
            }

        }
    }
}