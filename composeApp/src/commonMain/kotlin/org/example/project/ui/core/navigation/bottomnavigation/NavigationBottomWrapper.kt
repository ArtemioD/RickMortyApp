package org.example.project.ui.core.navigation.bottomnavigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.json.Json
import org.example.project.ui.core.navigation.CharacterDetail
import org.example.project.ui.core.navigation.Routes
import org.example.project.ui.home.tabs.characters.CharactersScreen
import org.example.project.ui.home.tabs.episodes.EpisodesScreen

@Composable
fun NavigationBottomWrapper(
    navController: NavHostController,
    mainNavController: NavHostController,
) {

    NavHost(navController, startDestination = Routes.Episodes.route) {
        composable(Routes.Episodes.route) {
            EpisodesScreen()
        }

        composable(Routes.Characters.route) {
            CharactersScreen(navigateToDetail = { characterModel ->
                val encode = Json.encodeToString(characterModel)
                mainNavController.navigate(CharacterDetail(encode))
            })
        }
    }
}