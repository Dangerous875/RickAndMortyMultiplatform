package com.klyxdevs.rickmortyapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.klyxdevs.rickmortyapp.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "RickMortyApp",
    ) {
        initKoin()
        App()
    }
}

