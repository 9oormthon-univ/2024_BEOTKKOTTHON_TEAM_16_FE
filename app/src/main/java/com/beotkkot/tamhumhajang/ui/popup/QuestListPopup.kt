package com.beotkkot.tamhumhajang.ui.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.data.model.Quest
import com.beotkkot.tamhumhajang.design.component.TamhumPopup
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme

val questDummy = listOf(
    Quest(
        0,
        "첫 시작",
        true,
        "탐험가 배지 획득 완료",
        ""
    ),
    Quest(
        0,
        "단골 손님",
        false,
        "좋아하는 상점을\n단골로 등록해보세요!",
        ""
    ),
    Quest(
        0,
        "첫 시장 단험",
        false,
        "아래는 직접 시장을\n탐험해볼 시간이예요!",
        ""
    )
)

@Composable
fun QuestListPopup(
    title: String = "첫 번째 퀘스트",
    quests: List<Quest> = questDummy,
    onClose: () -> Unit
) {
    TamhumPopup(
        onDismissRequest = onClose
    ) {
        Column(
            modifier = Modifier
                .width(300.dp)
                .padding(
                    top = 25.dp,
                    bottom = 30.dp,
                    start = 12.dp,
                    end = 12.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Text(
                text = title,
                style = TamhumhajangTheme.typography.largeTitle
            )

            quests.forEach { quest ->
                QuestItem(quest)
            }
        }
    }
}

@Composable
private fun QuestItem(
    quest: Quest
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (quest.isAcquired) {
                    TamhumhajangTheme.colors.color_0fa958
                } else {
                    Color(0xFFD7EDCC)
                },
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier.width(35.dp),
                model = ImageRequest.Builder(context)
                    .data(quest.imgUrl.ifEmpty { R.drawable.ic_shop })
                    .build(),
                contentDescription = "IC_SHOP",
                contentScale = ContentScale.FillWidth
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column {
            Text(
                text = if (quest.isAcquired) quest.name else quest.description,
                style = TamhumhajangTheme.typography.body2.copy(
                    color = if (quest.isAcquired) {
                        TamhumhajangTheme.colors.color_ffffff
                    } else {
                        TamhumhajangTheme.colors.color_000000
                    }
                )
            )

            if (!quest.isAcquired) {
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = quest.description,
                    style = TamhumhajangTheme.typography.description1.copy(
                        color = TamhumhajangTheme.colors.color_000000
                    )
                )
            }
        }
    }
}