package com.beotkkot.tamhumhajang.data.model.response

import com.beotkkot.tamhumhajang.data.model.RecommendMarket
import com.google.gson.annotations.SerializedName

data class RecommendMarketResponse(
    @SerializedName("markets")
    val markets: List<RecommendMarket>
)
