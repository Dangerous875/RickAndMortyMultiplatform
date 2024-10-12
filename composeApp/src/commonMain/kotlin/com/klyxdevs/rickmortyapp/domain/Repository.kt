package com.klyxdevs.rickmortyapp.domain

import com.klyxdevs.rickmortyapp.domain.model.CharacterModel

interface Repository {
    suspend fun getSingleCharacter(id : String): CharacterModel
}