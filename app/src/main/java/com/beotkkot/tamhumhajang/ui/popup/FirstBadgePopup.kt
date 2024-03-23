package com.beotkkot.tamhumhajang.ui.popup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme

@Composable
fun FirstBadgePopup(
    onClick: () -> Unit,
    onClose: () -> Unit
) {
    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .width(300.dp)
                .background(
                    color = TamhumhajangTheme.colors.color_ffffff,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(top = 40.dp, bottom = 24.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "첫 배지 획득\uD83D\uDD25",
                style = TamhumhajangTheme.typography.largeTitle.copy(
                    color = TamhumhajangTheme.colors.color_000000
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = TamhumhajangTheme.colors.color_0fa958)) {
                        append("용감한 탐험가")
                    }
                    append("가 되신 것을 축하드립니다!\n탐험가 배지를 획득하셨어요.")
                },
                style = TamhumhajangTheme.typography.body2.copy(
                    color = TamhumhajangTheme.colors.color_000000
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.img_brave_explorer),
                contentDescription = "IMG_BRAVE_EXPLORER"
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "시장을 바로 탐색하시겠습니까?",
                style = TamhumhajangTheme.typography.body1.copy(
                    color = TamhumhajangTheme.colors.color_000000
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(18.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = TamhumhajangTheme.colors.color_9ddb80,
                    contentColor = TamhumhajangTheme.colors.color_ffffff
                ),
                contentPadding = PaddingValues(vertical = 13.dp),
                onClick = { onClick() }
            ) {
                Text(
                    text = "탐색하기",
                    style = TamhumhajangTheme.typography.title3,
                    color = TamhumhajangTheme.colors.color_ffffff
                )
            }
        }
    }
}