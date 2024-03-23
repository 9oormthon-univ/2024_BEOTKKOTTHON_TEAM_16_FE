package com.beotkkot.tamhumhajang.ui.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewModelScope
import com.beotkkot.tamhumhajang.common.BaseViewModel
import com.beotkkot.tamhumhajang.data.ApiRepository
import com.beotkkot.tamhumhajang.data.DataStoreRepository
import com.beotkkot.tamhumhajang.data.adapter.ApiResult
import com.beotkkot.tamhumhajang.data.di.PersistenceModule.GRADE
import com.beotkkot.tamhumhajang.data.di.PersistenceModule.SEQUENCE
import com.beotkkot.tamhumhajang.data.di.PersistenceModule.USER_ID
import com.beotkkot.tamhumhajang.data.model.PopupType
import com.beotkkot.tamhumhajang.data.model.response.BadgePosition
import com.beotkkot.tamhumhajang.ui.PROFILE
import com.beotkkot.tamhumhajang.ui.toast.ToastType
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dataStoreRepository: DataStoreRepository,
    @ApplicationContext private val context: Context
) : BaseViewModel<MapContract.State, MapContract.Event, MapContract.Effect>(
    initialState = MapContract.State()
) {

    companion object {
        var initialMarkerLoadFlag = true
    }

    override fun reduceState(event: MapContract.Event) {
        TODO("Not yet implemented")
    }

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val locationRequest: LocationRequest =
        LocationRequest.Builder(1000) // 초기 1회만 가져오고 Long.MAX_VALUE 만큼 기다림
            .setPriority(Priority.PRIORITY_LOW_POWER)
            .build()
    private val locationCallback: CustomLocationCallback = CustomLocationCallback()

    init {
        val sequence = runBlocking { dataStoreRepository.getIntValue(SEQUENCE).first() }
        val grade = runBlocking { dataStoreRepository.getIntValue(GRADE).first() }
        updateState(currentState.copy(sequence = sequence, userGrade = grade))

        // 첫 시작 시 배지 획득 팝업
        if (sequence == 1) updateShowFirstBadgePopup(true)

        getShopsLocation()
    }

    fun checkIsNearMarker() = viewModelScope.launch {
        val badgePosition = currentState.badgePosition
        val merchantAssociationPosition = currentState.merchantAssociationPosition

        badgePosition?.let {
            val userPosition = currentState.userPosition

            if (
                userPosition.latitude >= it.latitude - 0.0001 &&
                userPosition.latitude <= it.latitude + 0.0001 &&
                userPosition.longitude >= it.longitude - 0.0001 &&
                userPosition.longitude <= it.longitude + 0.0001
            ) {
                updateState(currentState.copy(badgePosition = null))

                touch()
            }
        }
        
        merchantAssociationPosition?.let {
            val userPosition = currentState.userPosition

            if (
                userPosition.latitude >= it.latitude - 0.0001 &&
                userPosition.latitude <= it.latitude + 0.0001 &&
                userPosition.longitude >= it.longitude - 0.0001 &&
                userPosition.longitude <= it.longitude + 0.0001
            ) {
                updateState(
                    currentState.copy(
                        showingToast = ToastType.REWARD,
                        toastOnClick = {
                            postEffect(MapContract.Effect.NavigateTo(PROFILE))
                        }
                    )
                )
            } else {
                if (currentState.showingToast == ToastType.REWARD) {
                    updateState(
                        currentState.copy(
                            showingToast = null,
                            toastOnClick = { }
                        )
                    )
                }
            }
        }
    }

    fun setSequence(itemCount: Int) {
        updateState(currentState.copy(sequence = itemCount))
        runBlocking { dataStoreRepository.setIntValue(SEQUENCE, itemCount) }
    }

    fun setLevel(level: Int) {
        updateState(currentState.copy(userGrade = level))
        runBlocking { dataStoreRepository.setIntValue(GRADE, level) }
    }

    fun touch() = viewModelScope.launch {
        val userId = runBlocking { dataStoreRepository.getIntValue(USER_ID).first() }

        apiRepository.postTouch(userId).collect {
            when (it) {
                is ApiResult.Success -> {
                    val result = it.data

                    setSequence(result.id)

                    when (result.popupType) {
                        PopupType.APP -> {
                            updateState(
                                currentState.copy(
                                    showBadgePopup = true,
                                    badgePopup = result.badgePopup
                                )
                            )
                        }

                        PopupType.Location -> {
                            updateState(
                                currentState.copy(
                                    showBadgePopup = true,
                                    badgePopup = result.badgePopup,
                                    badgePosition = null
                                )
                            )
                        }

                        PopupType.Quiz -> {
                            //
                        }
                    }
                }

                is ApiResult.ApiError -> {
                    postEffect(MapContract.Effect.ShowSnackBar(it.message))
                }

                is ApiResult.NetworkError -> {
                    postEffect(MapContract.Effect.ShowSnackBar("네트워크 오류가 발생했습니다."))
                }
            }
        }
    }

    fun levelUp() = viewModelScope.launch {
        val userId = runBlocking { dataStoreRepository.getIntValue(USER_ID).first() }

        apiRepository.postLevelUp(userId).collect {
            when (it) {
                is ApiResult.Success -> {
                    val result = it.data

                    setLevel(result.level)
                    updateState(
                        currentState.copy(
                            showLevelUpPopup = true,
                            levelUpPopup = result, 
                            merchantAssociationPosition = result.badgePosition
                        )
                    )
                }

                is ApiResult.ApiError -> {
                    postEffect(MapContract.Effect.ShowSnackBar(it.message))
                }

                is ApiResult.NetworkError -> {
                    postEffect(MapContract.Effect.ShowSnackBar("네트워크 오류가 발생했습니다."))
                }
            }
        }
    }

    fun postBookmark(shopId: Int) = viewModelScope.launch {
        val userId = runBlocking { dataStoreRepository.getIntValue(USER_ID).first() }
        
        apiRepository.postBookmark(userId, shopId).onStart {
            updateState(currentState.copy(isLoading = true))
        }.collect {
            updateState(currentState.copy(isLoading = false))

            when (it) {
                is ApiResult.Success -> {
                    val result = it.data

                    if (result.isBookmarked && currentState.sequence == 2) {
                        touch()
                    }
                }

                is ApiResult.ApiError -> {
                    postEffect(MapContract.Effect.ShowSnackBar(it.message))
                }

                is ApiResult.NetworkError -> {
                    postEffect(MapContract.Effect.ShowSnackBar("네트워크 오류가 발생했습니다."))
                }
            }
        }
    }


    fun getShopsLocation() = viewModelScope.launch {
        val userId = runBlocking { dataStoreRepository.getIntValue(USER_ID).first() }

        apiRepository.getShops(userId).onStart {
            updateState(currentState.copy(isLoading = true))
        }.collect {
            updateState(currentState.copy(isLoading = false))
            when (it) {
                is ApiResult.Success -> {
                    val result = it.data.shops

                    updateState(currentState.copy(shops = result))
                }

                is ApiResult.ApiError -> {
                    postEffect(MapContract.Effect.ShowSnackBar(it.message))
                }

                is ApiResult.NetworkError -> {
                    postEffect(MapContract.Effect.ShowSnackBar("네트워크 오류가 발생했습니다."))
                }
            }
        }
    }

    fun showRecommendMarkets() = viewModelScope.launch {
        val userId = runBlocking { dataStoreRepository.getIntValue(USER_ID).first() }

        apiRepository.getRecommendMarkets(userId).onStart {
            updateState(currentState.copy(isLoading = true))
        }.collect {
            updateState(currentState.copy(isLoading = false))
            when (it) {
                is ApiResult.Success -> {
                    val result = it.data.markets

                    updateState(
                        currentState.copy(
                            showRecommendMarketPopup = true,
                            recommendMarkets = result
                        )
                    )
                }

                is ApiResult.ApiError -> {
                    postEffect(MapContract.Effect.ShowSnackBar(it.message))
                }

                is ApiResult.NetworkError -> {
                    postEffect(MapContract.Effect.ShowSnackBar("네트워크 오류가 발생했습니다."))
                }
            }
        }
    }

    fun showQuests() = viewModelScope.launch {
        val userId = runBlocking { dataStoreRepository.getIntValue(USER_ID).first() }

        apiRepository.getQuests(userId).onStart {
            updateState(currentState.copy(isLoading = true))
        }.collect {
            updateState(currentState.copy(isLoading = false))
            when (it) {
                is ApiResult.Success -> {
                    val result = it.data.quest

                    updateState(
                        currentState.copy(
                            showQuestPopup = true,
                            quests = result
                        )
                    )
                }

                is ApiResult.ApiError -> {
                    postEffect(MapContract.Effect.ShowSnackBar(it.message))
                }

                is ApiResult.NetworkError -> {
                    postEffect(MapContract.Effect.ShowSnackBar("네트워크 오류가 발생했습니다."))
                }
            }
        }
    }

    fun showNavigatePopup(name: String, onClick: () -> Unit) {
        postEffect(MapContract.Effect.ShowToast(ToastType.NAVIGATE, name, onClick))
    }

    fun updateMovingCameraPosition(movingCameraPosition: MovingCameraWrapper) {
        updateState(currentState.copy(movingCameraPosition = movingCameraPosition))
    }

    fun updateMovingCameraPositionToDefault() {
        updateState(currentState.copy(movingCameraPosition = MovingCameraWrapper.DEFAULT))
    }

    fun updateIsFixedPerspective(isFixed: Boolean) {
        updateState(currentState.copy(isFixedPerspective = isFixed))
    }

    fun updateShowingToast(type: ToastType?) {
        updateState(currentState.copy(showingToast = type))
    }

    fun updateToastName(name: String) {
        updateState(currentState.copy(toastName = name))
    }

    fun updateToastOnClick(onClick: () -> Unit) {
        updateState(currentState.copy(toastOnClick = onClick))
    }

    fun updateShowFirstBadgePopup(isShow: Boolean) {
        updateState(currentState.copy(showFirstBadgePopup = isShow))
    }

    fun updateShowBadgePopup(isShow: Boolean) {
        updateState(currentState.copy(showBadgePopup = isShow))
    }

    fun updateShowConnectionPopup(isShow: Boolean) {
        updateState(currentState.copy(showConnectionPopup = isShow))
    }

    fun updateBadgePosition(badgePosition: BadgePosition?) {
        updateState(currentState.copy(badgePosition = badgePosition))
    }

    fun updateMerchantAssociationPosition(badgePosition: BadgePosition?) {
        updateState(currentState.copy(merchantAssociationPosition = badgePosition))
    }

    fun updateShowRecommendShopPopup(isShow: Boolean) {
        updateState(currentState.copy(showRecommendMarketPopup = isShow))
    }

    fun updateShowQuestPopup(isShow: Boolean) {
        updateState(currentState.copy(showQuestPopup = isShow))
    }

    fun updateShowLevelUpPopup(isShow: Boolean) {
        updateState(currentState.copy(showLevelUpPopup = isShow))
    }

    fun updateShowRewardPopup(isShow: Boolean) {
        updateState(currentState.copy(showRewardPopup = isShow))
    }

    inner class CustomLocationCallback : LocationCallback() {

        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            locationResult.lastLocation?.let {
                // 초기 1회 진입할 때 마커 불러오기
                if (initialMarkerLoadFlag) {
                    initialMarkerLoadFlag = false
                    updateState(
                        currentState.copy(
                            isLoading = false,
                            movingCameraPosition = MovingCameraWrapper.MOVING(it)
                        )
                    )
                }
                updateState(currentState.copy(userPosition = com.kakao.vectormap.LatLng.from(it.latitude, it.longitude)))
            }
        }
    }

    fun addLocationListener() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    fun removeLocationListener() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

}