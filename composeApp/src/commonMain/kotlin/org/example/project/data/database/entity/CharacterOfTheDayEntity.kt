package org.example.project.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.json.Json
import org.example.project.domain.model.CharacterModel
import org.example.project.domain.model.CharacterOfTheDayModel

@Entity(tableName = "characteroftheday")
data class CharacterOfTheDayEntity(
    @PrimaryKey
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val selectedDay: String,
    val species: String,
    val gender: String,
    val origin: String,
    val episodes: String
)

fun CharacterOfTheDayEntity.toDomain(): CharacterOfTheDayModel {
    return CharacterOfTheDayModel(
        characterModel = CharacterModel(
            id = id,
            isAlive = isAlive,
            image = image,
            name = name,
            species = species,
            gender = gender,
            origin = origin,
            episodes = Json.decodeFromString<List<String>>(episodes)
        ),
        selectedDay = selectedDay
    )
}