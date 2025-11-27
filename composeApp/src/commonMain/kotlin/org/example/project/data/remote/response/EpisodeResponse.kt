package org.example.project.data.remote.response

import kotlinx.serialization.Serializable
import org.example.project.domain.model.EpisodeModel
import org.example.project.domain.model.SeasonEpisode
import org.example.project.domain.model.SeasonEpisode.*

@Serializable
data class EpisodeResponse(
    val id: Int,
    val name: String,
    val episode: String,
    val characters: List<String>
)

fun EpisodeResponse.toDomain(): EpisodeModel {
    val season = getSeasonFromEpisodeCode(episode)
    return EpisodeModel(
        id = id,
        name = name,
        episode = episode,
        characters = characters.map { url ->
            url.substringAfterLast("/")
        },
        videoUrl = getVideoUrlFromEpisodeCode(season),
        season = season
    )
}

private fun getVideoUrlFromEpisodeCode(season: SeasonEpisode): String {
    return when (season) {
        SEASON_1 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-b33d6.firebasestorage.app/o/rick_y_morty_trailer_es.mp4?alt=media&token=a09bcdc4-26d8-48aa-ac9f-5a9aedcb0878"
        SEASON_2 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-b33d6.firebasestorage.app/o/rick_y_morty_trailer_es.mp4?alt=media&token=a09bcdc4-26d8-48aa-ac9f-5a9aedcb0878"
        SEASON_3 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-b33d6.firebasestorage.app/o/rick_y_morty_trailer_es.mp4?alt=media&token=a09bcdc4-26d8-48aa-ac9f-5a9aedcb0878"
        SEASON_4 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-b33d6.firebasestorage.app/o/rick_y_morty_trailer_es.mp4?alt=media&token=a09bcdc4-26d8-48aa-ac9f-5a9aedcb0878"
        SEASON_5 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-b33d6.firebasestorage.app/o/rick_y_morty_trailer_es.mp4?alt=media&token=a09bcdc4-26d8-48aa-ac9f-5a9aedcb0878"
        SEASON_6 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-b33d6.firebasestorage.app/o/rick_y_morty_trailer_es.mp4?alt=media&token=a09bcdc4-26d8-48aa-ac9f-5a9aedcb0878"
        SEASON_7 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-b33d6.firebasestorage.app/o/rick_y_morty_trailer_es.mp4?alt=media&token=a09bcdc4-26d8-48aa-ac9f-5a9aedcb0878"
        SEASON_8 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-b33d6.firebasestorage.app/o/rick_y_morty_trailer_es.mp4?alt=media&token=a09bcdc4-26d8-48aa-ac9f-5a9aedcb0878"
        UNKNOWN -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-b33d6.firebasestorage.app/o/rick_y_morty_trailer_es.mp4?alt=media&token=a09bcdc4-26d8-48aa-ac9f-5a9aedcb0878"
    }
}

private fun getSeasonFromEpisodeCode(episodeCode: String): SeasonEpisode {
    return when{
        episodeCode.startsWith("S01") -> SEASON_1
        episodeCode.startsWith("S02") -> SEASON_2
        episodeCode.startsWith("S03") -> SEASON_3
        episodeCode.startsWith("S04") -> SEASON_4
        episodeCode.startsWith("S05") -> SEASON_5
        episodeCode.startsWith("S06") -> SEASON_6
        episodeCode.startsWith("S07") -> SEASON_7
        else -> UNKNOWN
    }
}
