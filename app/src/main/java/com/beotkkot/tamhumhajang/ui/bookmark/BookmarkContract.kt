package com.beotkkot.tamhumhajang.ui.bookmark

import androidx.navigation.NavOptions
import com.beotkkot.tamhumhajang.common.UiEffect
import com.beotkkot.tamhumhajang.common.UiEvent
import com.beotkkot.tamhumhajang.common.UiState
import com.beotkkot.tamhumhajang.data.model.response.BookmarkedShop

class BookmarkContract {

    data class State(
        val isLoading: Boolean = false,
        val shops: List<BookmarkedShop> = emptyList()
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