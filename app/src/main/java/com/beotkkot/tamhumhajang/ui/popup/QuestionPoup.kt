package com.beotkkot.tamhumhajang.ui.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.beotkkot.tamhumhajang.data.model.response.QuizQuestionPopup
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme

@Composable
fun QuestionPopup(
    popup: QuizQuestionPopup,
    onChoose: (String) -> Unit
) {
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .width(300.dp)
                .background(
                    color = TamhumhajangTheme.colors.color_ffffff,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(top = 30.dp, bottom = 18.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = popup.title,
                style = TamhumhajangTheme.typography.title2Description.copy(
                    color = TamhumhajangTheme.colors.color_000000
                )
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "아래의 문제를 맞추어, 돌발 상황을 해결해 보세요!",
                style = TamhumhajangTheme.typography.body2.copy(
                    fontWeight = FontWeight.Medium,
                    color = TamhumhajangTheme.colors.color_000000
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFF6F6F6))
                    .padding(vertical = 25.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = popup.question,
                    style = TamhumhajangTheme.typography.title3.copy(
                        color = TamhumhajangTheme.colors.color_0fa958,
                        fontSize = 18.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = TamhumhajangTheme.colors.color_0fa958,
                        contentColor = TamhumhajangTheme.colors.color_000000
                    ),
                    contentPadding = PaddingValues(vertical = 13.dp),
                    onClick = { onChoose(popup.positive) }
                ) {
                    Text(
                        text = popup.positive,
                        style = TamhumhajangTheme.typography.title3,
                        color = TamhumhajangTheme.colors.color_000000
                    )
                }

                Button(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFAC93F4),
                        contentColor = TamhumhajangTheme.colors.color_ffffff
                    ),
                    contentPadding = PaddingValues(vertical = 13.dp),
                    onClick = { onChoose(popup.negative) }
                ) {
                    Text(
                        text = popup.negative,
                        style = TamhumhajangTheme.typography.title3,
                        color = TamhumhajangTheme.colors.color_ffffff
                    )
                }
            }
        }
    }

}