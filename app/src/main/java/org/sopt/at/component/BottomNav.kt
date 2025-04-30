package org.sopt.at.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.sopt.at.R

@Composable
fun BottomNav(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.Black,
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = { navController.navigate("home") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home", modifier = Modifier.size(24.dp), tint = Color(0xFFCCCCCC)) },
            label = { Text("Home", fontSize = 10.sp, textAlign = TextAlign.Center, color = Color(0xFFCCCCCC)) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color(0x33CCCCCC) // 반투명한 회색
            )
        )
        NavigationBarItem(
            selected = currentRoute == "shorts",
            onClick = { navController.navigate("shorts") },
            icon = { Icon(painter = painterResource(id = R.drawable.shorts), contentDescription = "Shorts", modifier = Modifier.size(24.dp), tint = Color(0xFFCCCCCC)) },
            label = { Text("Shorts", fontSize = 10.sp, color = Color(0xFFCCCCCC)) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color(0x33CCCCCC) // 반투명한 회색
            )
        )
        NavigationBarItem(
            selected = currentRoute == "live",
            onClick = { navController.navigate("live") },
            icon = { Icon(painter = painterResource(id = R.drawable.live), contentDescription = "Live", modifier = Modifier.size(24.dp), tint = Color(0xFFCCCCCC)) },
            label = { Text("Live", fontSize = 10.sp, color = Color(0xFFCCCCCC)) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color(0x33CCCCCC) // 반투명한 회색
            )
        )
        NavigationBarItem(
            selected = currentRoute == "search",
            onClick = { navController.navigate("search") },
            icon = { Icon(painter = painterResource(id = R.drawable.search), contentDescription = "Search", modifier = Modifier.size(24.dp), tint = Color(0xFFCCCCCC)) },
            label = { Text("Search", fontSize = 10.sp, color = Color(0xFFCCCCCC)) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color(0x33CCCCCC) // 반투명한 회색
            )
        )
        NavigationBarItem(
            selected = currentRoute == "history",
            onClick = { navController.navigate("history") },
            icon = { Icon(painter = painterResource(id = R.drawable.history), contentDescription = "History", modifier = Modifier.size(24.dp), tint = Color(0xFFCCCCCC)) },
            label = { Text("History", fontSize = 10.sp, color = Color(0xFFCCCCCC)) },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color(0x33CCCCCC) // 반투명한 회색
            )
        )
    }
}