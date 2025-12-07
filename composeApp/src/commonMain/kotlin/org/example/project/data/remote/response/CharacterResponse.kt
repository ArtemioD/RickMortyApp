package org.example.project.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.project.domain.model.CharacterModel

@Serializable
data class CharacterResponse(
    @SerialName("id") val id: Int,
    val status: String,
    val image: String,
    val name: String,
    val species: String,
    val gender: String,
    val origin: OriginResponse,
    val episode: List<String>
) {
//    fun toDomain(): CharacterModel {
//        return CharacterModel(
//            id = id,
//            isAlive = status.lowercase() == "alive",
//            image = image
//        )
//    }
}

@Serializable
data class OriginResponse(val name: String)

fun CharacterResponse.toDomain(): CharacterModel {
    return CharacterModel(
        id = id,
        isAlive = status.lowercase() == "alive",
        image = image,
        name = name,
        species = species,
        gender = gender,
        origin = origin.name,
        episodes = episode.map { it.substringAfterLast("/") }
    )
}