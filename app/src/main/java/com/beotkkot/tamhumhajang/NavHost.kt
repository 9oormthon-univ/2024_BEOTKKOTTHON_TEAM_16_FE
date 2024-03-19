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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme
import com.beotkkot.tamhumhajang.ui.BOOKMARK
import com.beotkkot.tamhumhajang.ui.LOGIN
import com.beotkkot.tamhumhajang.ui.LoginScreen
import com.beotkkot.tamhumhajang.ui.MAP
import com.beotkkot.tamhumhajang.ui.SPLASH
import com.beotkkot.tamhumhajang.ui.SplashScreen
import com.beotkkot.tamhumhajang.ui.bookmark.BookmarkScreen
import com.beotkkot.tamhumhajang.ui.kakaomap.KakaoMapScreen
import com.beotkkot.tamhumhajang.ui.kakaomap.KakaoMapViewModel

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
                startDestination = SPLASH
            ) {
                composable(SPLASH) {
                    SplashScreen(appState = appState)
                }
                
                composable(LOGIN) {
                    LoginScreen(appState = appState)
                }

                composable(MAP) {
                    val viewModel: KakaoMapViewModel = hiltViewModel()

                    KakaoMapScreen(appState = appState, bottomSheetState = bottomSheetState, viewModel = viewModel)
                }

                composable(BOOKMARK) {
                    BookmarkScreen(appState = appState, bottomSheetState = bottomSheetState)
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