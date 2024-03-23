package com.beotkkot.tamhumhajang.ui.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.data.model.response.BadgePopup
import com.beotkkot.tamhumhajang.data.model.response.QuizWarningPopup
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme

@Preview
@Composable
fun EventBadgePopup(
    popup: QuizWarningPopup = QuizWarningPopup(
        title = "녹두 하나 주면 안 잡아먹지!",
        name = "생쥐 (찍)",
        image = "",
        confirm = "녹두 구하러 가기"
    ),
    onConfirm: () -> Unit = { },
    navigateToProfile: () -> Unit = { },
    onClose: () -> Unit = { }
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
                style = TamhumhajangTheme.typography.title2Description.copy(
                    color = TamhumhajangTheme.colors.color_000000
                )
            )

            Spacer(modifier = Modifier.height(35.dp))

            Text(
                text = popup.name,
                style = TamhumhajangTheme.typography.title2Description.copy(
                    color = TamhumhajangTheme.colors.color_0fa958
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(35.dp))

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(popup.image.ifEmpty { R.drawable.img_mouse })
                    .build(),
                contentDescription = "IMG_BADGE",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(35.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = TamhumhajangTheme.colors.color_0fa958,
                    contentColor = TamhumhajangTheme.colors.color_9ddb80
                ),
                contentPadding = PaddingValues(vertical = 13.dp),
                onClick = { navigateToProfile() }
            ) {
                Text(
                    text = popup.confirm,
                    style = TamhumhajangTheme.typography.title2Description,
                    color = TamhumhajangTheme.colors.color_000000
                )
            }
        }
    }
}