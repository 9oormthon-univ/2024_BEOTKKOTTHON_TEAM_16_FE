package com.beotkkot.tamhumhajang.data.model.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("timeStamp")
    val timeStamp: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("error")
    val message: String,
    @SerializedName("path")
    val path: String
)