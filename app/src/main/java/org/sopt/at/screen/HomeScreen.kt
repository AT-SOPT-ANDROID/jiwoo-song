package org.sopt.at.ui.screen

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.sopt.at.R
import org.sopt.atsoptandroid.MyPageScreen
import org.sopt.at.ui.theme.TivingTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item { TvingTopBar(navController) }
        stickyHeader {
            Surface(color = Color.Black) {
                CategoryTab()
            }
        }
        item { BannerSection() }

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvingTopBar(navController: NavHostController) {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(R.drawable.tving_logo)
                        .crossfade(true)
                        .build(),
                    contentDescription = "TVING Logo",
                    modifier = Modifier
                        .height(28.dp)
                        .padding(start = 8.dp)
                )
                IconButton(onClick = {
                    navController.navigate("mypage")
                }) {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(R.drawable.profile)
                            .crossfade(true)
                            .build(),
                        contentDescription = "My Page",
                        modifier = Modifier.size(50.dp)
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

@Composable
fun CategoryTab() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val categories = listOf("드라마", "예능", "영화", "스포츠", "애니", "뉴스")
        var selectedIndex by remember { mutableStateOf(0) }

        categories.forEachIndexed { index, title ->
            Text(
                text = title,
                modifier = Modifier.clickable { selectedIndex = index },
                color = if (selectedIndex == index) Color.White else Color.Gray,
                fontSize = 18.sp,
                fontWeight = if (selectedIndex == index) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

@Composable
fun BannerSection() {
    Box(modifier = Modifier.fillMaxWidth()) {
        StableImage(
            drawableResId = R.drawable.img_20,
            contentDescription = "Mock Banner",
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun SectionWithRow(title: String) {
    val imageList = if (title == "오늘의 티빙 TOP 20") {
        emptyList()
    } else {
        listOf(
            R.drawable.img_01, R.drawable.img_02, R.drawable.img_03, R.drawable.img_04, R.drawable.img_05,
            R.drawable.img_06, R.drawable.img_07, R.drawable.img_08, R.drawable.img_09, R.drawable.img_10,
            R.drawable.img_11, R.drawable.img_12, R.drawable.img_13, R.drawable.img_14, R.drawable.img_15,
            R.drawable.img_16, R.drawable.img_17, R.drawable.img_18, R.drawable.img_19, R.drawable.img_20
        ).shuffled().take(10)
    }

    Column(modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = TivingTheme.typography.title1.b24,
                color = TivingTheme.colors.basicWhite
            )
            Text(
                text = "더보기",
                style = TivingTheme.typography.body1.r16,
                color = TivingTheme.colors.gray01,
                modifier = Modifier.padding(end = 16.dp)
            )
        }

        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            if (title == "오늘의 티빙 TOP 20") {
                items(20) { index ->
                    val resId = when (index + 1) {
                        1 -> R.drawable.img_01
                        2 -> R.drawable.img_02
                        3 -> R.drawable.img_03
                        4 -> R.drawable.img_04
                        5 -> R.drawable.img_05
                        6 -> R.drawable.img_06
                        7 -> R.drawable.img_07
                        8 -> R.drawable.img_08
                        9 -> R.drawable.img_09
                        10 -> R.drawable.img_10
                        11 -> R.drawable.img_11
                        12 -> R.drawable.img_12
                        13 -> R.drawable.img_13
                        14 -> R.drawable.img_14
                        15 -> R.drawable.img_15
                        16 -> R.drawable.img_16
                        17 -> R.drawable.img_17
                        18 -> R.drawable.img_18
                        19 -> R.drawable.img_19
                        20 -> R.drawable.img_20
                        else -> android.R.drawable.ic_menu_gallery
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "${index + 1}",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .offset(y = (40).dp)
                        )
                        StableImage(
                            drawableResId = resId,
                            modifier = Modifier
                                .size(width = 120.dp, height = 180.dp)
                        )
                    }
                }
            } else {
                items(imageList.size) { index ->
                    val resId = imageList[index]
                    StableImage(
                        drawableResId = resId,
                        modifier = Modifier.size(width = 120.dp, height = 180.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun StableImage(
    modifier: Modifier = Modifier,
    drawableResId: Int,
    contentDescription: String = "",
    contentScale: ContentScale = ContentScale.Fit
) {
    val painter = painterResource(id = drawableResId)
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}