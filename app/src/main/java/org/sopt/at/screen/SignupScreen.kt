package org.sopt.atsoptandroid

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import org.sopt.at.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.ui.theme.TivingAppTheme


import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController: NavController) {
    var currentStep by remember { mutableStateOf(1) }
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {
                        if (currentStep == 1) {
                            navController.popBackStack()
                        } else {
                            currentStep = 1
                        }
                    }) {
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
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentStep) {
                1 -> IdInputScreen(
                    userId = userId,
                    onUserIdChanged = { userId = it },
                    onNextClicked = { currentStep = 2 }
                )
                2 -> PasswordInputScreen(
                    password = password,
                    onPasswordChanged = { password = it },
                    onComplete = {
                        navController.previousBackStackEntry?.savedStateHandle?.set("USER_ID", userId)
                        navController.previousBackStackEntry?.savedStateHandle?.set("PASSWORD", password)
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

@Composable
fun IdInputScreen(
    userId: String,
    onUserIdChanged: (String) -> Unit,
    onNextClicked: () -> Unit
) {
    val context = LocalContext.current
    val isValidId = validateUserId(userId)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "아이디를 입력해주세요.",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            TextField(
                value = userId,
                onValueChange = onUserIdChanged,
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
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "영문 소문자 또는 영문 소문자,숫자 조합 6~12자리",
                color = Color(0xFFB3B3B3),
                fontSize = 12.sp
            )
        }
        Button(
            onClick = {
                when {
                    userId.isEmpty() ->
                        Toast.makeText(context, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
                    !isValidId ->
                        Toast.makeText(context, "조건을 확인해주세요", Toast.LENGTH_SHORT).show()
                    isValidId -> {
                        Toast.makeText(context, "아이디 입력 완료!", Toast.LENGTH_SHORT).show()
                        onNextClicked()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .border(
                    width = 1.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(4.dp)
                ),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {
            Text("다음", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PasswordInputScreen(
    password: String,
    onPasswordChanged: (String) -> Unit,
    onComplete: () -> Unit
) {
    val context = LocalContext.current
    val isValidPassword = validatePassword(password)
    var passwordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "비밀번호를 입력해주세요.",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            TextField(
                value = password,
                onValueChange = onPasswordChanged,
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
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "영문, 숫자, 특수문자(~!@#$%^&*) 조합 8~15자리",
                color = Color(0xFFB3B3B3),
                fontSize = 12.sp
            )
        }
        Button(
            onClick = {
                when {
                    password.isEmpty() ->
                        Toast.makeText(context, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                    !isValidPassword ->
                        Toast.makeText(context, "조건을 확인해주세요", Toast.LENGTH_SHORT).show()
                    isValidPassword -> onComplete()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .border(
                    width = 1.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(4.dp)
                ),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {
            Text("완료", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

fun validateUserId(userId: String): Boolean {
    return userId.length in 6..12 &&
            userId.first().isLowerCase() &&
            userId.all { it.isLowerCase() || it.isDigit() }
}

fun validatePassword(password: String): Boolean {
    return password.length in 8..15 &&
            password.any { it.isLetter() } &&
            password.any { it.isDigit() } &&
            password.any { "~!@#$%^&*".contains(it) }
}

@Preview(showBackground = true)
@Composable
fun SignupPreview() {
    TivingAppTheme {
        val navController = rememberNavController()
        SignupScreen(navController = navController)
    }
}