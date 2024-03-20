package com.beotkkot.tamhumhajang.design.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme

@Preview
@Composable
fun TamhumPopup(
    onDismissRequest: () -> Unit = { },
    content: @Composable () -> Unit = { },
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = TamhumhajangTheme.colors.color_ffffff
        ) {
            Box(
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Min),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier.padding(
                        top = 23.dp,
                        end = 18.dp
                    ).align(
                        Alignment.TopEnd
                    )
                ) {
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                onDismissRequest()
                            },
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "IC_CLOSE"
                    )
                }

                content()
            }
        }
    }
}