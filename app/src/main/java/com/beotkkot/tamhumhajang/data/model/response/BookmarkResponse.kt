package com.beotkkot.tamhumhajang.data.model.response

import com.google.gson.annotations.SerializedName

data class BookmarkResponse(
    @SerializedName("bookmarked")
    val isBookmarked: Boolean,
    @SerializedName("bookmark")
    val bookmark: Bookmark
)

data class Bookmark(
    @SerializedName("id")
    val id: Int,
    @SerializedName("shop")
    val shop: BookmarkShop,
    @SerializedName("user")
    val user: BookmarkUser
)

data class BookmarkShop(
    @SerializedName("id")
    val id: Int,
    @SerializedName("market")
    val market: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("tag")
    val tag: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("address")
    val address: String
)

data class BookmarkUser(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)
