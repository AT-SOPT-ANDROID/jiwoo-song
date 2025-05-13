package org.sopt.at.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.at.ui.screen.HomeScreen
import org.sopt.atsoptandroid.LoginScreen
import org.sopt.atsoptandroid.MyPageScreen
import org.sopt.atsoptandroid.SignupScreen

@Composable
fun TivingApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController = navController)
        }

        composable("signup") {
            SignupScreen(navController = navController)
        }

        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("mypage") {
            MyPageScreen(navController = navController, userId = 390L) // 실제 userId 사용
        }
    }
}