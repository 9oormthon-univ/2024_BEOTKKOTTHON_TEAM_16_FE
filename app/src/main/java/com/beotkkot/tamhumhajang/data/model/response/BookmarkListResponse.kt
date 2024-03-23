package com.beotkkot.tamhumhajang.data.model.response

import com.google.gson.annotations.SerializedName

data class BookmarkListResponse(
    @SerializedName("shops")
    val shops: List<BookmarkedShop>
)

data class BookmarkedShop(
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
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("tags")
    val tags: List<String>
)
