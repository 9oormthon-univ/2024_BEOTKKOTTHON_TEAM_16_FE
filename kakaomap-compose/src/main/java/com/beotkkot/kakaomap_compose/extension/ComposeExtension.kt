package com.beotkkot.kakaomap_compose.extension

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import com.beotkkot.kakaomap_compose.internal.MapApplier
import com.kakao.vectormap.KakaoMap

internal val NoPadding = PaddingValues()

@Composable
internal fun getMap(): KakaoMap = (currentComposer.applier as MapApplier).map