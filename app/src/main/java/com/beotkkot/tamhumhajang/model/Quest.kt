package com.beotkkot.tamhumhajang.model

data class Quest(
    val id: Int,
    val name: String,
    val isAcquired: Boolean,
    val description: String,
    val imgUrl: String
)
