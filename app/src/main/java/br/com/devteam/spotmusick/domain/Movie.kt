package br.com.devteam.spotmusick.domain

data class Movie(
    val id: Int? = null,
    val poster: String? = null,
    val title: String? = null,
    val overview: String? = null,
    val releaseDate: String? = null
)

data class MovieList(
    val page: Int? = null,
    val totalResults: Int? = null,
    val totalPages: Int? = null,
    val results: Array<Movie>? = null
)