package com.beotkkot.tamhumhajang.ui.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme

@Preview
@Composable
fun ConnectionPopup(
    onClose: () -> Unit = { }
) {
    val isButtonClicked = remember { mutableStateOf(false) }
    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = TamhumhajangTheme.colors.color_ffffff
        ) {
            Box(
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Min)
                    .padding(
                        top = 20.dp,
                        bottom = 25.dp,
                        start = 20.dp,
                        end = 20.dp
                    )
                    .background(
                        color = TamhumhajangTheme.colors.color_ffffff,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .padding(
                            top = 20.dp,
                            end = 20.dp
                        )
                        .align(
                            Alignment.TopEnd
                        )
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "첫 번째 퀘스트의 \n" +
                                "마지막 배지\uD83E\uDD29",
                        style = TamhumhajangTheme.typography.title2Description.copy(
                            color = TamhumhajangTheme.colors.color_000000
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "맘에 드는 상점을 단골로 등록해보세요!",
                        style = TamhumhajangTheme.typography.body1.copy(
                            color = TamhumhajangTheme.colors.color_000000
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Icon(
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                            .clip(CircleShape)
                            .clickable {
                            },
                        painter = painterResource(id = R.drawable.img_bag_shadow),
                        contentDescription = "IC_CLOSE"
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "단골로 등록만 해도 배지를 획득할 수 있다구?!",
                        style = TamhumhajangTheme.typography.body2.copy(
                            color = TamhumhajangTheme.colors.color_0fa958
                        ),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(280.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF9DDB80),
                            contentColor = TamhumhajangTheme.colors.color_9ddb80
                        ),
                        contentPadding = PaddingValues(vertical = 10.dp),
                        onClick = {
                            isButtonClicked.value = !isButtonClicked.value
                        }
                    ) {
                        Text(
                            text = "마지막 배지 획득하러 가기",
                            style = TamhumhajangTheme.typography.body1,
                            color = TamhumhajangTheme.colors.color_ffffff
                        )
                    }
                }
            }
        }
    }
}