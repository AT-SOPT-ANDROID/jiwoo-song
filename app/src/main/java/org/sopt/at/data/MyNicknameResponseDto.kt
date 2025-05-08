package org.sopt.at.data

data class MyNicknameResponseDto(
    val code: String,
    val `data`: Data,
    val message: String,
    val success: Boolean
)