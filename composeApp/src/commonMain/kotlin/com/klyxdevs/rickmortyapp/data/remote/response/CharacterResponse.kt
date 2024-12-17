package com.klyxdevs.rickmortyapp.data.remote.response

import com.klyxdevs.rickmortyapp.domain.model.CharacterModel
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val id: Int,
    val status: String,
    val image: String,
    val name : String,
    val species:String,
    val gender:String,
    val origin:OriginResponse,
    val episode:List<String>
) {
    fun toDomain(): CharacterModel {
        return CharacterModel(
            id = this.id.toString(),
            isAlive = this.status.lowercase() == "alive",
            image = this.image,
            name = this.name,
            species = this.species,
            gender = this.gender,
            origin = this.origin.name,
            episode = this.episode.map { it.substringAfterLast("/") }
        )
    }
}