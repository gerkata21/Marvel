package com.sample.network.model.character

import com.google.gson.annotations.SerializedName

class CharactersData {
    @SerializedName("results")
    var charactersList: List<CharacterRemote>? = null
}