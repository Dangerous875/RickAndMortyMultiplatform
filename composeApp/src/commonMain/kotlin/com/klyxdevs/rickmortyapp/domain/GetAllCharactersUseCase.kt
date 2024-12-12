package com.klyxdevs.rickmortyapp.domain

import androidx.paging.PagingData
import com.klyxdevs.rickmortyapp.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

class GetAllCharactersUseCase(private val repository: Repository) {

    operator fun invoke(): Flow<PagingData<CharacterModel>> {
        return repository.getAllCharacters()
    }
}