package com.beotkkot.tamhumhajang.data

import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiDataSource: ApiDataSource
) {

    fun login(
        nickname: String
    ) = apiDataSource.login(nickname)

    fun getRecommendMarkets(
        userId: Int
    ) = apiDataSource.getRecommendMarkets(userId)

    fun getShops(
        userId: Int
    ) = apiDataSource.getShops(userId)

    fun getQuests(
        userId: Int
    ) = apiDataSource.getQuests(userId)

    fun getBadge(
        userId: Int,
        sequence: Int
    ) = apiDataSource.getBadge(userId, sequence)

    fun postTouch(
        userId: Int
    ) = apiDataSource.postTouch(userId)

    fun getBookmarks(
        userId: Int
    ) = apiDataSource.getBookmarks(userId)

    fun postBookmark(
        userId: Int,
        shopId: Int
    ) = apiDataSource.postBookmark(userId, shopId)

    fun getProfile(
        userId: Int
    ) = apiDataSource.getProfile(userId)

    fun postLevelUp(
        userId: Int
    ) = apiDataSource.postLevelUp(userId)

    fun useReward(
        userId: Int,
        trophyId: Int
    ) = apiDataSource.useReward(userId, trophyId)

}