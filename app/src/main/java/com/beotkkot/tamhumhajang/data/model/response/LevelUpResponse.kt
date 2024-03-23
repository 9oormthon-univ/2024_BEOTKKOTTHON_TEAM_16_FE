package com.beotkkot.tamhumhajang.data.model.response

import com.google.gson.annotations.SerializedName

data class LevelUpResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("grade")
    val grade: String,
    @SerializedName("level")
    val level: Int,
    @SerializedName("characterImgUrl")
    val characterImgUrl: String,
    @SerializedName("tierImgUrl")
    val tierImgUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("position")
    val badgePosition: BadgePosition
)