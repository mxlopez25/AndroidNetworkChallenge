package com.mxlopez.androidnetworkchallange.models

data class CharacterModel(val id: Int,
                          val name: String,
                          val status: String,
                          val species: String,
                          val type: String,
                          val gender: String,
                          val origin: OriginModel,
                          val location: LocationModel,
                          val image: String,
                          val episodes: Array<String>?,
                          val url: String,
                          val created: String
                          )
