package org.sopt.at.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.sopt.at.TvingTopBar
import org.sopt.at.component.CategoryTab
import org.sopt.at.BannerSection
import org.sopt.at.SectionWithRow
import org.sopt.at.ui.theme.TivingTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item { TvingTopBar() }
        stickyHeader {
            Surface(color = Color.Black) {
                CategoryTab()
            }
        }
        item { BannerSection() }

        // 필수 섹션
        item {
            SectionWithRow(
                title = "오늘의 티빙 TOP 20"
            )
        }
        item {
            SectionWithRow(
                title = "지금 방영 중인 콘텐츠"
            )
        }
    }
}