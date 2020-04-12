package br.com.devteam.spotmusick.repository

import com.google.firebase.auth.FirebaseAuth
import br.com.devteam.spotmusick.domain.Response

class AuthRepository() {

    private lateinit var auth: FirebaseAuth

    companion object {

        private var instance: AuthRepository? = null

        fun getInstance(): AuthRepository {
            if (instance == null) {
                instance = AuthRepository()
            }
            return instance!!
        }
    }


    init {
        auth = FirebaseAuth.getInstance()
    }

    fun login(email: String, password: String, callback: (response: Response?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {

            callback(Response())
        }.addOnFailureListener {
            callback(
                Response(
                    false,
                    userMessage = "Usuário ou senha incorretos."
                )
            )
        }
    }

    fun createUser(email: String, password: String, callback: (response: Response?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                callback(Response())
            }
            .addOnFailureListener {
                callback(
                    Response(
                        false,
                        userMessage = "Usuário ou senha incorretos."
                    )
                )
            }
    }

    fun sendPasswordResetEmail(email: String, callback: (response: Response?) -> Unit) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            callback(Response())
        }.addOnFailureListener {
            callback(
                Response(
                    false,
                    userMessage = "Erro ao enviar o email."
                )
            )
        }
    }
}