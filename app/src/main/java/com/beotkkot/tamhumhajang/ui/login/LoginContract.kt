package com.beotkkot.tamhumhajang.ui.login

import androidx.navigation.NavOptions
import com.beotkkot.tamhumhajang.common.UiEffect
import com.beotkkot.tamhumhajang.common.UiEvent
import com.beotkkot.tamhumhajang.common.UiState

class LoginContract {

    data class State(
        val isLoading: Boolean = false,
        val nickname: String = ""
    ) : UiState

    sealed class Event : UiEvent {

    }

    sealed class Effect : UiEffect {
        data class NavigateTo(
            val destination: String,
            val navOptions: NavOptions? = null
        ) : Effect()

        data class ShowSnackBar(val message: String) : Effect()
    }

}