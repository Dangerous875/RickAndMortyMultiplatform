package com.klyxdevs.rickmortyapp.ui.core.navigation.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.klyxdevs.rickmortyapp.ui.core.navigation.BottomBarRoutes
import com.klyxdevs.rickmortyapp.ui.screens.homeScreen.tabs.characters.CharactersScreen
import com.klyxdevs.rickmortyapp.ui.screens.homeScreen.tabs.episodes.EpisodesScreen

@Composable
fun NavigationBottomWrapper(mainNavHostController: NavHostController,navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarRoutes.EpisodesScreen) {
        composable<BottomBarRoutes.EpisodesScreen> { EpisodesScreen() }
        composable<BottomBarRoutes.CharactersScreen> { CharactersScreen(mainNavHostController) }
    }
}