package com.klyxdevs.rickmortyapp.ui.characterDetailScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.klyxdevs.rickmortyapp.domain.model.CharacterModel

@Composable
fun CharacterDetailScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text("characterModel.name")
    }
}