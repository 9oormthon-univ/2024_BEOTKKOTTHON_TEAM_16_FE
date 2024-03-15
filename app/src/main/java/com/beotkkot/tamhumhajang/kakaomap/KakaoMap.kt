package com.beotkkot.tamhumhajang.kakaomap

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView

@Composable
fun KakaoMap(
    modifier: Modifier = Modifier
) {
    val TAG = "kakaoMap"

    val context = LocalContext.current
    val mapView = MapView(context)

    AndroidView(
        modifier = modifier,
        factory = {
            mapView
        }
    )

    mapView.start(object : MapLifeCycleCallback() {
        override fun onMapDestroy() {
            Log.d(TAG, "Map destroyed")
        }

        override fun onMapError(p0: Exception?) {
            Log.d(TAG, p0.toString())
        }

    }, object : KakaoMapReadyCallback() {
        override fun onMapReady(p0: KakaoMap) {
            Log.d(TAG, "Map Ready")
        }
    })
}