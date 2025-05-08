package org.sopt.at.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.at.ui.screen.HomeScreen
import org.sopt.atsoptandroid.LoginScreen
import org.sopt.atsoptandroid.LoginViewModel
import org.sopt.atsoptandroid.MyPageScreen
import org.sopt.atsoptandroid.SignupScreen

@Composable
fun TivingApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            val viewModel = remember { LoginViewModel() }
            LoginScreen(navController = navController, viewModel = viewModel)
        }

        composable("signup") {
            SignupScreen(navController = navController)
        }

        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("mypage") {
            MyPageScreen(navController = navController)
        }
    }
}