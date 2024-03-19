package com.beotkkot.tamhumhajang.ui.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme

@Preview
@Composable
fun ShopBottomSheet(
    id: Int = 0,
    title: String = "청년 채소가게",
    isBookmarked: Boolean = true,
    address: String = "서울 송파구 마천동, 상인 3호점",
    category: String = "채소 상점",
    imgUrl: String = "",
    tags: List<String> = listOf("양상추", "시금치", "콩나물"),
    onBookmark: (Int) -> Unit = { }
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(TamhumhajangTheme.colors.color_ffffff)
            .padding(horizontal = 40.dp, vertical = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = title,
                style = TamhumhajangTheme.typography.largeTitle.copy(
                    color = TamhumhajangTheme.colors.color_000000
                )
            )

            Icon(
                modifier = Modifier.clickable {
                    onBookmark(id)
                }.align(Alignment.CenterEnd),
                painter = painterResource(id = R.drawable.ic_bookmark),
                contentDescription = "IC_BOOKMARK",
                tint = if (isBookmarked) {
                    Color(0xFFFFE266)
                } else {
                    TamhumhajangTheme.colors.color_bebebe
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 33.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_shop_location),
                    contentDescription = "IC_SHOP_LOCATION",
                    tint = Color.Unspecified
                )

                Text(
                    text = address,
                    style = TamhumhajangTheme.typography.body2.copy(
                        color = Color(0xFF727272)
                    )
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_shop_category),
                    contentDescription = "IC_SHOP_CATEGORY",
                    tint = Color.Unspecified
                )

                Text(
                    text = category,
                    style = TamhumhajangTheme.typography.body2.copy(
                        color = Color(0xFF727272)
                    )
                )
            }

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(imgUrl.ifEmpty { R.drawable.img_dummy_shop })
                    .build(),
                contentDescription = "IMG_WINE",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.12f)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                tags.forEach { tag ->
                    Text(
                        modifier = Modifier.background(
                            shape = RoundedCornerShape(20.dp),
                            color = TamhumhajangTheme.colors.color_9ddb80
                        ).padding(
                            horizontal = 12.dp,
                            vertical = 7.dp
                        ),
                        text = "# $tag",
                        style = TamhumhajangTheme.typography.description1.copy(
                            color = TamhumhajangTheme.colors.color_ffffff
                        )
                    )
                }
            }
        }
    }
}