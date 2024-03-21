package com.beotkkot.tamhumhajang.ui.login

import com.beotkkot.tamhumhajang.common.BaseViewModel
import com.beotkkot.tamhumhajang.data.ApiRepository
import com.beotkkot.tamhumhajang.data.DataStoreRepository
import com.beotkkot.tamhumhajang.data.di.PersistenceModule
import com.beotkkot.tamhumhajang.ui.MAP
import dagger.hilt.android.lifecycle.HiltViewModel
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
        // TODO("로그인 API 나오면 연동")

        dataStoreRepository.setIntValue(PersistenceModule.USER_ID, 1)
        postEffect(LoginContract.Effect.NavigateTo(MAP))
    }

    fun updateNickname(nickname: String) {
        updateState(currentState.copy(nickname = nickname))
    }
}