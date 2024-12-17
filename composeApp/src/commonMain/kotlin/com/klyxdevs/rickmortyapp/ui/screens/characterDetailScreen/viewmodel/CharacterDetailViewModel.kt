package com.klyxdevs.rickmortyapp.ui.screens.characterDetailScreen.viewmodel

import androidx.lifecycle.ViewModel
import com.klyxdevs.rickmortyapp.ui.screens.characterDetailScreen.model.CharacterDetail
import com.klyxdevs.rickmortyapp.ui.screens.characterDetailScreen.model.CharacterStateDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CharacterDetailViewModel(characterDetail: CharacterDetail ) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterStateDetail(characterDetail))
    val uiState = _uiState.asStateFlow()

}