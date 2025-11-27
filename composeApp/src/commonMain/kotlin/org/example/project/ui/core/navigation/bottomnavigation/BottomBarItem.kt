package org.example.project.ui.core.navigation.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.example.project.ui.core.navigation.Routes

sealed class BottomBarItem {
    abstract val route: String
    abstract val title: String
    abstract val icon: @Composable () -> Unit

    data class Episodes(
        override val route: String = Routes.Episodes.route,
        override val title: String = "Episodes",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Home, contentDescription = "Episodes")
        }
    ) : BottomBarItem()

    data class Characters(
        override val route: String = Routes.Characters.route,
        override val title: String = "Characters",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Person, contentDescription = "Characters")
        }
    ) : BottomBarItem() {}
}