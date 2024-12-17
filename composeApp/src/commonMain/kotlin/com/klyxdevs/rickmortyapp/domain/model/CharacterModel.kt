package com.klyxdevs.rickmortyapp.domain.model

import com.klyxdevs.rickmortyapp.ui.characterDetailScreen.model.CharacterDetail

data class CharacterModel(
    val id: String,
    val isAlive: Boolean,
    val image: String,
    val name: String
) {
    fun toCharacterDetail(): CharacterDetail {
        return CharacterDetail(id = id, isAlive = isAlive, image = image, name = name)
    }
}