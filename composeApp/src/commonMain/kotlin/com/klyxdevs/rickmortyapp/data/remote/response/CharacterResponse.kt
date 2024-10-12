package com.klyxdevs.rickmortyapp.data.remote.response

import com.klyxdevs.rickmortyapp.domain.model.CharacterModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val id: Int,
    val status: String,
    val image: String
) {
    fun toDomain(): CharacterModel {
        return CharacterModel(
            id = this.id.toString(),
            isAlive = this.status.lowercase() == "Alive",
            image = this.image
        )
    }
}