package com.beotkkot.tamhumhajang.data

import com.beotkkot.tamhumhajang.data.adapter.ApiResult
import com.beotkkot.tamhumhajang.data.model.response.BadgeResponse
import com.beotkkot.tamhumhajang.data.model.response.BookmarkResponse
import com.beotkkot.tamhumhajang.data.model.response.LevelUpPopupResponse
import com.beotkkot.tamhumhajang.data.model.response.LoginResponse
import com.beotkkot.tamhumhajang.data.model.response.ProfileResponse
import com.beotkkot.tamhumhajang.data.model.response.QuestListResponse
import com.beotkkot.tamhumhajang.data.model.response.RecommendMarketResponse
import com.beotkkot.tamhumhajang.data.model.response.ShopListResponse
import com.beotkkot.tamhumhajang.data.model.response.TouchResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("users/{nickname}")
    suspend fun login(
        @Path("nickname") nickname: String
    ): ApiResult<LoginResponse>

    @GET("users/{userId}/markets/recommend")
    suspend fun getRecommendMarkets(
        @Path("userId") userId: Int
    ): ApiResult<RecommendMarketResponse>

    @GET("users/{userId}/shopScreen")
    suspend fun getShops(
        @Path("userId") userId: Int
    ): ApiResult<ShopListResponse>

    @GET("users/{userId}/questList")
    suspend fun getQuests(
        @Path("userId") userId: Int
    ): ApiResult<QuestListResponse>

    @GET("users/{userId}/badge/{sequence}")
    suspend fun getBadge(
        @Path("userId") userId: Int,
        @Path("sequence") sequence: Int
    ): ApiResult<BadgeResponse>

    @POST("users/{userId}/touch")
    suspend fun postTouch(
        @Path("userId") userId: Int
    ): ApiResult<TouchResponse>

    @POST("users/{userId}/bookmarks/{shopId}")
    suspend fun postBookmark(
        @Path("userId") userId: Int,
        @Path("shopId") shopId: Int
    ): ApiResult<BookmarkResponse>

    @GET("users/{userId}/profile")
    suspend fun getProfile(
        @Path("userId") userId: Int
    ): ApiResult<ProfileResponse>

    @GET("users/{userId}/level/{sequence}")
    suspend fun getLevelUpPopup(
        @Path("userId") userId: Int,
        @Path("sequence") sequence: Int
    ): ApiResult<LevelUpPopupResponse>
}