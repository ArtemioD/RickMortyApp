package org.example.project.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.ui.home.HomeScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen()
        }
    }
}