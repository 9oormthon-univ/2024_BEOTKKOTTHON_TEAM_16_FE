package com.beotkkot.tamhumhajang.data.model.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("errorCode")
    val errorCode: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("cause")
    val cause: String
)