package com.klyxdevs.rickmortyapp.ui.characterDetailScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import co.touchlab.kermit.Logger
import com.klyxdevs.rickmortyapp.ui.core.extensions.decodingObject

@Composable
fun CharacterDetailScreen(characterDetail: String, navController: NavController) {
    Logger.i("DecoderFunction -> $characterDetail")
    val character = characterDetail.decodingObject()
    Logger.i("DecoderFunction -> $character")
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(character.name)
    }

}