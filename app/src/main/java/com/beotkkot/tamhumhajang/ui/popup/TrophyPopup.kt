package com.beotkkot.tamhumhajang.ui.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.design.component.TamhumPopup
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme

@Composable
fun TrophyPopup(
    onClose: () -> Unit = { }
) {
    val context = LocalContext.current

    TamhumPopup(
        onDismissRequest = { onClose() }
    ) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .padding(
                    top = 40.dp,
                    bottom = 25.dp,
                    start = 20.dp,
                    end = 20.dp
                )
                .background(
                    color = TamhumhajangTheme.colors.color_ffffff
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "축하드립니다\uD83E\uDD73 ",
                    style = TamhumhajangTheme.typography.title1.copy(
                        color = TamhumhajangTheme.colors.color_000000
                    )
                )

                Spacer(modifier = Modifier.height(13.dp))

                Text(
                    text = "꺄 트로피네요! \u2028\n퀘스트 달성 완료를 축하드립니다.\n" +
                            "상인회로 이동해서 상품을 받아보세요✨",
                    style = TamhumhajangTheme.typography.body2.copy(
                        color = TamhumhajangTheme.colors.color_000000
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(21.dp))

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(R.drawable.img_trophy)
                        .build(),
                    contentDescription = "IMG_TROPHY",
                    modifier = Modifier
                        .size(width = 120.dp, height = 120.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(36.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFAC93F4),
                        contentColor = TamhumhajangTheme.colors.color_ffffff
                    ),
                    contentPadding = PaddingValues(vertical = 13.dp),
                    onClick = {
                        onClose()
                    }
                ) {
                    Text(
                        text = "시장상인회 이동하기",
                        style = TamhumhajangTheme.typography.body2,
                        color = TamhumhajangTheme.colors.color_ffffff
                    )
                }


                Spacer(modifier = Modifier.height(7.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = TamhumhajangTheme.colors.color_d9d9d9,
                        contentColor = TamhumhajangTheme.colors.color_ffffff
                    ),
                    contentPadding = PaddingValues(vertical = 13.dp),
                    onClick = {
                        onClose()
                    }
                ) {
                    Text(
                        text = "다음에 받기",
                        style = TamhumhajangTheme.typography.description2,
                        color = TamhumhajangTheme.colors.color_000000
                    )
                }
            }
        }
    }
}
