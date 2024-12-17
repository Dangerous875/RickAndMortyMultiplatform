package com.klyxdevs.rickmortyapp.ui.screens.characterDetailScreen.model

import com.klyxdevs.rickmortyapp.domain.model.EpisodeModel

data class CharacterStateDetail(
    val characterDetail: CharacterDetail,
    val episodes: List<EpisodeModel>? = null
)
