package com.beotkkot.tamhumhajang.ui.profile

import androidx.lifecycle.viewModelScope
import com.beotkkot.tamhumhajang.common.BaseViewModel
import com.beotkkot.tamhumhajang.data.ApiRepository
import com.beotkkot.tamhumhajang.data.DataStoreRepository
import com.beotkkot.tamhumhajang.data.adapter.ApiResult
import com.beotkkot.tamhumhajang.data.di.PersistenceModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dataStoreRepository: DataStoreRepository
) : BaseViewModel<ProfileContract.State, ProfileContract.Event, ProfileContract.Effect>(
    initialState = ProfileContract.State()
) {
    override fun reduceState(event: ProfileContract.Event) {
        TODO("Not yet implemented")
    }

    fun getProfile() = viewModelScope.launch {
        val userId = runBlocking { dataStoreRepository.getIntValue(PersistenceModule.USER_ID).first() }

        apiRepository.getProfile(userId).onStart {
            updateState(currentState.copy(isLoading = false))
        }.collect {
            updateState(currentState.copy(isLoading = true))
            when (it) {
                is ApiResult.Success -> {
                    val result = it.data

                    updateState(
                        currentState.copy(
                            nickname = result.nickname,
                            gradeDescription = result.gradeDescription,
                            characterName = result.characterName,
                            previousImage = result.previousImage,
                            currentImage = result.currentImage,
                            nextImage = result.nextImage,
                            bookRows = result.bookRows
                        )
                    )
                }

                is ApiResult.ApiError -> {
                    postEffect(ProfileContract.Effect.ShowSnackBar(it.message))
                }

                is ApiResult.NetworkError -> {
                    postEffect(ProfileContract.Effect.ShowSnackBar("네트워크 오류가 발생했습니다."))
                }
            }
        }
    }
}