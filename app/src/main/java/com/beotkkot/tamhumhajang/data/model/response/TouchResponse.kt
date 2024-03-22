package com.beotkkot.tamhumhajang.data.model.response

data class TouchResponse(
    val type: String,
    val id: Int, // 현재 수행 중인 퀘스트
    val nextId: Int, // 다음 수행해야할 퀘스트

    val badgePopup: BadgePopup?,
    val levelUpPopup: LevelUpPopup?,
    val quizPopup: QuizPopup?,

    val name: String,   // 팝업 이름
    val popupDescription: String,   // 팝업 제목
    val profileDescription: String?, // 팝업 설명
    val image: String, // 팝업 이미지

    val latitude: Double?,
    val longitude: Double?,

    val quizConfirmText: String,
    val quizWarningTitle: String,
    val quizWarningImage: String,

    val quizQuestion: String,
    val quizPositive: String,
    val quizNegative: String
)

data class BadgePopup(
    // 이어서 탐험하기, 도감 이동하기 고정이어서 버튼 텍스트는 안 내려줌
    val title: String,
    val description: String,
    val imgUrl: String
)

data class LevelUpPopup(
    val tierImgUrl: String,
    val title: String,
    val grade: String,
    val characterImgUrl: String,
    val description: String
    // 이어서 탐색하기 고정이니깐 버튼 텍스트는 안내랴줌
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