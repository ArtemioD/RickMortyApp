package org.example.project.ui.core.navigation.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.example.project.ui.core.navigation.Routes
import org.example.project.ui.home.tabs.characters.CharactersScreen
import org.example.project.ui.home.tabs.episodes.EpisodesScreen

@Composable
fun NavigationBottomWrapper(navController: NavHostController) {

    NavHost(navController, startDestination = Routes.Episodes.route){
        composable(Routes.Episodes.route){
            EpisodesScreen()
        }

        composable(Routes.Characters.route){
            CharactersScreen()
        }
    }
}