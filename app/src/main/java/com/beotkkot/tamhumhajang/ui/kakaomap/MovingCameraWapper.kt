package com.beotkkot.tamhumhajang.ui.kakaomap

import android.location.Location


sealed class MovingCameraWrapper {
    object DEFAULT : MovingCameraWrapper()
    class MOVING(val location: Location) : MovingCameraWrapper()

    val isMoving: Boolean
        get() = this is MOVING
}

