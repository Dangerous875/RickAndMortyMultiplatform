package com.klyxdevs.rickmortyapp.ui.core.colors

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.klyxdevs.rickmortyapp.isDesktop

// colors
val BackgroundPrimaryColor
    @Composable
    get() = when {
        isDesktop() -> primaryBlack
        else -> if (isSystemInDarkTheme()) primaryBlack else primaryWhite
    }


val BackgroundSecondaryColor
    @Composable
    get() = when {
        isDesktop() -> secondaryBlack
        else -> if (isSystemInDarkTheme()) secondaryBlack else secondaryWhite
    }

val BackgroundTertiaryColor
    @Composable
    get() = when {
        isDesktop() -> tertiaryBlack
        else -> if (isSystemInDarkTheme()) tertiaryBlack else tertiaryWhite
    }

val DefaultTextColor
    @Composable
    get() = when {
        isDesktop() -> Color.White
        else -> if (isSystemInDarkTheme()) Color.White else Color.Black
    }

val PlaceholderColor
    @Composable
    get() = when {
        isDesktop() -> tertiaryBlack
        else -> if (isSystemInDarkTheme()) tertiaryBlack else secondaryWhite
    }

val Pink = Color(0xFFFF577D)
val Green = Color(0xFF5ccf92)

val primaryWhite = Color(0xFFFFFFFF)
val secondaryWhite = Color(0xFFEAE8EF)
val tertiaryWhite = Color(0xFFFAFAFA)

val primaryBlack = Color(0xFF000000)
val secondaryBlack = Color(0xFF302F2F)
val tertiaryBlack = Color(0xFF464646)