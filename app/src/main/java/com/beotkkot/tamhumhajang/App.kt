package com.beotkkot.tamhumhajang

import android.app.Application
import com.kakao.vectormap.KakaoMapSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoMapSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }

}