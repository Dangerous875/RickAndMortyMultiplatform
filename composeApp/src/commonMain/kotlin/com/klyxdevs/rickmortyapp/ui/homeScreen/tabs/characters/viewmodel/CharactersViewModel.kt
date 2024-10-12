package com.klyxdevs.rickmortyapp.ui.homeScreen.tabs.characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klyxdevs.rickmortyapp.domain.GetRandomCharacter
import com.klyxdevs.rickmortyapp.ui.homeScreen.tabs.characters.CharacterState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel(val getRandomCharacter: GetRandomCharacter) : ViewModel() {

    private val _state = MutableStateFlow(CharacterState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                getRandomCharacter()
            }
            _state.update { it.copy(characterOfTheDay = result) }
        }
    }

}