package org.example.project.ui.home.tabs.characters

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.example.project.domain.model.CharacterModel

data class CharactersState(
    val characterOfTheDay: CharacterModel? = null,
    val characters: Flow<PagingData<CharacterModel>> = emptyFlow(),
)
