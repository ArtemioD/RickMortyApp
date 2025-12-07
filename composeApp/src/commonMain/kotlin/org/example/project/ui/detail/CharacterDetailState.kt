package org.example.project.ui.detail

import org.example.project.domain.model.CharacterModel
import org.example.project.domain.model.EpisodeModel

data class CharacterDetailState(
    val characterModel: CharacterModel,
    val episodes: List<EpisodeModel>? = null
)