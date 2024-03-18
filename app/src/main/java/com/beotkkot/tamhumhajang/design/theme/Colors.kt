package com.beotkkot.tamhumhajang.design.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class Colors(
    color_ffffff: Color = Color(0xFFFFFFFF),
    color_000000: Color = Color(0x00000000),
    color_9ddb80: Color = Color(0xFF9DDB80),
    color_eaf8e4: Color = Color(0xFFEAF8E4),
    color_0fa958: Color = Color(0xFF0FA958),
    color_e0f3d7: Color = Color(0xFFE0F3D7),
    color_bebebe: Color = Color(0xFFBEBEBE),
    color_d9d9d9: Color = Color(0xFFD9D9D9)
) {
    var color_ffffff by mutableStateOf(color_ffffff)
        private set
    var color_000000 by mutableStateOf(color_000000)
        private set
    var color_9ddb80 by mutableStateOf(color_9ddb80)
        private set
    var color_eaf8e4 by mutableStateOf(color_eaf8e4)
        private set
    var color_0fa958 by mutableStateOf(color_0fa958)
        private set
    var color_e0f3d7 by mutableStateOf(color_e0f3d7)
        private set
    var color_bebebe by mutableStateOf(color_bebebe)
        private set
    var color_d9d9d9 by mutableStateOf(color_d9d9d9)
        private set

    fun copy(
        color_ffffff: Color = this.color_ffffff,
        color_000000: Color = this.color_000000,
        color_9ddb80: Color = this.color_9ddb80,
        color_eaf8e4: Color = this.color_eaf8e4,
        color_0fa958: Color = this.color_0fa958,
        color_e0f3d7: Color = this.color_e0f3d7,
        color_bebebe: Color = this.color_bebebe,
        color_d9d9d9: Color = this.color_d9d9d9
    ) = Colors(
        color_ffffff = color_ffffff,
        color_000000 = color_000000,
        color_9ddb80 = color_9ddb80,
        color_eaf8e4 = color_eaf8e4,
        color_0fa958 = color_0fa958,
        color_e0f3d7 = color_e0f3d7,
        color_bebebe = color_bebebe,
        color_d9d9d9 = color_d9d9d9
    )

    fun updateColorFrom(other: Colors) {
        color_ffffff = other.color_ffffff
        color_000000 = other.color_000000
        color_9ddb80 = other.color_9ddb80
        color_eaf8e4 = other.color_eaf8e4
        color_0fa958 = other.color_0fa958
        color_e0f3d7 = other.color_e0f3d7
        color_bebebe = other.color_bebebe
        color_d9d9d9 = other.color_d9d9d9
    }
}

val LocalColors = staticCompositionLocalOf { colors() }