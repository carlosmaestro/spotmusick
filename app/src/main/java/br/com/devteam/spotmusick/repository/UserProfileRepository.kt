package br.com.devteam.spotmusick.repository

import android.content.Context
import br.com.devteam.spotmusick.domain.ApiResponse
import br.com.devteam.spotmusick.domain.UserProfile
import br.com.devteam.spotmusick.repository.dto.UserProfileDTO
import br.com.devteam.spotmusick.repository.room.RoomDatabase
import br.com.devteam.spotmusick.repository.room.entity.UserProfileBasic
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserProfileService {

    @GET("v1/me")
    fun discovery(
        @Header("Authorization") authorization: String = "XXXX"
    ): Call<UserProfileDTO>
}

class UserProfileRepository(context: Context, baseUrl: String) : BaseRetrofit(context, baseUrl) {

    private val service = retrofit.create(UserProfileService::class.java)

    private val database = FirebaseDatabase.getInstance()
    private val auth = FirebaseAuth.getInstance()

//    private val appDb = RoomDatabase(context).getRoomDatabaseRef()

    private fun getUserDbRef(): DatabaseReference {
        val uid = auth.currentUser!!.uid
        return database.getReference("user_profile/$uid")
    }

    fun save(userProfile: UserProfile, callback: (apiResponse: ApiResponse?) -> Unit) {
//        val user = appDb.romDao().insertUser(
//            UserProfileBasic(
//                id = userProfile.id!!,
//                email = userProfile.email,
//                token = userProfile.token,
//                displayName = userProfile.displayName
//            )
//        )
        val ref = getUserDbRef()
        ref.setValue(userProfile).addOnSuccessListener {
            callback(ApiResponse())
        }.addOnFailureListener {
            callback(
                ApiResponse(
                    false,
                    userMessage = "Houve um problema ao salvar os dados do usuário."
                )
            )
        }
    }

    fun cacheUserProfile(token: String, callback: (apiResponse: ApiResponse?) -> Unit) {
        discovery(token) { userProfile ->
            if (userProfile != null) {
                userProfile.token = token
                save(userProfile) {
                    callback(it)
                }
            } else {
                callback(ApiResponse(false, "Houve um erro ao buscar o perfil do usuario."))
            }
        }
    }

    fun getUserProfile(callback: (userProfile: UserProfile?) -> Unit) {
        val ref = getUserDbRef()

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                callback(null)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val userProfileDTO = snapshot.getValue(UserProfileDTO::class.java)
                if (userProfileDTO != null) {
                    callback(userProfileDTO.toDomain())
                } else {
                    callback(null)
                }

            }
        })
//        val user = appDb.romDao().loadUser(id = auth.currentUser!!.uid)
//        if (user != null) {
//            callback(
//                UserProfile(
//                    id = user.id,
//                    displayName = user.displayName,
//                    token = user.token,
//                    email = user.email
//                )
//            )
//            return
//        } else {


//        }
    }


    fun discovery(token: String, callback: (userProfile: UserProfile?) -> Unit) {

        service.discovery("Bearer $token").enqueue(object : Callback<UserProfileDTO> {

            override fun onResponse(
                call: Call<UserProfileDTO>,
                response: Response<UserProfileDTO>
            ) {
                val profile = response.body()

                callback(profile?.toDomain())
            }

            override fun onFailure(call: Call<UserProfileDTO>, error: Throwable) {
                callback(null)
            }
        })
    }
}