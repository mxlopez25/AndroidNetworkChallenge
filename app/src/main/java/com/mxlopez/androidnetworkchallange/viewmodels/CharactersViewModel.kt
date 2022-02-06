package com.mxlopez.androidnetworkchallange.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import com.mxlopez.androidnetworkchallange.ApiService
import com.mxlopez.androidnetworkchallange.BASE_URL
import com.mxlopez.androidnetworkchallange.models.CharacterModel
import com.mxlopez.androidnetworkchallange.models.LocationModel
import com.mxlopez.androidnetworkchallange.models.OriginModel
import kotlinx.coroutines.launch
import org.json.JSONArray

class CharactersViewModel: ViewModel() {
    val TAG = "CharactersViewModel"
    private val _characterList = mutableStateListOf<CharacterModel>()
    var errorMessage: String by mutableStateOf("")
    val characterList: List<CharacterModel>
        get() = _characterList

    fun getCharacterList(context: Context) {
        viewModelScope.launch {
            val queue = ApiService.getInstance(context.applicationContext).requestQueue
            queue.add(getCharacters())

        }
    }

    fun getCharacters(): JsonObjectRequest {
        return JsonObjectRequest(Request.Method.GET, BASE_URL, null, {
            Log.d(TAG, "Success")
            Log.d(TAG, it.toString())
            val info = it.get("info")
            val results = it.getJSONArray("results") as JSONArray
            for(i in 0 until results.length()) {
                val result = results.getJSONObject(i)

                val resultOrigin = result.getJSONObject("origin")
                val origin = OriginModel(resultOrigin.get("name") as String, resultOrigin.get("url") as String?)

                val resultLocation = result.getJSONObject("location")
                val location = LocationModel(resultLocation.get("name") as String, resultLocation.get("url") as String?)

                var episode = Gson().fromJson(result.get("episode").toString(), Array<String>::class.java)
                // Converting to Chars

                val c = CharacterModel(
                    result.get("id") as Int,
                    result.get("name") as String,
                    result.get("status") as String,
                    result.get("species") as String,
                    result.get("type") as String,
                    result.get("gender") as String,
                    origin,
                    location,
                    result.get("image") as String,
                    episode,
                    result.get("url") as String,
                    result.get("created") as String
                )

                _characterList.add(c)
                // End Converting to Chars
            }

        }, {
            Log.e(TAG, "Error")
            Log.e(TAG, it.toString())
        })
    }
}