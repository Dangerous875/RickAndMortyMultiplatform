package com.klyxdevs.rickmortyapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBar(color: Color, size: Dp = 36.dp) {
    Box(modifier = Modifier.fillMaxSize().size(size), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = color)
    }
}