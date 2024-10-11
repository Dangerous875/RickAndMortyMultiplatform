package com.klyxdevs.rickmortyapp.ui.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreenRoute

@Serializable
data class TestScreenRoute(val string: String)

// BottomNav

@Serializable
sealed class NavRoutes(val route: String) {
    @Serializable
    data object EpisodesNavScreen :
        NavRoutes("com.klyxdevs.rickmortyapp.ui.core.navigation.NavRoutes.EpisodesNavScreen")

    @Serializable
    data object CharactersNavScreen :
        NavRoutes("com.klyxdevs.rickmortyapp.ui.core.navigation.NavRoutes.CharactersNavScreen")
}
