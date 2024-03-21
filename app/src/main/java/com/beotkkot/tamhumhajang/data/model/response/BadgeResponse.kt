package com.beotkkot.tamhumhajang.data.model.response

import com.google.gson.annotations.SerializedName

data class BadgeResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("imgUrl")
    val imgUrl: String,
    @SerializedName("positive")
    val positive: String,
    @SerializedName("negative")
    val negative: String
)