package com.beotkkot.tamhumhajang.ui.map

import androidx.navigation.NavOptions
import com.beotkkot.tamhumhajang.common.UiEffect
import com.beotkkot.tamhumhajang.common.UiEvent
import com.beotkkot.tamhumhajang.common.UiState
import com.beotkkot.tamhumhajang.data.model.Quest
import com.beotkkot.tamhumhajang.data.model.RecommendMarket
import com.beotkkot.tamhumhajang.data.model.response.BadgePosition
import com.beotkkot.tamhumhajang.data.model.response.Shop
import com.kakao.vectormap.LatLng

class MapContract {

    data class State(
        val isLoading: Boolean = false,
        val userPosition: LatLng = DEFAULT_LATLNG,
        val movingCameraPosition: MovingCameraWrapper = MovingCameraWrapper.DEFAULT,

        val sequence: Int = 0,

        val isFixedPerspective: Boolean = false,

        val shops: List<Shop> = emptyList(),
        val badgePosition: BadgePosition? = null,

        val showFirstBadgePopup: Boolean = true,

        val showRecommendMarketPopup: Boolean = false,
        val recommendMarkets: List<RecommendMarket> = emptyList(),

        val showQuestPopup: Boolean = false,
        val quests: List<Quest> = emptyList()
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