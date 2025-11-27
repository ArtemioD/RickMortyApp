package org.example.project.ui.home.tabs.episodes

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.example.project.domain.model.EpisodeModel

data class EpisodesState(
    val episodes: Flow<PagingData<EpisodeModel>> = emptyFlow(),
    val playVideo: String = ""
)
