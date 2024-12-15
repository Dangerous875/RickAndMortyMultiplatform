package com.klyxdevs.rickmortyapp.domain

import androidx.paging.PagingData
import com.klyxdevs.rickmortyapp.domain.model.CharacterModel
import com.klyxdevs.rickmortyapp.domain.model.CharacterOfTheDayModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getSingleCharacter(id : String): CharacterModel

    fun getAllCharacters():Flow<PagingData<CharacterModel>>

    suspend fun getCharacterDB(): CharacterOfTheDayModel?

    suspend fun saveCharacter(characterOfTheDayModel: CharacterOfTheDayModel)
}