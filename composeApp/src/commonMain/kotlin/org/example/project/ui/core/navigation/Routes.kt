package org.example.project.ui.core.navigation

sealed class Routes(val route: String) {
    data object Home : Routes("home")

    //Bottom Navigation
    data object Characters : Routes("characters")
    data object Episodes : Routes("episodes")

}