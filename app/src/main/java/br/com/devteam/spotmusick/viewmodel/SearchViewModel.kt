package br.com.devteam.spotmusick.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.devteam.spotmusick.domain.PagingObject
import br.com.devteam.spotmusick.domain.Track
import br.com.devteam.spotmusick.interactor.SearchInteractor
import br.com.devteam.spotmusick.repository.dto.TrackDTO

class SearchViewModel(val app: Application) : AndroidViewModel(app) {
    private val interactor = SearchInteractor(app.applicationContext)

    val result = MutableLiveData<List<Track>>()

    fun discovery(query: String) {
        interactor.discovery(query) {
            if (it != null) {
//                callback(it.items!!)
                result.value = it.items
            }
        }
    }
}