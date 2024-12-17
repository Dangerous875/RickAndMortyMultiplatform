package com.klyxdevs.rickmortyapp.ui.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreenRoute

@Serializable
object CharacterDetailRoute

// BottomNav

@Serializable
sealed class BottomBarRoutes{
    @Serializable
    data object EpisodesScreen : BottomBarRoutes()
    @Serializable
    data object CharactersScreen : BottomBarRoutes()
}
