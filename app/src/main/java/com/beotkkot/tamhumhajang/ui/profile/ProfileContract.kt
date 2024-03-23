package com.beotkkot.tamhumhajang.ui.profile

import androidx.navigation.NavOptions
import com.beotkkot.tamhumhajang.common.UiEffect
import com.beotkkot.tamhumhajang.common.UiEvent
import com.beotkkot.tamhumhajang.common.UiState
import com.beotkkot.tamhumhajang.data.model.response.BookRow

class ProfileContract {

    data class State(
        val isLoading: Boolean = false,
        val nickname: String = "",
        val gradeDescription: String = "",
        val characterName: String = "",
        val previousImage: String? = null,
        val currentImage: String = "",
        val nextImage: String = "",
        val bookRows: List<BookRow> = emptyList(),

        val showCouponPopup: Boolean = false,
        val couponId: Int? = null
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