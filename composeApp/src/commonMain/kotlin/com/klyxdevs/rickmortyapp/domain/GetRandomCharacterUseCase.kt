package com.klyxdevs.rickmortyapp.domain

import co.touchlab.kermit.Logger
import com.klyxdevs.rickmortyapp.domain.model.CharacterModel
import com.klyxdevs.rickmortyapp.domain.model.CharacterOfTheDayModel
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetRandomCharacterUseCase(private val repository: Repository) {

    suspend operator fun invoke(): CharacterModel {
        val characterOfTheDay: CharacterOfTheDayModel? = repository.getCharacterDB()
        val selectedDate = getCurrentDayOfTheYear()

        return if (characterOfTheDay != null && characterOfTheDay.selectedDate == selectedDate) {
            Logger.i("TestGetRandomCharacter-> DB= $characterOfTheDay")
            characterOfTheDay.characterModel
        } else {
            val result = generateRandomCharacter()
            repository.saveCharacter(
                CharacterOfTheDayModel(
                    characterModel = result,
                    selectedDate = selectedDate
                )
            )
            Logger.i("TestGetRandomCharacter-> Remote= $result")
            return result
        }
    }

    private suspend fun generateRandomCharacter(): CharacterModel {
        val random = (1..826).random()
        return repository.getSingleCharacter(random.toString())
    }

    private fun getCurrentDayOfTheYear(): String {
        val instant = Clock.System.now()
        val localtime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localtime.dayOfYear}${localtime.year}"
    }
}