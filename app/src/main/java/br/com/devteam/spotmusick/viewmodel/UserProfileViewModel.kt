package br.com.devteam.spotmusick.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.devteam.spotmusick.domain.Response
import br.com.devteam.spotmusick.domain.SpotifyProfile
import br.com.devteam.spotmusick.interactor.UserProfileInteractor

class UserProfileViewModel (val app: Application) : AndroidViewModel(app) {

    private val interactor = UserProfileInteractor()

    val spotifyProfile: MutableLiveData<SpotifyProfile> = MutableLiveData()


    fun save( callback: (response: Response?) -> Unit) {
        interactor.save(spotifyProfile.value, callback)
    }
}