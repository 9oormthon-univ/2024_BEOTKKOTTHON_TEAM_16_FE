package com.beotkkot.tamhumhajang.design.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomNavigationItem(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    @DrawableRes selectedIcon: Int,
    @DrawableRes unselectedIcon: Int,
    selectedContentColor: Color,
    unselectedContentColor: Color,
    onClick: () -> Unit
) {
    val contentColor = if (selected) selectedContentColor else unselectedContentColor

    Surface(
        modifier = modifier,
        color = Color.Transparent,
        onClick = onClick,
        interactionSource = NoRippleInteractionSource()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = if (selected) selectedIcon else unselectedIcon),
                contentDescription = label,
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = label,
                style = TamhumhajangTheme.typography.bodyLarge,
                color = contentColor
            )
        }
    }
}