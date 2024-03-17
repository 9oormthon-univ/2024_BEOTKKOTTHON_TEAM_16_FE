package com.beotkkot.kakaomap_compose.extension

import com.beotkkot.kakaomap_compose.state.CameraPositionState
import com.kakao.vectormap.label.Label
import com.kakao.vectormap.label.TrackingManager

internal fun TrackingManager.startTrackingCompat(label: Label, cameraPositionState: CameraPositionState?) {
    startTracking(label)
    cameraPositionState?.onLabelTrackStarted(label.position)
}

internal fun TrackingManager.stopTrackingCompat(cameraPositionState: CameraPositionState?) {
    stopTracking()
    cameraPositionState?.onLabelTrackStopped()
}