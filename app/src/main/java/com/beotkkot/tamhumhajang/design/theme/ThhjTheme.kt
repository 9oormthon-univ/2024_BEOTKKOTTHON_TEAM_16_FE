package com.beotkkot.tamhumhajang.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object ThhjTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}