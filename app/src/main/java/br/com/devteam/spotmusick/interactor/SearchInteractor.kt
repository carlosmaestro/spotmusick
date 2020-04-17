package br.com.devteam.spotmusick.interactor

import android.content.Context
import br.com.devteam.spotmusick.domain.PagingObject
import br.com.devteam.spotmusick.domain.Track
import br.com.devteam.spotmusick.repository.SearchRepository

class SearchInteractor(private val context: Context) {
    val repository = SearchRepository(context, "https://api.spotify.com")
    val userProfileInteractor = UserProfileInteractor(context)

    fun discovery(query: String, callback: (pagingObject: PagingObject<Track>?) -> Unit) {
        userProfileInteractor.getUserProfile {
            if (it != null) {
                repository.discovery(query, callback, it.token!!)
            } else {
                callback(null)
            }
        }
    }
}