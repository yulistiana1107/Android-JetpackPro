package com.yulistiana.jetpackpro.lastsubmission.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Responses<T>(
    @SerializedName("status_message")
    val statusMessage: String? = null,
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("results")
    val result: List<T>? = null
)