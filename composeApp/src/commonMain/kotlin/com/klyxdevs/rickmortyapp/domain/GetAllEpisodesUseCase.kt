package com.klyxdevs.rickmortyapp.domain

import com.klyxdevs.rickmortyapp.domain.model.EpisodeModel

class GetAllEpisodesUseCase(private val repository: Repository) {
    suspend operator fun invoke(episodes : List<String>):List<EpisodeModel>{
        return repository.getEpisodesForCharacter(episodes)
    }
}