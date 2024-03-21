package com.beotkkot.tamhumhajang.data.model.response

import com.google.gson.annotations.SerializedName

data class ShopListResponse(
    @SerializedName("shops")
    val shops: List<Shop>,
    @SerializedName("badgePosition")
    val badgePosition: BadgePosition
)

data class Shop(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("imgUrl")
    val imgUrl: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)

data class BadgePosition(
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?
)