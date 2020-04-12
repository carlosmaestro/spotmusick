package br.com.devteam.spotmusick.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.devteam.spotmusick.domain.Response
import br.com.devteam.spotmusick.interactor.AuthInteractor

class AuthViewModel(val app: Application) : AndroidViewModel(app) {

    private val authInteractor = AuthInteractor()

    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val confirmPassword: MutableLiveData<String> = MutableLiveData()

    fun login(callback: (response: Response?) -> Unit) {
        authInteractor.login(email.value, password.value, callback)
    }

    fun sendPasswordResetEmail(callback: (response: Response?) -> Unit) {
        authInteractor.sendPasswordResetEmail(email.value, callback)
    }

    fun createUser(callback: (response: Response?) -> Unit) {
        authInteractor.createUser(email.value, password.value, confirmPassword.value, callback)
    }

}
