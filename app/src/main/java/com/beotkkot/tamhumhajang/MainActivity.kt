package com.beotkkot.tamhumhajang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // WindowInset 직접 조절하기 위해서
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            TamhumhajangTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = TamhumhajangTheme.colors.white
                ) {
                    NavHost()
                }
            }
        }
    }
}