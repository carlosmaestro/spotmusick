package br.com.devteam.spotmusick.repository

import android.content.Context
import br.com.devteam.spotmusick.domain.PagingObject
import br.com.devteam.spotmusick.domain.Track
import br.com.devteam.spotmusick.repository.dto.PagingObjectDTO
import br.com.devteam.spotmusick.repository.dto.TrackDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchService {

    @GET("v1/search")
    fun <T>discovery(
        @Header("Authorization") authorization: String = "XXXX",
        @Query("q") query: String = "",
        @Query("type") type: String = "track",
        @Query("market") market: String = "BR"
    ): Call<HashMap<String, PagingObjectDTO<TrackDTO, Track>>>
}

class SearchRepository(context: Context, baseUrl: String) : BaseRetrofit(context, baseUrl) {
    private val service = retrofit.create(SearchService::class.java)

    fun discovery(query: String, callback: (pagingObject: PagingObject<Track>?) -> Unit, token: String) {

        service.discovery<TrackDTO>("Bearer $token", query).enqueue(object : Callback<HashMap<String, PagingObjectDTO<TrackDTO, Track>>> {

            override fun onResponse(
                call: Call<HashMap<String, PagingObjectDTO<TrackDTO, Track>>>,
                response: Response<HashMap<String, PagingObjectDTO<TrackDTO, Track>>>
            ) {

                val body = response.body()

                callback(body?.get("tracks")?.toDomain())
            }

            override fun onFailure(call: Call<HashMap<String, PagingObjectDTO<TrackDTO, Track>>>, error: Throwable) {
                callback(null)
            }
        })
    }

}