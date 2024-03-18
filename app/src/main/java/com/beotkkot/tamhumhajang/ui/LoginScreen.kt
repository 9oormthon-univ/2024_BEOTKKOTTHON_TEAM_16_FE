package com.beotkkot.tamhumhajang.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.beotkkot.tamhumhajang.AppState
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme

@Composable
fun LoginScreen(
    appState: AppState
) {
    var nickname by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = TamhumhajangTheme.colors.color_0fa958),
        contentAlignment = Alignment.Center
    ) {
       Column(
           modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           Image(
               modifier = Modifier.height(204.dp),
               painter = painterResource(id = R.mipmap.ic_splash_logo),
               contentDescription = "IMG_SPLASH_LOGO"
           )

           Spacer(modifier = Modifier.height(40.dp))

           BasicTextField(
               value = nickname,
               onValueChange = {
                   nickname = it
               },
               singleLine = true,
               cursorBrush = SolidColor(Color.White),
               textStyle = TamhumhajangTheme.typography.title3.copy(
                   color = TamhumhajangTheme.colors.color_000000
               ),
               decorationBox = { innerTextField ->
                   Row(
                       modifier = Modifier
                           .fillMaxWidth()
                           .background(
                               color = TamhumhajangTheme.colors.color_ffffff,
                               shape = RoundedCornerShape(30.dp)
                           )
                           .padding(
                               horizontal = 20.dp,
                               vertical = 12.dp
                           )
                   ) {
                       if (nickname.isEmpty()) {
                           Text(
                               text = "닉네임",
                               style = TamhumhajangTheme.typography.title3.copy(
                                   color = Color(0xFF858585)
                               )
                           )
                       }
                       innerTextField()
                   }
               }
           )

           Spacer(modifier = Modifier.height(20.dp))

           Button(
               modifier = Modifier.fillMaxWidth(),
               shape = RoundedCornerShape(12.dp),
               colors = ButtonDefaults.buttonColors(
                   backgroundColor = Color(0xFFAC93F4),
                   contentColor = TamhumhajangTheme.colors.color_ffffff
               ),
               contentPadding = PaddingValues(vertical = 13.dp),
               onClick = {
                   appState.navigate("kakaomap")
               }
           ) {
               Text(
                   text = "시작하기",
                   style = TamhumhajangTheme.typography.title3,
                   color = TamhumhajangTheme.colors.color_ffffff
               )
           }
       }
    }
}