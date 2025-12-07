package org.example.project.domain.model

import kotlinx.serialization.json.Json
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
        selectedDay = selectedDay,
        species = characterModel.species,
        gender = characterModel.gender,
        origin = characterModel.origin,
        episodes = Json.encodeToString(characterModel.episodes)
    )
}