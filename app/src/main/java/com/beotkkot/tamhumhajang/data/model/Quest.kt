package com.beotkkot.tamhumhajang.data.model

import com.google.gson.annotations.SerializedName

data class Quest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("isAcquired")
    val isAcquired: Boolean,
    @SerializedName("description")
    val description: String,
    @SerializedName("imgUrl")
    val imgUrl: String
)
