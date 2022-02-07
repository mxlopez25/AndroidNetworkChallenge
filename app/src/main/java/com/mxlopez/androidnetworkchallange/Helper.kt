package com.mxlopez.androidnetworkchallange

import com.mxlopez.androidnetworkchallange.models.CharacterModel

object Helper {
    private var charList = mutableListOf<CharacterModel>()

    fun getCharacterList(): MutableList<CharacterModel> {
        return charList
    }

    fun setCharacter(value: CharacterModel) {
        charList.add(value)
    }
}