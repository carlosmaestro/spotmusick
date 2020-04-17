package br.com.devteam.spotmusick.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.devteam.spotmusick.domain.ApiResponse
import br.com.devteam.spotmusick.domain.SpotifyProfile
import br.com.devteam.spotmusick.domain.UserProfile
import br.com.devteam.spotmusick.interactor.UserProfileInteractor

class UserProfileViewModel (val app: Application) : AndroidViewModel(app) {

    private val interactor = UserProfileInteractor(app.applicationContext)

    val userProfile: MutableLiveData<UserProfile> = MutableLiveData()


    fun save( callback: (apiResponse: ApiResponse?) -> Unit) {
        interactor.save(userProfile.value, callback)
    }

    fun cacheUserProfile(token: String, callback: (apiResponse: ApiResponse?) -> Unit) {
        interactor.cacheUserProfile(token, callback)
    }
}