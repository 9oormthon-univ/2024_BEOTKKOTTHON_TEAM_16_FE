package com.beotkkot.tamhumhajang.data.model.response

import com.beotkkot.tamhumhajang.data.model.PopupType
import com.google.gson.annotations.SerializedName

/**
 * id % 3 == 0 이면 레벨업 API 호출
 *
 *
 * type == PopupType.APP 인 경우 badgePopup만 확인
 * type == PopupType.LOCATION 인 경우 badgePopup만 확인
 *
 * type == PopupType.QUIZ 인 경우 badgePopup, quizPopup 둘 다 확인
 */
data class TouchResponse(
    @SerializedName("popupType")
    val popupType: PopupType,
    @SerializedName("itemId")
    val id: Int, // 현재 수행 중인 퀘스트

    //@SerializedName("badgePosition")
    //val badgePosition: BadgePosition?,

    @SerializedName("badgePopup")
    val badgePopup: BadgePopup?,
    @SerializedName("quizPopup")
    val quizPopup: QuizPopup?,
)

data class BadgePopup(
    // 이어서 탐험하기, 도감 이동하기 고정이어서 버튼 텍스트는 안 내려줌
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("imgUrl")
    val imgUrl: String
)

data class QuizPopup(
    val warning: QuizWarningPopup,
    val question: QuizQuestionPopup
)

data class QuizWarningPopup(
    val title: String,  // 제목
    val name: String, // 몬스터 이름 ex) 호랑이 (어흥),
    val confirm: String // 떡 구하러 가기, 과일 구하러 가기
)

data class QuizQuestionPopup(
    val title: String,  // 제목
    val description: String,    // 아래의 문제를 맞추어, 돌발 상황을 해결해 보세요!,
    val question: String,   // 마천 시장은 전문시장이다.
    val negative: String,   // O
    val positive: String    // X
)