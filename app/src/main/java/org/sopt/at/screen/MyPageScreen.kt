package org.sopt.atsoptandroid

import kotlinx.coroutines.*
import android.util.Log
import org.sopt.at.api.ServicePool
import org.sopt.at.data.UpdateNicknameRequestDto

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.sopt.at.R
import org.sopt.at.ui.theme.TivingAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    nickname: String
) {
    val nicknameState = remember { mutableStateOf(nickname) }
    var isDialogOpen by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    navigationIconContentColor = Color.White
                )
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 프로필 이미지와 이메일을 가로로 배치
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Profile Image",
                    modifier = Modifier.size(100.dp)
                )
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = nicknameState.value,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        IconButton(onClick = { isDialogOpen = true }) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_pencil),
                                contentDescription = "닉네임 수정",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
        if (isDialogOpen) {
            var newNickname by remember { mutableStateOf("") }
            AlertDialog(
                onDismissRequest = { isDialogOpen = false }, //모달창 닫힘 처리
                title = { Text("새 닉네임을 입력해주세요") },
                text = {
                    OutlinedTextField(
                        value = newNickname,
                        onValueChange = { newNickname = it },
                        label = { Text("새 닉네임") }
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val userService = ServicePool.userService
                            CoroutineScope(Dispatchers.IO).launch {
                                try {
                                    val response = userService.updateNickname(UpdateNicknameRequestDto(newNickname))
                                    if (response.isSuccessful) {
                                        withContext(Dispatchers.Main) {
                                            nicknameState.value = newNickname
                                            isDialogOpen = false
                                        }
                                    }
                                } catch (e: Exception) {
                                    Log.e("NicknameUpdate", "Update failed: ${e.message}")
                                }
                            }
                        }
                    ) {
                        Text("확인")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { isDialogOpen = false }) {
                        Text("취소")
                    }
                },
                containerColor = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPagePreview() {
    TivingAppTheme {
        MyPageScreen(navController = rememberNavController(), nickname = "SampleNickname")
    }
}