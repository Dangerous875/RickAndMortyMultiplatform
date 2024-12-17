package com.klyxdevs.rickmortyapp.ui.characterDetailScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.klyxdevs.rickmortyapp.ui.characterDetailScreen.model.CharacterDetail
import kotlinx.serialization.json.Json

@Composable
fun CharacterDetailScreen(characterDetail: String, navController: NavController) {
    val character = Json.decodeFromString<CharacterDetail>(characterDetail)
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(character.name)
    }

}