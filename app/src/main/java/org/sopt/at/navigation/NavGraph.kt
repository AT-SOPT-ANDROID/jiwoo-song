package org.sopt.at.navigation

import org.sopt.at.screen.ShortsScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.at.screen.LiveScreen
import org.sopt.at.screen.SearchScreen
import org.sopt.at.screen.HistoryScreen
import org.sopt.at.ui.screen.HomeScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") { HomeScreen(navController = navController) }
        composable("shorts") { ShortsScreen() }
        composable("live") { LiveScreen() }
        composable("search") { SearchScreen() }
        composable("history") { HistoryScreen() }
    }
}