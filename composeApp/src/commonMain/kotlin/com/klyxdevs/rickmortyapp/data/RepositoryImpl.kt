package com.klyxdevs.rickmortyapp.data

import com.klyxdevs.rickmortyapp.data.remote.ApiService
import com.klyxdevs.rickmortyapp.domain.Repository
import com.klyxdevs.rickmortyapp.domain.model.CharacterModel

class RepositoryImpl(private val api : ApiService):Repository {
    override suspend fun getSingleCharacter(id: String): CharacterModel {
        return api.getSingleCharacter(id).toDomain()
    }
}