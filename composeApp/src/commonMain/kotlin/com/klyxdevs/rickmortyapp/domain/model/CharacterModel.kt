package com.klyxdevs.rickmortyapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterModel(val id: String, val isAlive: Boolean, val image: String, val name: String)