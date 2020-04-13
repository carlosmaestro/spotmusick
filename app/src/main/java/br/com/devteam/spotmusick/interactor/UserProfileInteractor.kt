package br.com.devteam.spotmusick.interactor

import br.com.devteam.spotmusick.domain.Response
import br.com.devteam.spotmusick.domain.SpotifyProfile
import br.com.devteam.spotmusick.repository.UserProfileRepository

class UserProfileInteractor {
    val repository = UserProfileRepository()


    fun save(spotifyProfile: SpotifyProfile?, callback: (response: Response?) -> Unit) {
        if (spotifyProfile != null) {
            repository.save(spotifyProfile, callback)
        }
    }
}