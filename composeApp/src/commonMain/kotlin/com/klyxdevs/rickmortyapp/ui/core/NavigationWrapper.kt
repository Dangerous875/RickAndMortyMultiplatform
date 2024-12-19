package com.klyxdevs.rickmortyapp.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.klyxdevs.rickmortyapp.ui.screens.characterDetailScreen.CharacterDetailScreen
import com.klyxdevs.rickmortyapp.ui.core.navigation.CharacterDetailRoute
import com.klyxdevs.rickmortyapp.ui.core.navigation.HomeScreenRoute
import com.klyxdevs.rickmortyapp.ui.screens.homeScreen.HomeScreen

@Composable
fun NavigationWrapper() {
    val mainNavController = rememberNavController()
    NavHost(navController = mainNavController, startDestination = HomeScreenRoute) {
        composable<HomeScreenRoute> { HomeScreen(mainNavController) }
        composable<CharacterDetailRoute> {
            val safeArgs = it.toRoute<CharacterDetailRoute>()
            CharacterDetailScreen(safeArgs.characterDetail,mainNavController)
        }
    }
}