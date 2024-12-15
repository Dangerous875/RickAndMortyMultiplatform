package com.klyxdevs.rickmortyapp.domain.model

import com.klyxdevs.rickmortyapp.data.database.entity.CharacterOfTheDayEntity

data class CharacterOfTheDayModel(val characterModel: CharacterModel, val selectedDate: String) {
    fun toEntity(): CharacterOfTheDayEntity {
        return CharacterOfTheDayEntity(
            characterModel.id,
            characterModel.isAlive,
            characterModel.image,
            characterModel.name,
            selectedDate
        )
    }
}
