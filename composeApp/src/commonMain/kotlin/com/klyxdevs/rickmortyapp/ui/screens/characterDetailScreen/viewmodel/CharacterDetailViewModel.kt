package com.klyxdevs.rickmortyapp.ui.screens.characterDetailScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.klyxdevs.rickmortyapp.domain.GetAllEpisodesUseCase
import com.klyxdevs.rickmortyapp.ui.screens.characterDetailScreen.model.CharacterDetail
import com.klyxdevs.rickmortyapp.ui.screens.characterDetailScreen.model.CharacterStateDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailViewModel(characterDetail: CharacterDetail,getAllEpisodesUseCase: GetAllEpisodesUseCase ) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterStateDetail(characterDetail))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                getAllEpisodesUseCase(characterDetail.episode)
            }
            _uiState.update { it.copy(episodes = result) }
        }
    }

}