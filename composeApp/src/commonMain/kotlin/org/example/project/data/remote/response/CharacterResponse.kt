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
) {
//    fun toDomain(): CharacterModel {
//        return CharacterModel(
//            id = id,
//            isAlive = status.lowercase() == "alive",
//            image = image
//        )
//    }
}

fun CharacterResponse.toDomain(): CharacterModel {
    return CharacterModel(
        id = id,
        isAlive = status.lowercase() == "alive",
        image = image,
        name = name
    )
}
