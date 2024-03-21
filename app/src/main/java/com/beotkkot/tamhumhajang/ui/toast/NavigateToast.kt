package com.beotkkot.tamhumhajang.ui.toast

import androidx.compose.runtime.Composable
import com.beotkkot.tamhumhajang.R

@Composable
fun NavigateToast(
    name: String,
    onClick: () -> Unit
) {
    TamhumToast(
        leadingIcon = R.drawable.ic_toast_map,
        description = "$name '카카오맵'으로 길찾기"
    ) {
        onClick()
    }
}