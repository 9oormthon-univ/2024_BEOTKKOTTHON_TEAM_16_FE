package com.beotkkot.tamhumhajang.ui.toast

import androidx.compose.runtime.Composable
import com.beotkkot.tamhumhajang.R

@Composable
fun CheckProfileToast(
    onClick: () -> Unit
) {
    TamhumToast(
        leadingIcon = R.drawable.ic_toast_map,
        description = "보관한 트로피는 '도감'에서\n확인할 수 있어요!"
    ) {
        onClick()
    }
}