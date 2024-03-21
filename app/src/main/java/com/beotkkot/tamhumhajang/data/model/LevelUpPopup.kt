package com.beotkkot.tamhumhajang.data.model

data class LevelUpIntroducePopup(
    val title: String,
    val grade: String,
    val imgUrl: String,
    val description: String,
    val confirm: String
)

data class LevelUpPopup(
    val introducePopup: LevelUpIntroducePopup,
    val rewardPopup: RewardPopup
)