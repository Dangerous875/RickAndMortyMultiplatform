package com.klyxdevs.rickmortyapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.klyxdevs.rickmortyapp.data.database.RickMortyDatabase
import com.klyxdevs.rickmortyapp.data.remote.ApiService
import com.klyxdevs.rickmortyapp.data.remote.paging.CharactersPagingSource
import com.klyxdevs.rickmortyapp.domain.Repository
import com.klyxdevs.rickmortyapp.domain.model.CharacterModel
import com.klyxdevs.rickmortyapp.domain.model.CharacterOfTheDayModel
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val api: ApiService,
    private val charactersPagingSource: CharactersPagingSource,
    private val rickMortyDatabase: RickMortyDatabase
) : Repository {
    companion object {
        const val MAX_ITEMS = 20
        const val PREFETCH_ITEMS = 5
    }

    override suspend fun getSingleCharacter(id: String): CharacterModel {
        return api.getSingleCharacter(id).toDomain()
    }

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = { charactersPagingSource }).flow
    }

    override suspend fun getCharacterDB():CharacterOfTheDayModel? {
        return rickMortyDatabase.getPreferencesDao().getCharacterOfTheDayDB()?.toDomain()
    }

    override suspend fun saveCharacter(characterOfTheDayModel: CharacterOfTheDayModel) {
        rickMortyDatabase.getPreferencesDao().saveCharacter(characterOfTheDayModel.toEntity())
    }
}