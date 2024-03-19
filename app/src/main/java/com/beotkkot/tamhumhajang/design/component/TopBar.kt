package com.beotkkot.tamhumhajang.design.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme

@Preview
@Composable
fun TopBar(
    content: String = "",
    @DrawableRes leadingIcon: Int = R.drawable.ic_arrow_back,
    leadingIconOnClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = leadingIcon),
            contentDescription = "IC_ARROW_BACK",
            tint = Color.Unspecified,
            modifier = Modifier
                .padding(start = 16.dp)
                .clip(CircleShape)
                .align(Alignment.CenterStart)
                .clickable { leadingIconOnClick() }
        )

        Text(
            text = content,
            style = TamhumhajangTheme.typography.title1.copy(
                fontWeight = FontWeight.Bold,
                color = TamhumhajangTheme.colors.color_000000
            )
        )
    }
}