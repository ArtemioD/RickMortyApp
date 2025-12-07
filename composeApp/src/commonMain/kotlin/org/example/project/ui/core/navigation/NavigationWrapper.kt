package org.example.project.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.json.Json
import org.example.project.domain.model.CharacterModel
import org.example.project.ui.detail.CharacterDetailScreen
import org.example.project.ui.home.HomeScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(navController)
        }

        composable<CharacterDetail> {navBackStackEntry ->
            val characterDetailEncoder =navBackStackEntry.toRoute<CharacterDetail>()
            val characterModel = Json.decodeFromString<CharacterModel>(characterDetailEncoder.characterModel)
            CharacterDetailScreen(characterModel)
        }
    }
}