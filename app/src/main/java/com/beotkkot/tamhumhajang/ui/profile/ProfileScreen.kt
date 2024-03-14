package com.beotkkot.tamhumhajang.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.beotkkot.tamhumhajang.AppState
import com.beotkkot.tamhumhajang.BottomSheetState
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme

@Composable
fun ProfileScreen(
    appState: AppState,
    bottomSheetState: BottomSheetState
) {
    Column(
        modifier = Modifier.fillMaxSize().background(
            color = TamhumhajangTheme.colors.white
        )
    ) {
        Text(
            text = "프로필"
        )
    }
}