package org.example.project.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.example.project.domain.model.CharacterModel
import org.example.project.domain.model.CharacterOfTheDayModel

@Entity(tableName = "characteroftheday")
data class CharacterOfTheDayEntity(
    @PrimaryKey
    val id: Int,
    val isAlive: Boolean,
    val image: String,
    val name: String,
    val selectedDay: String
)

fun CharacterOfTheDayEntity.toDomain(): CharacterOfTheDayModel {
    return CharacterOfTheDayModel(
        characterModel = CharacterModel(
            id = id,
            isAlive = isAlive,
            image = image,
            name = name
        ),
        selectedDay = selectedDay
    )
}