package com.klyxdevs.rickmortyapp.di

import com.klyxdevs.rickmortyapp.ui.homeScreen.tabs.characters.viewmodel.CharactersViewModel
import com.klyxdevs.rickmortyapp.ui.homeScreen.tabs.episodes.viewmodel.EpisodesViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::EpisodesViewModel)
    viewModelOf(::CharactersViewModel)
}