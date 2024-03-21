package com.beotkkot.tamhumhajang.ui.toast

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TamhumToast(
    @DrawableRes leadingIcon: Int,
    description: String,
    @DrawableRes trailingIcon: Int? = R.drawable.ic_toast_arrow,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.
            background(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFF505866)
            )
            .padding(
                horizontal = 16.dp,
                vertical = 10.dp
            ),
        color = Color(0xFF505866),
        elevation = 10.dp,
        onClick = onClick
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = leadingIcon),
                contentDescription = "IC_TOAST_LEADING_ICON",
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                modifier = Modifier.fillMaxWidth().weight(1f),
                text = description,
                style = TamhumhajangTheme.typography.title3.copy(
                    color = TamhumhajangTheme.colors.color_ffffff
                )
            )

            trailingIcon?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = "IC_TOAST_ARROW",
                    tint = Color.Unspecified
                )
            }
        }
    }
}