package com.klyxdevs.rickmortyapp.ui.screens.characterDetailScreen.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class CharacterDetail(
    val id: String,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val species : String,
    val gender:String,
    val origin: String,
    val episode:List<String>
) {
    fun encodingObject(): String {
        return Json.encodeToString(this)
    }
}

