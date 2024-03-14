package com.beotkkot.tamhumhajang.design.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class Colors(
    white: Color = Color(0xFFFFFFF),
    purple80: Color = Color(0xFFD0BCFF),
    purpleGrey80: Color = Color(0xFFCCC2DC),
    pink80: Color = Color(0xFFEFB8C8),
    purple40: Color = Color(0xFF6650a4),
    purpleGrey40: Color = Color(0xFF625b71),
    pink40: Color = Color(0xFF7D5260)
) {
    var white by mutableStateOf(white)
        private set
    var purple80 by mutableStateOf(purple80)
        private set
    var purpleGrey80 by mutableStateOf(purpleGrey80)
        private set
    var pink80 by mutableStateOf(pink80)
        private set
    var purple40 by mutableStateOf(purple40)
        private set
    var purpleGrey40 by mutableStateOf(purpleGrey40)
        private set
    var pink40 by mutableStateOf(pink40)
        private set

    fun copy(
        white: Color = this.white,
        purple80: Color = this.purple80,
        purpleGrey80: Color = this.purpleGrey80,
        pink80: Color = this.pink80,
        purple40: Color = this.purple40,
        purpleGrey40: Color = this.purpleGrey40,
        pink40: Color = this.pink40
    ) = Colors(
        white = white,
        purple80 = purple80,
        purpleGrey80 = purpleGrey80,
        pink80 = pink80,
        purple40 = purple40,
        purpleGrey40 = purpleGrey40,
        pink40 = pink40
    )

    fun updateColorFrom(other: Colors) {
        white = other.white
        purple80 = other.purple80
        purpleGrey80 = other.purpleGrey80
        pink80 = other.pink80
        purple40 = other.purple40
        purpleGrey40 = other.purpleGrey40
        pink40 = other.pink40
    }
}

val LocalColors = staticCompositionLocalOf { colors() }