package com.sample.network.utils

import com.google.gson.annotations.SerializedName

abstract class BaseResponse<T> {
    @SerializedName("code")
    var code = 0

    @SerializedName("status_message")
    var statusMessage: String? = null
    abstract val data: T
}