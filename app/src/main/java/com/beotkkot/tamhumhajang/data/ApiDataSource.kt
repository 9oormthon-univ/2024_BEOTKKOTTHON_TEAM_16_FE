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
        userId: Int,
        sequence: Int
    ) = flow {
        emit(apiService.getQuests(userId, sequence))
    }.flowOn(ioDispatcher)

    fun getBadge(
        userId: Int,
        sequence: Int
    ) = flow {
        emit(apiService.getBadge(userId, sequence))
    }.flowOn(ioDispatcher)

    fun getProfile(
        userId: Int
    ) = flow {
        emit(apiService.getProfile(userId))
    }.flowOn(ioDispatcher)

    fun getLevelUpPopup(
        userId: Int,
        sequence: Int
    ) = flow {
        emit(apiService.getLevelUpPopup(userId, sequence))
    }.flowOn(ioDispatcher)
}