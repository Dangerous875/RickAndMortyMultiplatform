package com.klyxdevs.rickmortyapp.ui.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreenRoute

@Serializable
data class TestScreenRoute(val string: String)

// BottomNav

@Serializable
sealed class BottomBarRoutes{
    @Serializable
    data object EpisodesScreen : BottomBarRoutes()
    @Serializable
    data object CharactersScreen : BottomBarRoutes()
}
