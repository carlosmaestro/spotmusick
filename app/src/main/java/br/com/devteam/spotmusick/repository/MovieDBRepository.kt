package br.com.devteam.spotmusick.repository

import android.content.Context
import br.com.devteam.spotmusick.domain.Movie
import br.com.devteam.spotmusick.repository.dto.MovieDTO
import br.com.devteam.spotmusick.repository.dto.MovieListDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Interface que define os Endpoints (endereços relativos a uma URL Base) no Retrofit
interface MovieDBService {

    // Cara Endpoint precisa ser configura de acordo com o método HTTP do servidor
    // No caso da API The Movie DB o endpoint para descoberta de filmes é do tipo GET
    //
    // O Retorno Call do método é um tipo de objeto "Operação" que será executado quando
    // o Método ENQUEUE for acionado no Retrofit
    //
    @GET("discover/movie")
    fun discovery(
        @Query("api_key") apiKey: String = "XXXXXXX",
        @Query("language") language: String = "pt-BR"
    ): Call<MovieListDTO>

    @GET("movie")
    fun movieDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = "XXXXXXXX",
        @Query("language") language: String = "pt-BR"
    ): Call<MovieDTO>

}

class MovieDBRepository(context: Context, baseUrl: String) : BaseRetrofit(context, baseUrl) {
    private val service = retrofit.create(MovieDBService::class.java)

    fun discovery(callback: (movies: Array<Movie>) -> Unit) {

        // No Retrofit as chamadas são feitas através de um "Service" que contém as definições
        // da requisição HTTP.
        // A conexão inicia quando o método ENQUEUE é chamado
        service.discovery().enqueue(object : Callback<MovieListDTO> {

            // As chamadas do Retrofit são ASSÍNCRONAS e quando terminam a conexão com o
            // servidor chamado executam duas funções:
            // onResponse caso a conexão tenha retornado algum resultado 200 ou equivalente (SUCESSO)
            // onFailure caso a conexão tenha retornado algum erro na chamada 400 ou superior na tabela
            //           de erros HTTP (https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status)
            override fun onResponse(call: Call<MovieListDTO>, response: Response<MovieListDTO>) {
                val result = mutableListOf<Movie>()
                val movies = response.body()?.results

                // Percorre a lista para converter os objetos DTO (MovieDTO) para Domínio (Movie)
                movies?.forEach { m ->

                    // Converte o DTO (Data Transfer Object para um objeto de Domínio de Negócio
                    val domain = Movie(
                        id = m.id,
                        title = m.title,
                        overview = m.overview,
                        poster = m.poster,
                        releaseDate = m.releaseDate
                    )

                    result.add(domain)
                }

                // Chama a função de retorno uma vez que a chamada do Retrofit é ASSÍNCRONA
                callback(result.toTypedArray())
            }

            override fun onFailure(call: Call<MovieListDTO>, error: Throwable) {
                // Caso algum problema ocorra durante a conexão retorna uma lista de filmes vazia
                callback(arrayOf())
            }
        })
    }

    fun movieDetail(movieId: Int, callback: (movies: Movie) -> Unit) {
        service.movieDetail(movieId).enqueue(object : Callback<MovieDTO> {
            override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
                val movie = response.body()
                val domain = Movie(
                    id = movie?.id,
                    title = movie?.title,
                    overview = movie?.overview,
                    poster = movie?.poster,
                    releaseDate = movie?.releaseDate
                )
                callback(domain)
            }

            override fun onFailure(call: Call<MovieDTO>, error: Throwable) {
                callback(Movie())
            }
        })
    }

}

