package org.sopt.at.component

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.ui.theme.TivingTheme

@Immutable
data class CategoryItem(val name: String)

@Composable
fun CategoryTab(modifier: Modifier = Modifier) {
    val categories = listOf(
        CategoryItem("드라마"),
        CategoryItem("예능"),
        CategoryItem("영화"),
        CategoryItem("스포츠"),
        CategoryItem("애니"),
        CategoryItem("뉴스")
    )
    var selectedIndex by remember { mutableStateOf(0) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        categories.forEachIndexed { index, item ->
            Text(
                text = item.name,
                fontWeight = if (selectedIndex == index) FontWeight.Bold else FontWeight.Normal,
                color = if (selectedIndex == index) Color.White else Color.Gray,
                modifier = Modifier
                    .clickable { selectedIndex = index }
                    .padding(horizontal = 12.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun CategoryTabPreview() {
    TivingTheme {
        CategoryTab()
    }
}