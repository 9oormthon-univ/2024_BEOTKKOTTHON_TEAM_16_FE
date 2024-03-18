package com.beotkkot.tamhumhajang.ui.map

import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.beotkkot.tamhumhajang.AppState
import com.beotkkot.tamhumhajang.BottomSheetState
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme
import com.beotkkot.tamhumhajang.ui.map.MapContract.Companion.DEFAULT_LATLNG
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.overlay.OverlayImage
import kotlinx.coroutines.delay

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapScreen(
    appState: AppState,
    bottomSheetState: BottomSheetState,
    viewModel: MapViewModel
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
                appState.cameraPositionState.animate(
                    update = CameraUpdate.scrollTo(LatLng(movingCameraPosition.location))
                )
                viewModel.updateIsFixedPerspective(false)
                viewModel.updateMovingCameraPositionToDefault()
            }
        }
    }



    LaunchedEffect(uiState.isFixedPerspective) {
        while (true) {
            delay(100)
            if (uiState.isFixedPerspective) {
                appState.cameraPositionState.animate(
                    update = CameraUpdate.scrollAndZoomTo(
                        uiState.userPosition,
                        15.0
                    )
                )
            } else {
                break
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = TamhumhajangTheme.colors.color_ffffff
            )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            NaverMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = appState.cameraPositionState,
                properties = MapProperties(
                    locationTrackingMode = LocationTrackingMode.Follow
                ),
                onMapClick = { _, _ ->
                },
                onMapLoaded = {
                    if (MapViewModel.initialMarkerLoadFlag && uiState.userPosition != DEFAULT_LATLNG) {
                        MapViewModel.initialMarkerLoadFlag = false
                        viewModel.updateMovingCameraPosition(
                            MovingCameraWrapper.MOVING(
                                Location("UserPosition").apply {
                                    latitude = uiState.userPosition.latitude
                                    longitude = uiState.userPosition.longitude
                                }
                            )
                        )
                    }
                },
                uiSettings = MapUiSettings(
                    logoMargin = PaddingValues(
                        start = 12.dp,
                        bottom = 10.dp
                    )
                )
            ) {
                Marker(
                    state = MarkerState(position = uiState.userPosition),
                    icon = OverlayImage.fromResource(R.drawable.ic_current_location_disabled),
                    captionText = "나",
                    height = 30.dp,
                    width = 30.dp,
                    onClick = {
                        true
                    }
                )
            }

            CurrentPositionIcon(
                enabled = uiState.isFixedPerspective
            ) {
                viewModel.updateIsFixedPerspective(!uiState.isFixedPerspective)
            }
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
                painter = painterResource(id = R.drawable.ic_current_location_disabled),
                contentDescription = "IC_CURRENT_LOCATION",
                tint = if (enabled) Color.White else Color.Unspecified
            )
        }
    }
}