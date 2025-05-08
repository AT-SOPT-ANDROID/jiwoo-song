package org.sopt.at.data

data class LoginResponseDto(
    val code: String,
    val `data`: Any,
    val message: String,
    val success: Boolean
)