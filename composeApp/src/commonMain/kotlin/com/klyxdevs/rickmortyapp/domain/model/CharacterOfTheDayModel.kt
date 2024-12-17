package com.klyxdevs.rickmortyapp.domain.model

import com.klyxdevs.rickmortyapp.data.database.entity.CharacterOfTheDayEntity

data class CharacterOfTheDayModel(val characterModel: CharacterModel, val selectedDate: String) {
    fun toEntity(): CharacterOfTheDayEntity {
        return CharacterOfTheDayEntity(
            id = characterModel.id,
            isAlive = characterModel.isAlive,
            image = characterModel.image,
            name = characterModel.name,
            species = characterModel.species,
            selectedDate = selectedDate
        )
    }
}
