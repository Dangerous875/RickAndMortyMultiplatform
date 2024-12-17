package com.klyxdevs.rickmortyapp.ui.characterDetailScreen.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetail(val id: String, val isAlive: Boolean, val image: String, val name: String)

