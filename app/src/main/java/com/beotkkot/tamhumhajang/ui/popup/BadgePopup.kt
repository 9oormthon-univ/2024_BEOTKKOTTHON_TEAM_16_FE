package com.beotkkot.tamhumhajang.ui.popup

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme
import com.beotkkot.tamhumhajang.model.BadgePopup

val badgePopupDummy = BadgePopup(
    title = "탐색의 시작 배지 획득\uD83D\uDD25",
    description = "시장의 첫 탐색의 시작을 축하드립니다!\n탐색의 시작 배지를 획득하셨어요.",
    imgUrl = "",
    positive = "이어서 탐험하기",
    negative = "도감 이동하기"
)

@Composable
fun BadgePopup(
    popup: BadgePopup = badgePopupDummy,
    onConfirm: () -> Unit,
    navigateToProfile: () -> Unit,
    onClose: () -> Unit
) {
    val context = LocalContext.current

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
                text = popup.title,
                style = TamhumhajangTheme.typography.largeTitle.copy(
                    color = TamhumhajangTheme.colors.color_000000
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = popup.description,
                style = TamhumhajangTheme.typography.title2Description.copy(
                    color = TamhumhajangTheme.colors.color_000000
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(popup.imgUrl.ifEmpty { R.drawable.img_brave_explorer })
                    .build(),
                contentDescription = "IMG_BADGE",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(35.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = TamhumhajangTheme.colors.color_9ddb80,
                    contentColor = TamhumhajangTheme.colors.color_9ddb80
                ),
                contentPadding = PaddingValues(vertical = 13.dp),
                onClick = { onConfirm() }
            ) {
                Text(
                    text = "이어서 탐색하기",
                    style = TamhumhajangTheme.typography.title3,
                    color = TamhumhajangTheme.colors.color_ffffff
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = TamhumhajangTheme.colors.color_bebebe,
                    contentColor = TamhumhajangTheme.colors.color_9ddb80
                ),
                contentPadding = PaddingValues(vertical = 13.dp),
                onClick = { navigateToProfile() }
            ) {
                Text(
                    text = "도감 이동하기",
                    style = TamhumhajangTheme.typography.title3,
                    color = TamhumhajangTheme.colors.color_000000
                )
            }
        }
    }
}