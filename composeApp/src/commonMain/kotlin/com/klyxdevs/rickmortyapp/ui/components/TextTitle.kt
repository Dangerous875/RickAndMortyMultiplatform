package com.klyxdevs.rickmortyapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.klyxdevs.rickmortyapp.ui.core.colors.DefaultTextColor

@Composable
fun TextTitle(text:String){
    Text(text.uppercase(), color = DefaultTextColor, fontWeight = FontWeight.Bold)
}