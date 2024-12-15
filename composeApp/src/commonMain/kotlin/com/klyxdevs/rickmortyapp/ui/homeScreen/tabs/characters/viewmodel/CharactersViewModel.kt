package com.klyxdevs.rickmortyapp.ui.homeScreen.tabs.characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.klyxdevs.rickmortyapp.domain.GetAllCharactersUseCase
import com.klyxdevs.rickmortyapp.domain.GetRandomCharacterUseCase
import com.klyxdevs.rickmortyapp.ui.homeScreen.tabs.characters.CharacterState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel(
    private val getRandomCharacter: GetRandomCharacterUseCase,
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getRandomCharacter()
            }
            _state.update { it.copy(characterOfTheDay = result) }
        }
        getAllCharacters()
    }

    private fun getAllCharacters() {
        _state.update { state ->
            state.copy(characters = getAllCharactersUseCase().cachedIn(viewModelScope))
        }
    }

}