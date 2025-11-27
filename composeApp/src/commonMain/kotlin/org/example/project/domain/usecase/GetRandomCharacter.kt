package org.example.project.domain.usecase

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.project.domain.Repository
import org.example.project.domain.model.CharacterModel
import org.example.project.domain.model.CharacterOfTheDayModel


class GetRandomCharacter(private val repository: Repository) {

    suspend operator fun invoke(): CharacterModel {

        val characterOfTheDay = repository.getCharacterDB()
        val selectedDay = getCurrentDayOfTheYear()


        return if (characterOfTheDay != null && characterOfTheDay.selectedDay == selectedDay) {
            characterOfTheDay.characterModel
        } else {
            val result =generateRandomCharacter()
            repository.saveCharacterDB(CharacterOfTheDayModel(result, selectedDay))
            result
        }
    }

    private suspend fun generateRandomCharacter(): CharacterModel {
        val random = (1..826).random()
        return repository.getSingleCharacter(random.toString())
    }

    private fun getCurrentDayOfTheYear(): String {
        val instant = Clock.System.now()
        val localTame = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localTame.dayOfYear}${localTame.year}"
    }
}