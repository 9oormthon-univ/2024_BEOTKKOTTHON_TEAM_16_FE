package com.beotkkot.tamhumhajang

import android.app.Application
import android.util.Log
import com.kakao.vectormap.KakaoMapSdk

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("debugging", "앱 초기화")

        KakaoMapSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }

}