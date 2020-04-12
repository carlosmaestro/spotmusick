package br.com.devteam.spotmusick.interactor

import android.content.Context
import br.com.devteam.spotmusick.domain.Response
import br.com.devteam.spotmusick.repository.AuthRepository

class AuthInteractor() {
    private val authRepository = AuthRepository()

    fun login(email: String?, password: String?, callback: (response: Response?) -> Unit) {
        if (email == null || password == null) {
            callback(
                Response(
                    false,
                    userMessage = "Preencha os campos de usuário e senha."
                )
            )
        } else {
            authRepository.login(email, password, callback)
        }
    }

    fun createUser(
        email: String?,
        password: String?,
        confirmPassword: String?,
        callback: (response: Response?) -> Unit
    ) {

        if (
            email == null ||
            password == null ||
            confirmPassword == null
        ) {
            callback(Response(false, userMessage = "É necessário preencher os campos."))
        } else if (password.length < 6) {
            callback(Response(false, userMessage = "A senha precisa ter seis ou mais caracteres."))
        } else if (confirmPassword != password) {
            callback(Response(false, userMessage = "As senhas precisam ser iguais."))
        } else {
            authRepository.createUser(email, password, callback)
        }
    }

    fun sendPasswordResetEmail(email: String?, callback: (response: Response?) -> Unit) {
        if (email != null) {
            authRepository.sendPasswordResetEmail(email, callback)
        } else {
            callback(Response(false, userMessage = "Preencha o campo e-mail."))
        }
    }
}