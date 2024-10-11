package com.klyxdevs.rickmortyapp.ui.core.navigation.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.klyxdevs.rickmortyapp.ui.core.navigation.NavRoutes
import com.klyxdevs.rickmortyapp.ui.homeScreen.tabs.characters.CharacterScreen
import com.klyxdevs.rickmortyapp.ui.homeScreen.tabs.episodes.EpisodesScreen

@Composable
fun NavigationBottomWrapper(navController: NavHostController){
    NavHost(navController = navController , startDestination = NavRoutes.EpisodesNavScreen){
        composable<NavRoutes.EpisodesNavScreen> { EpisodesScreen() }
        composable<NavRoutes.CharactersNavScreen> { CharacterScreen() }
    }
}