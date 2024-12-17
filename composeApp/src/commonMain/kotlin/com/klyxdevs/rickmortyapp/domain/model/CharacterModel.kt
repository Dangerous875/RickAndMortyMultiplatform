package com.klyxdevs.rickmortyapp.domain.model

import com.klyxdevs.rickmortyapp.ui.screens.characterDetailScreen.model.CharacterDetail

data class CharacterModel(
    val id: String,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val species: String,
    val gender: String,
    val origin: String,
    val episode: List<String>
) {
    fun toCharacterDetail(): CharacterDetail {
        return CharacterDetail(
            id = id,
            name = name,
            isAlive = isAlive,
            image = image,
            species = species,
            gender = gender,
            origin = origin,
            episode = episode
        )
    }
}