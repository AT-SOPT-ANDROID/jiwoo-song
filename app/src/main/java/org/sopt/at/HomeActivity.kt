package org.sopt.at

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.component.BottomNav
import org.sopt.at.component.CategoryTab
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.atsoptandroid.MyActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvingTopBar() {
    val context = LocalContext.current

    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tving_logo),
                    contentDescription = "TVING Logo",
                    modifier = Modifier
                        .height(28.dp)
                        .padding(start = 8.dp)
                )
                IconButton(onClick = {
                    context.startActivity(Intent(context, MyActivity::class.java))
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "My Page",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black,
            titleContentColor = Color.White
        )
    )
}

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TvingTopBar() },
                    bottomBar = { BottomNav() }
                ) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 64.dp), // BottomBar 가림 방지 여유 공간
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item { CategoryTab() }
            item { BannerSection() }

            // 필수 섹션
            item { SectionWithRow(title = "오늘의 티빙 TOP 20") }
            item { SectionWithRow(title = "지금 방영 중인 콘텐츠") }
            item { SectionWithRow(title = "실시간 인기 LIVE") }

            // 추가 섹션
            item { SectionWithRow(title = "웹툰을 찢고 나온 오빠들") }
        }
    }
}

@Composable
fun CategoryTab() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(5) {
            Text("Category $it")
        }
    }
}

@Composable
fun BannerSection() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_report_image),
            contentDescription = "Mock Banner",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun SectionWithRow(title: String) {
    Column(modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = "더보기", modifier = Modifier.padding(end = 16.dp))
        }
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(10) {
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 120.dp, height = 180.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ATSOPTANDROIDTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Black,
            topBar = { TvingTopBar() },
            bottomBar = { BottomNav() }
        ) { innerPadding ->
            HomeScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}