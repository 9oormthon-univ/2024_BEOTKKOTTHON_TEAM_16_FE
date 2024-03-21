package com.beotkkot.tamhumhajang.data.model.response

import com.google.gson.annotations.SerializedName

data class LevelUpPopupResponse(
    @SerializedName("levelUpIntroducePopup")
    val levelUpIntroducePopup: LevelUpIntroducePopup,
    @SerializedName("rewardPopup")
    val rewardPopup: RewardPopup
)

data class LevelUpIntroducePopup(
    @SerializedName("title")
    val title: String,
    @SerializedName("grade")
    val grade: String,
    @SerializedName("imgUrl")
    val imgUrl: String,
    @SerializedName("tierImgUrl")
    val tierImgUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("confirm")
    val confirm: String
)

data class RewardPopup(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("imgUrl")
    val imgUrl: String,
    @SerializedName("positive")
    val positive: String,
    @SerializedName("negative")
    val negative: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)