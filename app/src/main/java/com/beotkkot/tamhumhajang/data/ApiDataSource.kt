package com.beotkkot.tamhumhajang.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ApiDataSource @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun login(
        nickname: String
    ) = flow {
        emit(apiService.login(nickname))
    }.flowOn(ioDispatcher)

    fun getRecommendMarkets(
        userId: Int
    ) = flow {
        emit(apiService.getRecommendMarkets(userId))
    }.flowOn(ioDispatcher)

    fun getShops(
        userId: Int
    ) = flow {
        emit(apiService.getShops(userId))
    }.flowOn(ioDispatcher)

    fun getQuests(
        userId: Int
    ) = flow {
        emit(apiService.getQuests(userId))
    }.flowOn(ioDispatcher)

    fun getBadge(
        userId: Int,
        sequence: Int
    ) = flow {
        emit(apiService.getBadge(userId, sequence))
    }.flowOn(ioDispatcher)

    fun postTouch(
        userId: Int
    ) = flow {
        emit(apiService.postTouch(userId))
    }.flowOn(ioDispatcher)

    fun getBookmarks(
        userId: Int
    ) = flow {
        emit(apiService.getBookmarks(userId))
    }.flowOn(ioDispatcher)

    fun postBookmark(
        userId: Int,
        shopId: Int
    ) = flow {
        emit(apiService.postBookmark(userId, shopId))
    }.flowOn(ioDispatcher)

    fun getProfile(
        userId: Int
    ) = flow {
        emit(apiService.getProfile(userId))
    }.flowOn(ioDispatcher)

    fun postLevelUp(
        userId: Int
    ) = flow {
        emit(apiService.postLevelUp(userId))
    }.flowOn(ioDispatcher)

    fun useReward(
        userId: Int,
        trophyId: Int
    ) = flow {
        emit(apiService.useReward(userId, trophyId))
    }.flowOn(ioDispatcher)
}