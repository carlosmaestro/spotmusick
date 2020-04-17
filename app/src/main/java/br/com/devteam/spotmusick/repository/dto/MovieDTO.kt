package br.com.devteam.spotmusick.repository.dto

import com.google.gson.annotations.SerializedName

data class MovieDTO(
    val id: Int? = null,

        @SerializedName("poster_path")
    val poster: String? = null,

    val title: String? = null,
    val overview: String? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null,

    val adult: Boolean? = null,

    @SerializedName("vote_average")
    val voteAverage: Float? = null
)

data class MovieListDTO(
    val page: Int? = null,

    @SerializedName("total_results")
    val totalResults: Int? = null,

    @SerializedName("total_pages")
    val totalPages: Int? = null,

    val results: Array<MovieDTO>? = null
)