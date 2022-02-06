package com.mxlopez.androidnetworkchallange

import android.content.Context
import android.net.Network
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.Volley
import com.mxlopez.androidnetworkchallange.models.CharacterModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://rickandmortyapi.com/api/character/"

//interface APIService {
//    @GET("characters")
//    suspend fun getCharacters(): List<CharacterModel>
//
//    companion object {
//        var apiService: APIService? = null
//        fun getInstance(): APIService {
//            if(apiService == null) {
//                apiService = Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build().create(APIService::class.java)
//            }
//            return apiService!!
//        }
//    }
//}

class ApiService constructor(context: Context) {
    val TAG = "ApiService"
    companion object {
        @Volatile
        private var INSTANCE: ApiService? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ApiService(context).also {
                    INSTANCE = it
                }
            }
    }

    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}