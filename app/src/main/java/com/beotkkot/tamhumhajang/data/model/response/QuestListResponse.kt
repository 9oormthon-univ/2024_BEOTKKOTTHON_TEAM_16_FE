package com.beotkkot.tamhumhajang.data.model.response

import com.beotkkot.tamhumhajang.data.model.Quest
import com.google.gson.annotations.SerializedName

data class QuestListResponse(
    @SerializedName("sequence")
    val sequence: Int,
    @SerializedName("quests")
    val quest: List<Quest>
)
