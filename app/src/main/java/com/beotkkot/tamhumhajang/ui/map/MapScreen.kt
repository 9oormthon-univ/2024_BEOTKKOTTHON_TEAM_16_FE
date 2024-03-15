package com.beotkkot.tamhumhajang.ui.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.beotkkot.tamhumhajang.AppState
import com.beotkkot.tamhumhajang.BottomSheetState
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme
import com.beotkkot.tamhumhajang.kakaomap.KakaoMap

@Composable
fun MapScreen(
    appState: AppState,
    bottomSheetState: BottomSheetState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = TamhumhajangTheme.colors.white
            )
    ) {
        KakaoMap()
    }
}