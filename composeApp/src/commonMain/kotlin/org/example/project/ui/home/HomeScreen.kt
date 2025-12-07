package org.example.project.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.example.project.ui.core.navigation.bottomnavigation.BottomBarItem
import org.example.project.ui.core.navigation.bottomnavigation.BottomBarItem.Characters
import org.example.project.ui.core.navigation.bottomnavigation.BottomBarItem.Episodes
import org.example.project.ui.core.navigation.bottomnavigation.NavigationBottomWrapper

@Composable
fun HomeScreen(mainNavController: NavHostController) {

    val items = listOf(Episodes(), Characters())
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomNavigation(items, navController) }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavigationBottomWrapper(navController, mainNavController)
        }
    }
}

@Composable
fun BottomNavigation(items: List<BottomBarItem>, navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = item.icon,
                label = { Text(item.title) },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(route = item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                alwaysShowLabel = false
            )
        }
    }
}