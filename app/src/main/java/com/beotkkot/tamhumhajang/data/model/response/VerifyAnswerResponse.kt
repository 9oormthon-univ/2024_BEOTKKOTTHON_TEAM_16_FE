package com.beotkkot.tamhumhajang.data.model.response

import com.google.gson.annotations.SerializedName

data class VerifyAnswerResponse(
    @SerializedName("isCollect")
    val isCorrect: Boolean,
    @SerializedName("isLevelUp")
    val isLevelUp: Boolean
)
