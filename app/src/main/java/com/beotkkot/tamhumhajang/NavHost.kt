package com.beotkkot.tamhumhajang

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.beotkkot.tamhumhajang.design.component.BottomNavigationBar
import com.beotkkot.tamhumhajang.design.component.BottomNavigationItem
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme
import com.beotkkot.tamhumhajang.ui.home.HomeScreen
import com.beotkkot.tamhumhajang.ui.map.MapScreen
import com.beotkkot.tamhumhajang.ui.profile.ProfileScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NavHost() {
    val appState = rememberAppState()
    val bottomSheetState = rememberWineyBottomSheetState()
    val navController = appState.navController

    ModalBottomSheetLayout(
        sheetContent = {
            bottomSheetState.bottomSheetContent.value?.invoke(this)
        },
        sheetState = bottomSheetState.bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp),
        modifier = Modifier.navigationBarsPadding()
    ) {
        Scaffold(
            backgroundColor = TamhumhajangTheme.colors.white,
            snackbarHost = {
                SnackBar(appState)
            },
            bottomBar = {
                AnimatedVisibility(
                    appState.shouldShowBottomBar,
                    enter = slideInVertically { it },
                    exit = slideOutVertically { it },
                ) {
                    WineyBottomNavigationBar(
                        destinations = appState.topLevelDestination,
                        currentDestination = appState.currentDestination,
                        onNavigateToDestination = appState::navigateToTopLevelDestination
                    )
                }
            }
        ) { padding ->

            NavHost(
                modifier = Modifier
                    .bottomBarPadding(appState.currentDestination, padding)
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                navController = navController,
                startDestination = "home"
            ) {
                composable(
                    route = "home"
                ) {
                    HomeScreen(
                        appState = appState,
                        bottomSheetState = bottomSheetState
                    )
                }

                composable(
                    route = "map"
                ) {
                    MapScreen(
                        appState = appState,
                        bottomSheetState = bottomSheetState
                    )
                }
                
                composable(
                    route = "profile"
                ) {
                    ProfileScreen(
                        appState = appState,
                        bottomSheetState = bottomSheetState
                    )
                }
            }
        }
    }
}

@Composable
private fun Modifier.Companion.bottomBarPadding(
    currentDestination: NavDestination?,
    padding: PaddingValues
): Modifier {
    return if (currentDestination?.route in listOf("")
    ) {
        Modifier
    } else {
        Modifier.padding(padding)
    }
}


@Composable
private fun SnackBar(appState: AppState) {
    SnackbarHost(hostState = appState.scaffoldState.snackbarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(
                    bottom = 50.dp,
                    start = 20.dp,
                    end = 20.dp
                )
            ) {
                Text(text = data.message)
            }
        }
    )
}

@Composable
private fun WineyBottomNavigationBar(
    modifier: Modifier = Modifier,
    destinations: List<TopLevelDestination>,
    currentDestination: NavDestination?,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    backgroundColor: Color = TamhumhajangTheme.colors.white,
    selectedContentColor: Color = Color(0xFF1A1E27),
    unselectedContentColor: Color = Color(0xFFDADADA)
) {
    BottomNavigationBar(
        modifier = modifier,
        backgroundColor = backgroundColor
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination?.route == destination.route

            BottomNavigationItem(
                label = destination.label,
                selected = selected,
                selectedIcon = destination.selectedIcon,
                unselectedIcon = destination.unselectedIcon,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,
                onClick = {
                    if (!selected) {
                        onNavigateToDestination(destination)
                    }
                },
            )
        }
    }
}