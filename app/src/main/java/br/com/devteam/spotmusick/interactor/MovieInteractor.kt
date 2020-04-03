package br.com.devteam.spotmusick.interactor

import android.content.Context
import br.com.devteam.spotmusick.R
import br.com.devteam.spotmusick.domain.Movie
import br.com.devteam.spotmusick.repository.MovieDBRepository

// Classes do tipo Interactor são criadas para conter Regras de Negócio.
//
class MovieInteractor(private val context: Context) {
    private val movieRepository = MovieDBRepository(context, context.getString(R.string.movie_db_base_url))

    fun getMovieDetail(id: Int, callback: (movies: Movie) -> Unit) {
        if (id < 0) {
            throw Exception(context.getString(R.string.invalid_movie_id))
        }

        movieRepository.movieDetail(id, callback)
    }

    fun discovery(callback: (movies: Array<Movie>) -> Unit) {
        movieRepository.discovery(callback)
    }
}