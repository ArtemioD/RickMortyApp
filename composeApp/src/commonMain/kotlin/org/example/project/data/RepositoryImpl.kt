package org.example.project.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.example.project.data.database.RickMortyDatabase
import org.example.project.data.database.entity.toDomain
import org.example.project.data.remote.ApiService
import org.example.project.data.remote.paging.CharactersPagingSource
import org.example.project.data.remote.paging.EpisodesPagingSource
import org.example.project.data.remote.response.toDomain
import org.example.project.domain.Repository
import org.example.project.domain.model.CharacterModel
import org.example.project.domain.model.CharacterOfTheDayModel
import org.example.project.domain.model.EpisodeModel
import org.example.project.domain.model.toEntity

class RepositoryImpl(
    val api: ApiService,
    private val pagingSource: CharactersPagingSource,
    private val episodesSource: EpisodesPagingSource,
    private val rickMortyDatabase: RickMortyDatabase
) :
    Repository {

    override suspend fun getSingleCharacter(id: String): CharacterModel {
        return api.getSingleCharacter(id).toDomain()
    }

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = { pagingSource }
        ).flow
    }

    override fun getAllEpisodes(): Flow<PagingData<EpisodeModel>> {
        return Pager(
            config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_ITEMS),
            pagingSourceFactory = { episodesSource }
        ).flow
    }

    override suspend fun getCharacterDB(): CharacterOfTheDayModel? {
        return rickMortyDatabase.getPreferencesDao().getCharacterOfTheDayDB()?.toDomain()
    }

    override suspend fun saveCharacterDB(characterOfTheDayModel: CharacterOfTheDayModel) {
        rickMortyDatabase.getPreferencesDao().saveCharacter(characterOfTheDayModel.toEntity())
    }

    override suspend fun getEpisodesForCharacter(episodes: List<String>): List<EpisodeModel> {

        if (episodes.isEmpty()) return emptyList()

        return if (episodes.size > 1) {
            api.getEpisodes(episodes.joinToString(",")).map { episodeModel ->
                episodeModel.toDomain()
            }
        } else {
            listOf(api.getSingleEpisode(episodes.first()).toDomain())
        }

    }

    companion object {
        const val MAX_ITEMS = 20
        const val PREFETCH_ITEMS = 5
    }
}



