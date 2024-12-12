package com.klyxdevs.rickmortyapp.domain

import com.klyxdevs.rickmortyapp.domain.model.CharacterModel
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetRandomCharacterUseCase(private val repository: Repository) {
    // val characterDataBase = repository.gerSavedCharacter()
    suspend operator fun invoke(): CharacterModel {
        val random = (1..826).random()
        return repository.getSingleCharacter(random.toString())

    }

    private fun getCurrentDayOfTheYear():String{
        val instant = Clock.System.now()
        val localtime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localtime.dayOfYear}${localtime.year}"
    }
}