package br.com.devteam.spotmusick.repository

import br.com.devteam.spotmusick.R
import br.com.devteam.spotmusick.domain.Response
import br.com.devteam.spotmusick.domain.SpotifyProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class UserProfileRepository {

    private val database = FirebaseDatabase.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private fun getUserDbRef(): DatabaseReference{
        val uid = auth.currentUser!!.uid
        val path = R.string.user_config_firebase_path.toString()
        return database.getReference("$path/$uid")
    }

    fun save(spotifyProfile: SpotifyProfile, callback: (response: Response?) -> Unit){
        val ref = getUserDbRef()
        ref.setValue(spotifyProfile).addOnSuccessListener {
            callback(Response())
        }.addOnFailureListener {
            callback(
                Response(
                    false,
                    userMessage = "Houve um problema ao salvar os dados do usuário."
                )
            )
        }
    }
}