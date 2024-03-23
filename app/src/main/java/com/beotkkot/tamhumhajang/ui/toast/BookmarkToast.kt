package com.beotkkot.tamhumhajang.ui.toast

import androidx.compose.runtime.Composable
import com.beotkkot.tamhumhajang.R

@Composable
fun BookmarkToast(
    onClick: () -> Unit
) {
    TamhumToast(
        leadingIcon = R.drawable.ic_bookmark,
        description = "마음에 드는 상점을 클릭해서\n즐겨찾기를 등록해보아요!"
    ) {
        onClick()
    }
}