package com.klyxdevs.rickmortyapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.klyxdevs.rickmortyapp.domain.model.CharacterModel
import com.klyxdevs.rickmortyapp.domain.model.CharacterOfTheDayModel
import kotlinx.serialization.json.Json

@Entity(tableName = "characteroftheday")
data class CharacterOfTheDayEntity(
    @PrimaryKey
    val id: String,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val selectedDate: String,
    val species: String,
    val gender: String,
    val origin: String,
    val episode:String
) {
    fun toDomain(): CharacterOfTheDayModel {
        return CharacterOfTheDayModel(
            characterModel = CharacterModel(
                id = id,
                isAlive = isAlive,
                image = image,
                name = name,
                species = species,
                gender = gender,
                origin = origin,
                episode = Json.decodeFromString<List<String>>(episode)
            ),
            selectedDate = selectedDate
        )
    }
}