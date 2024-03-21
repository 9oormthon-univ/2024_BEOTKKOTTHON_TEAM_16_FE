package com.beotkkot.tamhumhajang.ui.toast

import androidx.compose.runtime.Composable
import com.beotkkot.tamhumhajang.R

@Composable
fun UseRewardToast(
    onClick: () -> Unit
) {
    TamhumToast(
        leadingIcon = R.drawable.ic_toast_reward,
        description = "시장상인회에서 바로\n트로피를 사용하시겠습니까?"
    ) {
        onClick()
    }
}