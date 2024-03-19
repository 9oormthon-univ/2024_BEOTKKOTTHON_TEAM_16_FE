package com.beotkkot.tamhumhajang.ui.kakaomap

import androidx.navigation.NavOptions
import com.beotkkot.tamhumhajang.common.UiEffect
import com.beotkkot.tamhumhajang.common.UiEvent
import com.beotkkot.tamhumhajang.common.UiState
import com.kakao.vectormap.LatLng

class MapContract {

    data class State(
        val isLoading: Boolean = false,
        val userPosition: LatLng = DEFAULT_LATLNG,
        val movingCameraPosition: MovingCameraWrapper = MovingCameraWrapper.DEFAULT,
        val isFixedPerspective: Boolean = false
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

    companion object {
        val DEFAULT_LATLNG = LatLng.from(37.5437, 127.0659)
    }
}