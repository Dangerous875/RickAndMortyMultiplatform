package com.klyxdevs.rickmortyapp.ui.core.navigation.bottomnavigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.klyxdevs.rickmortyapp.ui.core.navigation.BottomBarRoutes
import org.jetbrains.compose.resources.painterResource
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.ic_characters
import rickmortyapp.composeapp.generated.resources.ic_player

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
            get() = {
                Icon(
                    painter = painterResource(Res.drawable.ic_player),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
            }
    }

    data object Characters : BottomBarItem() {
        override val route: BottomBarRoutes
            get() = BottomBarRoutes.CharactersScreen
        override val title: String
            get() = "Characters"
        override val icon: @Composable () -> Unit
            get() = {
                Icon(
                    painter = painterResource(Res.drawable.ic_characters),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
            }
    }
}


