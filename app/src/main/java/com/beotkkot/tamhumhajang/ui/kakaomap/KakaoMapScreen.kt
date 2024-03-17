package com.beotkkot.tamhumhajang.ui.kakaomap

import android.location.Location
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.beotkkot.kakaomap_compose.KakaoMap
import com.beotkkot.kakaomap_compose.overlay.Label
import com.beotkkot.tamhumhajang.AppState
import com.beotkkot.tamhumhajang.BottomSheetState
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.ui.map.ManageLocationPermission
import com.beotkkot.tamhumhajang.ui.map.MovingCameraWrapper
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.camera.CameraAnimation
import com.kakao.vectormap.camera.CameraUpdateFactory
import kotlinx.coroutines.delay

@Composable
fun KakaoMapScreen(
    appState: AppState,
    bottomSheetState: BottomSheetState,
    viewModel: KakaoMapViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ManageLocationPermission(
        addLocationListener = viewModel::addLocationListener,
        showSnackbar = appState::showSnackbar,
        removeLocationListener = viewModel::removeLocationListener
    )

    LaunchedEffect(uiState.movingCameraPosition) {
        when (val movingCameraPosition = uiState.movingCameraPosition) {
            MovingCameraWrapper.DEFAULT -> {
                // Do Nothing
            }

            is MovingCameraWrapper.MOVING -> {
                appState.kakaoCameraPositionState.move(
                    CameraUpdateFactory.newCenterPosition(LatLng.from(movingCameraPosition.location.latitude, movingCameraPosition.location.longitude)),
                    CameraAnimation.from(100, true, true)
                )
                viewModel.updateIsFixedPerspective(false)
                viewModel.updateMovingCameraPositionToDefault()
            }
        }
    }

    LaunchedEffect(uiState.isFixedPerspective) {
        while (true) {
            delay(100)
            Log.d("debugging", "이동 : ${uiState.isFixedPerspective}")

            if (uiState.isFixedPerspective) {
                appState.kakaoCameraPositionState.move(
                    CameraUpdateFactory.newCenterPosition(uiState.userPosition),
                    CameraAnimation.from(100, true, true)
                )
            } else {
                break
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        KakaoMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = appState.kakaoCameraPositionState,
            onMapReady = {
                if (KakaoMapViewModel.initialMarkerLoadFlag && uiState.userPosition != KakaoMapContract.DEFAULT_LATLNG) {
                    KakaoMapViewModel.initialMarkerLoadFlag = false
                    viewModel.updateMovingCameraPosition(
                        MovingCameraWrapper.MOVING(
                            Location("UserPosition").apply {
                                latitude = uiState.userPosition.latitude
                                longitude = uiState.userPosition.longitude
                            }
                        )
                    )
                }
            }
        ) {
            Label(
                position = uiState.userPosition,
                iconResId = R.drawable.img_current_location,
                tag = "나"
            ) {

            }
        }

        CurrentPositionIcon(
            enabled = uiState.isFixedPerspective
        ) {
            viewModel.updateIsFixedPerspective(!uiState.isFixedPerspective)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CurrentPositionIcon(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .padding(end = 14.dp, bottom = 28.dp),
        shape = RoundedCornerShape(10.dp),
        color = if (enabled) Color.Green else Color.White,
        elevation = 10.dp,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.padding(7.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_current_location),
                contentDescription = "IC_CURRENT_LOCATION",
                tint = if (enabled) Color.White else Color.Unspecified
            )
        }
    }
}