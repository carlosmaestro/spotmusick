package br.com.devteam.spotmusick.interactor

import android.content.Context
import br.com.devteam.spotmusick.R
import br.com.devteam.spotmusick.domain.ApiResponse
import br.com.devteam.spotmusick.domain.UserProfile
import br.com.devteam.spotmusick.repository.UserProfileRepository

class UserProfileInteractor(private val context: Context) {
    val repository = UserProfileRepository(context, "https://api.spotify.com")


    fun save(userProfile: UserProfile?, callback: (apiResponse: ApiResponse?) -> Unit) {
        if (userProfile != null) {
            repository.save(userProfile, callback)
        }
    }

    fun cacheUserProfile(token: String, callback: (apiResponse: ApiResponse?) -> Unit) {
        if (token != null) {
            repository.cacheUserProfile(token, callback)
        }else{
            callback(ApiResponse(false, "Token não provido."))
        }
    }

    fun getUserProfile(callback: (userProfile: UserProfile?) -> Unit){
        repository.getUserProfile(callback)
    }
}