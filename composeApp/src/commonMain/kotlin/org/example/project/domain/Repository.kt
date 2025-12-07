package org.example.project.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.example.project.domain.model.CharacterModel
import org.example.project.domain.model.CharacterOfTheDayModel
import org.example.project.domain.model.EpisodeModel

interface Repository {
    suspend fun getSingleCharacter(id: String): CharacterModel

    fun getAllCharacters(): Flow<PagingData<CharacterModel>>

    fun getAllEpisodes(): Flow<PagingData<EpisodeModel>>

    suspend fun getCharacterDB(): CharacterOfTheDayModel?

    suspend fun saveCharacterDB(characterOfTheDayModel: CharacterOfTheDayModel)
    suspend fun getEpisodesForCharacter(episodes: List<String>): List<EpisodeModel>

}