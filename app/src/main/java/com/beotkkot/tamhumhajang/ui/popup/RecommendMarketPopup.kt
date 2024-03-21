package com.beotkkot.tamhumhajang.ui.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.data.model.RecommendMarket
import com.beotkkot.tamhumhajang.design.component.TamhumPopup
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme

val marketsDummy = listOf(
    RecommendMarket(
        0,
        "'다 있는 의정부 제일 시장'을 추천드립니다.\n함께 의정부 제일 시장을 탐색하시겠습니까?",
        "",
        0.0,
        0.0
    ),
    RecommendMarket(
        0,
        "'맛집으로 넘치는 마천 시장'을 추천드립니다.\n함께 마천 시장을 탐색하시겠습니까?",
        "",
        0.0,
        0.0
    ),
    RecommendMarket(
        0,
        "'싱싱한 수산물 가득 가락 시장'을 추천드립니다.\n함께 가락 시장을 탐색하시겠습니까?",
        "",
        0.0,
        0.0
    )
)

@Composable
fun RecommendMarketPopup(
    markets: List<RecommendMarket> = marketsDummy,
    onClose: () -> Unit = { }
) {
    var selectedMarketPosition by remember { mutableIntStateOf(1) }
    val currentMarket = markets[selectedMarketPosition]

    val context = LocalContext.current

    TamhumPopup(
        onDismissRequest = onClose
    ) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .padding(
                    top = 40.dp,
                    bottom = 25.dp,
                    start = 20.dp,
                    end = 20.dp
                )
                .background(
                    color = TamhumhajangTheme.colors.color_ffffff
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "추천 시장\uD83D\uDCAB",
                    style = TamhumhajangTheme.typography.title2Description.copy(
                        color = TamhumhajangTheme.colors.color_000000
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = currentMarket.description,
                    style = TamhumhajangTheme.typography.body1.copy(
                        color = TamhumhajangTheme.colors.color_000000
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(currentMarket.imgUrl.ifEmpty { R.drawable.img_dummy_shop })
                        .build(),
                    contentDescription = "IMG_SHOP",
                    modifier = Modifier
                        .size(width = 170.dp, height = 160.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFAC93F4),
                        contentColor = TamhumhajangTheme.colors.color_ffffff
                    ),
                    contentPadding = PaddingValues(vertical = 13.dp),
                    onClick = {
                        onClose()
                    }
                ) {
                    Text(
                        text = "추천시장 이동하기",
                        style = TamhumhajangTheme.typography.title3,
                        color = TamhumhajangTheme.colors.color_ffffff
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (selectedMarketPosition > 0) {
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable { selectedMarketPosition -= 1 },
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = "IC_ARROW_LEFT",
                        tint = Color.Unspecified
                    )
                } else {
                    Spacer(modifier = Modifier.size(1.dp))
                }

                if (selectedMarketPosition < markets.size - 1) {
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable { selectedMarketPosition += 1 },
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = "IC_ARROW_LEFT",
                        tint = Color.Unspecified
                    )
                } else {
                    Spacer(modifier = Modifier.size(1.dp))
                }
            }
        }
    }
}