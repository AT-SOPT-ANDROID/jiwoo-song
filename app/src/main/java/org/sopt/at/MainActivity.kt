package org.sopt.at

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.at.navigation.TivingApp
import org.sopt.at.ui.screen.HomeScreen
import org.sopt.at.ui.theme.TivingAppTheme
import org.sopt.atsoptandroid.LoginScreen
import org.sopt.atsoptandroid.LoginViewModel
import org.sopt.atsoptandroid.MyPageScreen
import org.sopt.atsoptandroid.SignupScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TivingApp()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TivingPreview() {
    TivingAppTheme {
        TivingApp()
    }
}