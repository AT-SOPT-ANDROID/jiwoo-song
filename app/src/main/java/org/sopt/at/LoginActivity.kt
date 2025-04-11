package org.sopt.atsoptandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
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
import androidx.lifecycle.ViewModel
import org.sopt.at.R
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class LoginViewModel : ViewModel() {
    var savedUserId by mutableStateOf("")
    var savedPassword by mutableStateOf("")
}

class LoginActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()
    private val signupLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.let { intent ->
                viewModel.savedUserId = intent.getStringExtra("USER_ID")?.trim() ?: ""
                viewModel.savedPassword = intent.getStringExtra("PASSWORD")?.trim() ?: ""
                Log.d("Login", "Received data: ${viewModel.savedUserId}/${viewModel.savedPassword}")
                Toast.makeText(this, "회원가입 완료! 로그인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ATSOPTANDROIDTheme {
                LoginScreen(
                    viewModel = viewModel,
                    onLoginClick = { inputId, inputPwd ->
                        val trimmedId = inputId.trim()
                        val trimmedPwd = inputPwd.trim()
                        Log.d("Login", "Comparing: $trimmedId/${viewModel.savedUserId}, $trimmedPwd/${viewModel.savedPassword}")
                        when {
                            trimmedId.isEmpty() || trimmedPwd.isEmpty() ->
                                Toast.makeText(this, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                            trimmedId == viewModel.savedUserId && trimmedPwd == viewModel.savedPassword -> {
                                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, MyActivity::class.java))
                                finish()
                            }
                            else ->
                                Toast.makeText(this, "로그인 실패: 정보 불일치", Toast.LENGTH_SHORT).show()
                        }
                    },
                    onSignupClick = {
                        signupLauncher.launch(Intent(this, SignupActivity::class.java))
                    }
                )
            }
        }
    }
}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginClick: (String, String) -> Unit,
    onSignupClick: () -> Unit
) {
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
                onClick = { onLoginClick(textId, textPwd) },
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
                    modifier = Modifier.clickable { onSignupClick() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    ATSOPTANDROIDTheme {
        LoginScreen(
            viewModel = LoginViewModel(),
            onLoginClick = { _, _ -> },
            onSignupClick = {}
        )
    }
}