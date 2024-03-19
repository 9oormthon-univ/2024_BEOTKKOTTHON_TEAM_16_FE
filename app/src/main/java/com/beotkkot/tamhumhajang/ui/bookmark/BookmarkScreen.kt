package com.beotkkot.tamhumhajang.ui.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beotkkot.tamhumhajang.AppState
import com.beotkkot.tamhumhajang.BottomSheetState
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.design.component.TopBar
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme

@Composable
fun BookmarkScreen(
    appState: AppState,
    bottomSheetState: BottomSheetState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TamhumhajangTheme.colors.color_ffffff)
    ) {
        TopBar(
            "단골 상점"
        ) {
            appState.navigateUp()
        }

        LazyColumn {
            items(10) {
                ShopItem()
            }
        }
    }
}

@Composable
private fun ShopItem(
    id: Int = 0,
    name: String = "청년 채소가게",
    address: String = "서울 송파구 마천동, 상인 3호점",
    category: String = "채소 상점",
    imgUrl: String = "",
    onClick: (Int) -> Unit = { }
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(imgUrl.ifEmpty { R.drawable.img_dummy_shop })
                .build(),
            contentDescription = "IMG_WINE",
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Column {
            Text(
                text = name,
                style = TamhumhajangTheme.typography.title3.copy(
                    color = TamhumhajangTheme.colors.color_000000
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

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

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_shop_category),
                    contentDescription = "IC_SHOP_LOCATION",
                    tint = Color.Unspecified
                )

                Text(
                    text = category,
                    style = TamhumhajangTheme.typography.body2.copy(
                        color = Color(0xFF727272)
                    )
                )
            }
        }
    }
}