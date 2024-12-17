package com.klyxdevs.rickmortyapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TextTitle(text:String){
    Text(text.uppercase(), color = Color.White, fontWeight = FontWeight.Bold)
}