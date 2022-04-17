package models

import androidx.lifecycle.ViewModel
import com.example.testapp.models.Movie

class FavoritesViewModel : ViewModel() {

    private val favmovies = mutableListOf<Movie>()
    val favorite: List<Movie>
        get() = favmovies

    fun addMovie(movie: Movie){
        favmovies.add(movie)
    }

    fun removeMovie(movie: Movie){
        favmovies.remove(movie)
    }

    fun checkMovie(movie: Movie): Boolean{
        return favmovies.contains(movie)
    }
}