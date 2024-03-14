package com.beotkkot.tamhumhajang

import androidx.annotation.DrawableRes

enum class TopLevelDestination(
    val label: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val route: String
) {
    Home(
        label = "홈",
        selectedIcon = R.drawable.ic_home_selected,
        unselectedIcon = R.drawable.ic_home_unselected,
        route = "home"
    ),
    Map(
        label = "지도",
        selectedIcon = R.drawable.ic_map_selected,
        unselectedIcon = R.drawable.ic_map_unselected,
        route = "map"
    ),
    Profile(
        label = "프로필",
        selectedIcon = R.drawable.ic_profile_selected,
        unselectedIcon = R.drawable.ic_profile_unselected,
        route = "profile"
    )
}