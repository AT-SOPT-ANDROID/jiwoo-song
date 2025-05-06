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
import androidx.compose.runtime.Immutable
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

@Immutable
data class BottomNavItem(
    val route: String,
    val label: String,
    val iconResId: Int
)

@Composable
fun BottomNav(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomNavItem("home", "Home", -1),
        BottomNavItem("shorts", "Shorts", R.drawable.shorts),
        BottomNavItem("live", "Live", R.drawable.live),
        BottomNavItem("search", "Search", R.drawable.search),
        BottomNavItem("history", "History", R.drawable.history)
    )

    NavigationBar(
        containerColor = Color.Black,
        contentColor = Color.White
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) },
                icon = {
                    if (item.iconResId != -1) {
                        Icon(
                            painter = painterResource(id = item.iconResId),
                            contentDescription = item.label,
                            modifier = Modifier.size(24.dp),
                            tint = Color(0xFFCCCCCC)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = item.label,
                            modifier = Modifier.size(24.dp),
                            tint = Color(0xFFCCCCCC)
                        )
                    }
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFFCCCCCC)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color(0x33CCCCCC)
                )
            )
        }
    }
}