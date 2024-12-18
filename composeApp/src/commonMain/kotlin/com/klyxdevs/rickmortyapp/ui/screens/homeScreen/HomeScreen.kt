package com.klyxdevs.rickmortyapp.ui.screens.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.touchlab.kermit.Logger
import com.klyxdevs.rickmortyapp.ui.core.colors.BackgroundPrimaryColor
import com.klyxdevs.rickmortyapp.ui.core.colors.BackgroundSecondaryColor
import com.klyxdevs.rickmortyapp.ui.core.colors.BackgroundTertiaryColor
import com.klyxdevs.rickmortyapp.ui.core.colors.DefaultTextColor
import com.klyxdevs.rickmortyapp.ui.core.colors.Green
import com.klyxdevs.rickmortyapp.ui.core.navigation.bottomnavigation.BottomBarItem
import com.klyxdevs.rickmortyapp.ui.core.navigation.bottomnavigation.NavigationBottomWrapper
import org.jetbrains.compose.resources.painterResource
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.ricktoolbar

@Composable
fun HomeScreen(mainNavHostController: NavHostController) {
    val items = listOf(BottomBarItem.Episodes, BottomBarItem.Characters)
    val navControllerNavBar = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(items, navControllerNavBar) },
        topBar = { TopBar() }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            NavigationBottomWrapper(mainNavHostController, navControllerNavBar)
        }
    }
}

@Composable
fun TopBar() {
    Box(
        modifier = Modifier.fillMaxWidth().background(BackgroundPrimaryColor),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(Res.drawable.ricktoolbar),
            null,
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun BottomNavigation(items: List<BottomBarItem>, navControllerNavBar: NavHostController) {
    val navBackStackEntry by navControllerNavBar.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar(containerColor = BackgroundSecondaryColor, contentColor = Green) {
        val currentScreen = currentDestination?.toString()?.substringAfterLast(".")
        items.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Green,
                    selectedIconColor = BackgroundTertiaryColor,
                    unselectedIconColor = Green
                ),
                icon = item.icon,
                onClick = {
                    navControllerNavBar.navigate(route = item.route) {
                        navControllerNavBar.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                selected = currentScreen == item.route.toString(),
                label = { Text(item.title, color = DefaultTextColor) }
            )
            Logger.i("Destination stack -> currentScreen")
            Logger.i("Destination item -> ${item.route}")
        }
    }
}