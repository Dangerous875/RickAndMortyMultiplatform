package com.klyxdevs.rickmortyapp

import androidx.compose.ui.window.ComposeUIViewController
import com.klyxdevs.rickmortyapp.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = initKoin() ) { App() }