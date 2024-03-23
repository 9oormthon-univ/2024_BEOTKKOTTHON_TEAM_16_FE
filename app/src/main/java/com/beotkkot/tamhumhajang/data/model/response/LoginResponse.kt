package com.beotkkot.tamhumhajang.data.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("grade")
    val grade: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("getUserItemCount")
    val getUserItemCount: Int
)
