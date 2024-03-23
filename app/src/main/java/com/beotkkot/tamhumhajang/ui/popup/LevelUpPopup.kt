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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.data.model.response.LevelUpResponse
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme
@Composable
fun LevelUpPopup(
    popup: LevelUpResponse,
    onClick: () -> Unit,
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
                .padding(top = 13.dp, bottom = 24.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier.width(83.dp).height(50.dp),
                model = ImageRequest.Builder(context)
                    .data(popup.tierImgUrl.ifEmpty { R.drawable.ic_level_silver })
                    .build(),
                contentDescription = "IMG_TIER",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = popup.title,
                style = TamhumhajangTheme.typography.largeTitle.copy(
                    color = TamhumhajangTheme.colors.color_000000
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = popup.grade,
                style = TamhumhajangTheme.typography.title2Description.copy(
                    color = TamhumhajangTheme.colors.color_000000
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(popup.characterImgUrl.ifEmpty { R.drawable.img_silver })
                    .build(),
                contentDescription = "IMG_TIER",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = popup.description,
                style = TamhumhajangTheme.typography.body2.copy(
                    color = TamhumhajangTheme.colors.color_000000
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFAC93F4),
                    contentColor = TamhumhajangTheme.colors.color_ffffff
                ),
                contentPadding = PaddingValues(vertical = 13.dp),
                onClick = { onClick() }
            ) {
                Text(
                    text = "이어서 탐색하기",
                    style = TamhumhajangTheme.typography.title3,
                    color = TamhumhajangTheme.colors.color_ffffff
                )
            }
        }
    }
}