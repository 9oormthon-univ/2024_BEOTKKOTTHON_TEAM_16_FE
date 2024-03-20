package com.beotkkot.tamhumhajang.ui.map

import android.location.Location
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.beotkkot.kakaomap_compose.state.rememberCameraPositionState
import com.beotkkot.tamhumhajang.AppState
import com.beotkkot.tamhumhajang.BottomSheetState
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme
import com.beotkkot.tamhumhajang.ui.BOOKMARK
import com.beotkkot.tamhumhajang.ui.PROFILE
import com.beotkkot.tamhumhajang.ui.bookmark.ShopBottomSheet
import com.beotkkot.tamhumhajang.ui.popup.RecommendMarketPopup
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.camera.CameraAnimation
import com.kakao.vectormap.camera.CameraUpdateFactory
import kotlinx.coroutines.delay

@Composable
fun MapScreen(
    appState: AppState,
    bottomSheetState: BottomSheetState,
    viewModel: MapViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val cameraPositionState = rememberCameraPositionState()

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
                cameraPositionState.move(
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
                cameraPositionState.move(
                    CameraUpdateFactory.newCenterPosition(uiState.userPosition),
                    CameraAnimation.from(100, true, true)
                )
            } else {
                break
            }
        }
    }

    if (uiState.showRecommendShopPopup) {
        RecommendMarketPopup {
            viewModel.updateShowRecommendShopPopup(false)
        }
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        KakaoMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapReady = {
                if (MapViewModel.initialMarkerLoadFlag && uiState.userPosition != MapContract.DEFAULT_LATLNG) {
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
            }
        ) {
            Label(
                position = uiState.userPosition,
                iconResId = R.drawable.img_explorer_level_1,
                tag = "나"
            ) {

            }

            Label(
                position = LatLng.from(uiState.userPosition.latitude + 0.0005, uiState.userPosition.longitude + 0.0005),
                iconResId = R.drawable.img_shop_location,
                tag = "나",
                onClick = {
                    bottomSheetState.showBottomSheet {
                        ShopBottomSheet()
                    }
                }
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.dp, top = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BookButton {
                appState.navigate(PROFILE)
            }

            BookmarkButton {
                appState.navigate(BOOKMARK)
            }
        }

        Column(
            modifier = Modifier.padding(end = 14.dp, bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            QuestButton {

            }

            ShopButton {
                viewModel.updateShowRecommendShopPopup(true)
            }

            TrackingButton(
                isTracking = uiState.isFixedPerspective
            ) {
                viewModel.updateIsFixedPerspective(!uiState.isFixedPerspective)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BookButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier.size(48.dp),
        shape = RoundedCornerShape(10.dp),
        color = TamhumhajangTheme.colors.color_ffffff,
        elevation = 10.dp,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_book),
                contentDescription = "IC_BOOK",
                tint = Color.Unspecified
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BookmarkButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier.size(48.dp),
        shape = RoundedCornerShape(10.dp),
        color = TamhumhajangTheme.colors.color_ffffff,
        elevation = 10.dp,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_star),
                contentDescription = "IC_STAR",
                tint = Color.Unspecified
            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun QuestButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier.size(48.dp),
        shape = RoundedCornerShape(10.dp),
        color = TamhumhajangTheme.colors.color_ffffff,
        elevation = 10.dp,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_quest),
                contentDescription = "IC_QUEST",
                tint = Color.Unspecified
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ShopButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier.size(48.dp),
        shape = RoundedCornerShape(10.dp),
        color = TamhumhajangTheme.colors.color_ffffff,
        elevation = 10.dp,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_shop),
                contentDescription = "IC_SHOP",
                tint = Color.Unspecified
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun TrackingButton(
    modifier: Modifier = Modifier,
    isTracking: Boolean = true,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier.size(48.dp),
        shape = RoundedCornerShape(10.dp),
        color = if (isTracking) TamhumhajangTheme.colors.color_0fa958 else TamhumhajangTheme.colors.color_ffffff,
        elevation = 10.dp,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = if (isTracking) R.drawable.ic_current_location_enabled else  R.drawable.ic_current_location_disabled),
                contentDescription = "IC_CURRENT_LOCATION",
                tint = if (isTracking) Color.White else Color.Unspecified
            )
        }
    }
}