package com.klyxdevs.rickmortyapp.ui.core.navigation

import com.klyxdevs.rickmortyapp.ui.characterDetailScreen.model.CharacterDetail
import kotlinx.serialization.Serializable

@Serializable
object HomeScreenRoute

@Serializable
data class CharacterDetailRoute(val characterDetail: CharacterDetail)
// BottomNav

@Serializable
sealed class BottomBarRoutes{
    @Serializable
    data object EpisodesScreen : BottomBarRoutes()
    @Serializable
    data object CharactersScreen : BottomBarRoutes()
}
