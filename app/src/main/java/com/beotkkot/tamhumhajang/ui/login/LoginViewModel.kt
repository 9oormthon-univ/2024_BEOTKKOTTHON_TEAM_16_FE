package com.beotkkot.tamhumhajang.ui.login

import com.beotkkot.tamhumhajang.common.BaseViewModel
import com.beotkkot.tamhumhajang.data.ApiRepository
import com.beotkkot.tamhumhajang.data.DataStoreRepository
import com.beotkkot.tamhumhajang.data.adapter.ApiResult
import com.beotkkot.tamhumhajang.data.di.PersistenceModule.GRADE
import com.beotkkot.tamhumhajang.data.di.PersistenceModule.USER_ID
import com.beotkkot.tamhumhajang.ui.MAP
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dataStoreRepository: DataStoreRepository
) : BaseViewModel<LoginContract.State, LoginContract.Event, LoginContract.Effect>(
    initialState = LoginContract.State()
) {
    override fun reduceState(event: LoginContract.Event) {
        TODO("Not yet implemented")
    }

    fun login(nickname: String) = runBlocking {
        apiRepository.login(nickname).onStart {
            updateState(currentState.copy(isLoading = true))
        }.collect {
            when (it) {
                is ApiResult.Success -> {
                    val result = it.data
                    runBlocking {
                        dataStoreRepository.setIntValue(USER_ID, result.id)
                        dataStoreRepository.setIntValue(GRADE, result.grade)
                    }
                    postEffect(LoginContract.Effect.NavigateTo(MAP))
                }
                is ApiResult.ApiError -> {
                    postEffect(LoginContract.Effect.ShowSnackBar(it.message))
                }
                is ApiResult.NetworkError -> {
                    postEffect(LoginContract.Effect.ShowSnackBar("네트워크 오류가 발생했습니다."))
                }
            }
        }

        dataStoreRepository.setIntValue(USER_ID, 1)

    }

    fun updateNickname(nickname: String) {
        updateState(currentState.copy(nickname = nickname))
    }
}