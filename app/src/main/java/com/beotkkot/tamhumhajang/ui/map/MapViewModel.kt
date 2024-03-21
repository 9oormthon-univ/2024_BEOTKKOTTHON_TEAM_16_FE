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
import com.beotkkot.tamhumhajang.data.di.PersistenceModule.SEQUENCE
import com.beotkkot.tamhumhajang.data.di.PersistenceModule.USER_ID
import com.beotkkot.tamhumhajang.data.model.response.BadgePosition
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
        updateState(currentState.copy(sequence = sequence))

        getShopsLocation()
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

                    // 임시로 배지 포지션을 두번째 샵으로 설정 TODO : 서버 수정 후 지워야함
                    updateState(
                        currentState.copy(
                            shops = result,
                            badgePosition = BadgePosition(result[1].latitude, result[1].longitude)
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
        val sequence = runBlocking { dataStoreRepository.getIntValue(SEQUENCE).first() }

        apiRepository.getQuests(userId, sequence).onStart {
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

    fun updateShowRecommendShopPopup(isShow: Boolean) {
        updateState(currentState.copy(showRecommendMarketPopup = isShow))
    }

    fun updateShowQuestPopup(isShow: Boolean) {
        updateState(currentState.copy(showQuestPopup = isShow))
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