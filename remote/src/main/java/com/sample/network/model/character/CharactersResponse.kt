package com.sample.network.model.character

import com.google.gson.annotations.SerializedName
import com.sample.network.utils.BaseResponse

class CharactersResponse : BaseResponse<Any?>() {
    @SerializedName("data")
    override var data: CharactersData? = null
}