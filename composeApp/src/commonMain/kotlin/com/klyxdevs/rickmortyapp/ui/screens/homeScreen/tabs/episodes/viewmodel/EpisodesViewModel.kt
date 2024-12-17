package com.klyxdevs.rickmortyapp.ui.screens.homeScreen.tabs.episodes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.klyxdevs.rickmortyapp.domain.Repository
import com.klyxdevs.rickmortyapp.ui.screens.homeScreen.tabs.episodes.EpisodesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class EpisodesViewModel(private val repository: Repository) : ViewModel() {
    private val _state = MutableStateFlow(EpisodesState())
    val state: StateFlow<EpisodesState> = _state

    init {
        _state.update { state ->
            state.copy(
                characters = repository.getAllEpisodes().cachedIn(viewModelScope)
            )
        }
    }

    fun onPlaySelected(url: String) {
        _state.update { state -> state.copy(playVideo = url) }
    }

    fun onCloseVideo() {
        _state.update { state -> state.copy(playVideo = "") }
    }

}