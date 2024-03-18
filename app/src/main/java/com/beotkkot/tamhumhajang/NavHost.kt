package com.beotkkot.tamhumhajang

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.beotkkot.tamhumhajang.design.component.BottomNavigationBar
import com.beotkkot.tamhumhajang.design.component.BottomNavigationItem
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme
import com.beotkkot.tamhumhajang.ui.kakaomap.KakaoMapScreen
import com.beotkkot.tamhumhajang.ui.kakaomap.KakaoMapViewModel
import com.beotkkot.tamhumhajang.ui.map.MapScreen
import com.beotkkot.tamhumhajang.ui.map.MapViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NavHost() {
    val appState = rememberAppState()
    val bottomSheetState = rememberWineyBottomSheetState()
    val navController = appState.navController

    ModalBottomSheetLayout(
        sheetContent = {
            bottomSheetState.bottomSheetContent.value?.invoke(this)
        },
        sheetState = bottomSheetState.bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp),
        modifier = Modifier.navigationBarsPadding()
    ) {
        Scaffold(
            backgroundColor = TamhumhajangTheme.colors.color_ffffff,
            snackbarHost = {
                SnackBar(appState)
            }
        ) { padding ->
            NavHost(
                modifier = Modifier
                    .padding(padding)
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                navController = navController,
                startDestination = "kakaomap"
            ) {
                composable(
                    route = "map"
                ) {
                    val viewModel: MapViewModel = hiltViewModel()

                    MapScreen(
                        appState = appState,
                        bottomSheetState = bottomSheetState,
                        viewModel = viewModel
                    )
                }

                composable(route = "kakaomap") {
                    val viewModel: KakaoMapViewModel = hiltViewModel()

                    KakaoMapScreen(appState = appState, bottomSheetState = bottomSheetState, viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
private fun SnackBar(appState: AppState) {
    SnackbarHost(hostState = appState.scaffoldState.snackbarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(
                    bottom = 30.dp,
                    start = 20.dp,
                    end = 20.dp
                )
            ) {
                Text(text = data.message)
            }
        }
    )
}

@Composable
private fun WineyBottomNavigationBar(
    modifier: Modifier = Modifier,
    destinations: List<TopLevelDestination>,
    currentDestination: NavDestination?,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    backgroundColor: Color = TamhumhajangTheme.colors.color_ffffff,
    selectedContentColor: Color = Color(0xFF1A1E27),
    unselectedContentColor: Color = Color(0xFFDADADA)
) {
    BottomNavigationBar(
        modifier = modifier,
        backgroundColor = backgroundColor
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination?.route == destination.route

            BottomNavigationItem(
                label = destination.label,
                selected = selected,
                selectedIcon = destination.selectedIcon,
                unselectedIcon = destination.unselectedIcon,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,
                onClick = {
                    if (!selected) {
                        onNavigateToDestination(destination)
                    }
                },
            )
        }
    }
}