package org.example.project.ui.core.navigation

import kotlinx.serialization.Serializable
import org.example.project.domain.model.CharacterModel

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    //Bottom Navigation
    data object Characters : Routes("characters")
    data object Episodes : Routes("episodes")

}

@Serializable
data class CharacterDetail(val characterModel: String)