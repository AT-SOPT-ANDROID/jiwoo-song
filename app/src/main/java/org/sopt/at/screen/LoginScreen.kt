package org.sopt.atsoptandroid

import kotlinx.coroutines.*
import android.util.Log
import org.sopt.at.api.ServicePool
import org.sopt.at.data.LoginRequestDto

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.sopt.at.R
import org.sopt.at.ui.theme.TivingAppTheme

@Composable
fun LoginScreen(navController: NavController) {
    var textId by remember { mutableStateOf("") }
    var textPwd by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "TVING ID 로그인",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                value = textId,
                onValueChange = { textId = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF262626), RoundedCornerShape(4.dp)),
                placeholder = { Text("아이디", color = Color(0xFFB3B3B3)) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = textPwd,
                onValueChange = { textPwd = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF262626), RoundedCornerShape(4.dp)),
                placeholder = { Text("비밀번호", color = Color(0xFFB3B3B3)) },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(
                        onClick = { passwordVisible = !passwordVisible }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (passwordVisible) R.drawable.eye_on else R.drawable.eye_off
                            ),
                            contentDescription = if (passwordVisible) "비밀번호 보기" else "비밀번호 숨기기",
                            tint = Color(0xFFB3B3B3)
                        )
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    val userService = ServicePool.userService
                    val userId = textId.trim()
                    val password = textPwd.trim()

                    if (userId.isEmpty() || password.isEmpty()) {
                        Toast.makeText(context, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val loginRequest = LoginRequestDto(userId, password)
                            val response = userService.login(loginRequest)

                            if (response.isSuccessful) {
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(context, "로그인 성공!", Toast.LENGTH_SHORT).show()

                                    navController.navigate("home") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                }
                            } else {
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(context, "로그인 실패: 정보 불일치", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } catch (e: Exception) {
                            Log.e("Login", "Login failed: ${e.message}")
                            withContext(Dispatchers.Main) {
                                Toast.makeText(context, "네트워크 오류 발생", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF404040)
                )
            ) {
                Text("로그인 하기", color = Color.White)
            }
            Spacer(modifier = Modifier.height(35.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("아이디 찾기", color = Color.Gray)
                Text(" | ", color = Color.Gray)
                Text("비밀번호 찾기", color = Color.Gray)
                Text(" | ", color = Color.Gray)
                Text(
                    text = "회원가입",
                    color = Color.Gray,
                    modifier = Modifier.clickable {
                        navController.navigate("signup")
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    TivingAppTheme {
        val navController = rememberNavController()
        LoginScreen(
            navController = navController
        )
    }
}