package com.beotkkot.tamhumhajang.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.beotkkot.tamhumhajang.AppState
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    appState: AppState
) {
    LaunchedEffect(true) {
        delay(2000)
        appState.navigate("kakaomap")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = TamhumhajangTheme.colors.color_0fa958),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.mipmap.ic_splash_logo),
            contentDescription = "IC_SPLASH_LOGO"
        )
    }
}