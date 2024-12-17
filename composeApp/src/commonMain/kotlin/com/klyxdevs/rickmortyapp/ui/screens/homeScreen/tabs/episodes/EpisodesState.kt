package com.klyxdevs.rickmortyapp.ui.screens.homeScreen.tabs.episodes

import androidx.paging.PagingData
import com.klyxdevs.rickmortyapp.domain.model.EpisodeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class EpisodesState(
    val characters: Flow<PagingData<EpisodeModel>> = emptyFlow(),
    val playVideo:String = ""
)