package org.example.project.domain.model

import org.example.project.data.database.entity.CharacterOfTheDayEntity

data class CharacterOfTheDayModel(
    val characterModel: CharacterModel,
    val selectedDay: String
)

fun CharacterOfTheDayModel.toEntity(): CharacterOfTheDayEntity {
    return CharacterOfTheDayEntity(
        id = characterModel.id,
        isAlive = characterModel.isAlive,
        image = characterModel.image,
        name = characterModel.name,
        selectedDay = selectedDay
    )
}