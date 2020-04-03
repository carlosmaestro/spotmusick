package br.com.devteam.spotmusick.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.devteam.spotmusick.domain.Movie
import br.com.devteam.spotmusick.interactor.MovieInteractor

// A responsabilidade de uma classe ViewModel é preparar os dados para apresentação (transformações,
// formatação de dados, etc)
//
class MovieViewModel(val app: Application) : AndroidViewModel(app) {
    private val interactor = MovieInteractor(app.applicationContext)

    // Os objetos do Tipo LiveData no Android são tipos que possibilitam o conceito de "Publish/Subscribe",
    // ou seja, as variáveis podem ter "funções ouvintes" que são acionadas sempre que o valor da
    // variável for modificado
    val result = MutableLiveData<Array<Movie>>()

    fun discovery() {
        interactor.discovery { movies ->
            val moviesWithImageURL = mutableListOf<Movie>()

            // Pelo fato da responsabilidade de transformar um dado para apresentação ser das "ViewModels"
            // as linha abaixo modificam os dados antes de serem apresentados para o usuário.
            // Neste caso será acrecentado a URL Base da API The Movie DB para recuperar imagens
            // de um filme criando um novo objeto Movie com essa modificação
            movies.forEach { m ->
                val newMovie = Movie(
                    id = m.id,
                    releaseDate = m.releaseDate,
                    poster = "https://image.tmdb.org/t/p/w500${m.poster}",
                    overview = m.overview,
                    title =  m.title
                )
                moviesWithImageURL.add(newMovie)
            }

            result.value = moviesWithImageURL.toTypedArray()
        }
    }

}