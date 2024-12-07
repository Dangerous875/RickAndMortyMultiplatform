package com.klyxdevs.rickmortyapp.ui.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.touchlab.kermit.Logger
import com.klyxdevs.rickmortyapp.ui.core.navigation.bottomnavigation.BottomBarItem
import com.klyxdevs.rickmortyapp.ui.core.navigation.bottomnavigation.NavigationBottomWrapper

@Composable
fun HomeScreen() {
    val items = listOf(BottomBarItem.Episodes, BottomBarItem.Characters)
    val navControllerNavBar = rememberNavController()
    Scaffold(bottomBar = { BottomNavigation(items, navControllerNavBar) }) {
        Box { NavigationBottomWrapper(navControllerNavBar) }
    }
}

@Composable
fun BottomNavigation(items: List<BottomBarItem>, navControllerNavBar: NavHostController) {
    val navBackStackEntry by navControllerNavBar.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar {
        val currentScreen = currentDestination?.toString()?.substringAfterLast(".")
        items.forEach { item ->
            NavigationBarItem(
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
                label = { Text(item.title) }
            )
            Logger.i("Destination stack -> currentScreen")
            Logger.i("Destination item -> ${item.route}")
        }
    }
}