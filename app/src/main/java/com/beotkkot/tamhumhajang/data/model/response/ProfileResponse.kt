package com.beotkkot.tamhumhajang.data.model.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("gradeDescription")
    val gradeDescription: String,
    @SerializedName("characterName")
    val characterName: String,
    @SerializedName("previousImage")
    val previousImage: String?,
    @SerializedName("currentImage")
    val currentImage: String,
    @SerializedName("nextImage")
    val nextImage: String,
    @SerializedName("bookRows")
    val bookRows: List<BookRow>

)

data class BookRow(
    @SerializedName("badges")
    val badges: List<Badge>,
    @SerializedName("reward")
    val reward: Reward
)

data class Badge(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("acquisitionMethod")
    val acquisitionMethod: String,
    @SerializedName("acquiredAt")
    val acquiredAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("imgUrl")
    val imgUrl: String
)

data class Reward(
    @SerializedName("id")
    val id: Int,
    @SerializedName("imgUrl")
    val imgUrl: String,
    @SerializedName("isUsed")
    val isUsed: Boolean,
    @SerializedName("isAcquired")
    val isAcquired: Boolean
)