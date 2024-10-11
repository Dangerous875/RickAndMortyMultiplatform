package com.klyxdevs.rickmortyapp.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.klyxdevs.rickmortyapp.ui.homeScreen.HomeScreen
import com.klyxdevs.rickmortyapp.ui.testScreen.TestScreen
import com.klyxdevs.rickmortyapp.ui.core.navigation.HomeScreenRoute
import com.klyxdevs.rickmortyapp.ui.core.navigation.TestScreenRoute

@Composable
fun NavigationWrapper() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeScreenRoute) {
        composable<HomeScreenRoute> { HomeScreen() }
        composable<TestScreenRoute> {
            val safeArgs = it.toRoute<TestScreenRoute>()
            TestScreen(safeArgs.string,navController)
        }
    }
}