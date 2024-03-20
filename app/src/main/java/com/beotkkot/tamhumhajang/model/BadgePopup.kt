package com.beotkkot.tamhumhajang.model

data class BadgePopup(
    val title: String,
    val description: String, // evnet 이름 및 item_category를 이용해 생성
    val imgUrl: String, // imgUrl 취득 시 활성화 이미지 url, 미취득 시 비활성화 이미지 url
    val positive: String,
    val negative: String
)
