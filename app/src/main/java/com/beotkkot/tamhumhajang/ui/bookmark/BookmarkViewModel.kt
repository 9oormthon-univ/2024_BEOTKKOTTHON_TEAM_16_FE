package com.beotkkot.tamhumhajang.ui.bookmark

import androidx.lifecycle.viewModelScope
import com.beotkkot.tamhumhajang.common.BaseViewModel
import com.beotkkot.tamhumhajang.data.ApiRepository
import com.beotkkot.tamhumhajang.data.DataStoreRepository
import com.beotkkot.tamhumhajang.data.adapter.ApiResult
import com.beotkkot.tamhumhajang.data.di.PersistenceModule.USER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dataStoreRepository: DataStoreRepository
) : BaseViewModel<BookmarkContract.State, BookmarkContract.Event, BookmarkContract.Effect>(
    initialState = BookmarkContract.State()
) {

    override fun reduceState(event: BookmarkContract.Event) {
        TODO("Not yet implemented")
    }

    fun getShops() = viewModelScope.launch {
        val userId = runBlocking { dataStoreRepository.getIntValue(USER_ID).first() }

        apiRepository.getBookmarks(userId).collect {
            when (it) {
                is ApiResult.Success -> {
                    val result = it.data

                    updateState(currentState.copy(shops = result.shops))
                }

                is ApiResult.ApiError -> {
                    postEffect(BookmarkContract.Effect.ShowSnackBar(it.message))
                }

                is ApiResult.NetworkError -> {
                    postEffect(BookmarkContract.Effect.ShowSnackBar("네트워크 오류가 발생했습니다."))
                }
            }
        }
    }
}