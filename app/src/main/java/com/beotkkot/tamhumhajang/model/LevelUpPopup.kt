package com.beotkkot.tamhumhajang.model

data class LevelUpIntroducePopup(
    val title: String,
    val grade: String,
    val imgUrl: String,
    val description: String,
    val confirm: String
)

data class RewardPopup(
    val title: String,
    val description: String,
    val imgUrl: String,
    val positive: String,
    val negative: String,
    val latitude: Double,
    val longitude: Double
)

data class LevelUpPopup(
    val introducePopup: LevelUpIntroducePopup,
    val rewardPopup: RewardPopup
)