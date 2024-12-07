package com.klyxdevs.rickmortyapp.ui.core.navigation.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.klyxdevs.rickmortyapp.ui.core.navigation.BottomBarRoutes

sealed class BottomBarItem {
    abstract val route: BottomBarRoutes
    abstract val title: String
    abstract val icon: @Composable () -> Unit

    data object Episodes : BottomBarItem() {
        override val route: BottomBarRoutes
            get() = BottomBarRoutes.EpisodesScreen
        override val title: String
            get() = "Episodes"
        override val icon: @Composable () -> Unit
            get() = { Icon(imageVector = Icons.Default.Home, contentDescription = "") }
    }

    data object Characters : BottomBarItem() {
        override val route: BottomBarRoutes
            get() = BottomBarRoutes.CharactersScreen
        override val title: String
            get() = "Characters"
        override val icon: @Composable () -> Unit
            get() = { Icon(imageVector = Icons.Default.Person, contentDescription = "") }
    }
}


