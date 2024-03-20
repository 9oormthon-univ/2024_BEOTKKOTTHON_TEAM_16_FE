package com.beotkkot.tamhumhajang.ui.popup

import androidx.compose.runtime.Composable
import com.beotkkot.tamhumhajang.model.Quest

val questDummy = listOf(
    Quest(
        0,
        "첫 시작",
        true,
        "탐험가 배지 획득 완료",
        ""
    )
)

@Composable
fun QuestListPopup(
    title: String = "첫 번째 퀘스트",
    quests: List<Quest>
) {
}