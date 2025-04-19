package org.sopt.at.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

@Composable
fun BottomNav() {
    var selectedTab by remember { mutableStateOf(0) }

    NavigationBar(
        containerColor = Color.Black,
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = selectedTab == 0,
            onClick = { selectedTab = 0 },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home", fontSize = 10.sp, textAlign = TextAlign.Center) }
        )
        NavigationBarItem(
            selected = selectedTab == 1,
            onClick = { selectedTab = 1 },
            icon = { Text("🎞", fontSize = 20.sp) },
            label = { Text("Shorts", fontSize = 10.sp) }
        )
        NavigationBarItem(
            selected = selectedTab == 2,
            onClick = { selectedTab = 2 },
            icon = { Text("📡", fontSize = 20.sp) },
            label = { Text("Live", fontSize = 10.sp) }
        )
        NavigationBarItem(
            selected = selectedTab == 3,
            onClick = { selectedTab = 3 },
            icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            label = { Text("Search", fontSize = 10.sp) }
        )
        NavigationBarItem(
            selected = selectedTab == 4,
            onClick = { selectedTab = 4 },
            icon = { Text("🕓", fontSize = 20.sp) },
            label = { Text("History", fontSize = 10.sp) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavPreview() {
    ATSOPTANDROIDTheme {
        var selectedTab by remember { mutableStateOf(0) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                NavigationBar(containerColor = Color.Black) {
                    NavigationBarItem(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home", fontSize = 10.sp, textAlign = TextAlign.Center, color = Color.White) }
                    )
                    NavigationBarItem(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        icon = { Text("🎞", color = Color.White) },
                        label = { Text("Shorts", color = Color.White) }
                    )
                    NavigationBarItem(
                        selected = selectedTab == 2,
                        onClick = { selectedTab = 2 },
                        icon = { Text("📡", color = Color.White) },
                        label = { Text("Live", color = Color.White) }
                    )
                    NavigationBarItem(
                        selected = selectedTab == 3,
                        onClick = { selectedTab = 3 },
                        icon = { Text("🔍", color = Color.White) },
                        label = { Text("Search", color = Color.White) }
                    )
                    NavigationBarItem(
                        selected = selectedTab == 4,
                        onClick = { selectedTab = 4 },
                        icon = { Text("🕓", color = Color.White) },
                        label = { Text("History", color = Color.White) }
                    )
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                when (selectedTab) {
                    0 -> Text(text = "Home Screen")
                    1 -> Text(text = "Shorts Screen")
                    2 -> Text(text = "Live Screen")
                    3 -> Text(text = "Search Screen")
                    4 -> Text(text = "History Screen")
                }
            }
        }
    }
}