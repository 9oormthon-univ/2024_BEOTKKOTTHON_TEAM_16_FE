package com.beotkkot.tamhumhajang.data.model.response

import com.google.gson.annotations.SerializedName

data class VerifyAnswerResponse(
    @SerializedName("isCorrect")
    val isCorrect: Boolean
)
