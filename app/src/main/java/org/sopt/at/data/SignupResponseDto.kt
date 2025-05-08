package org.sopt.at.data

data class SignupResponseDto(
    val code: String,
    val `data`: Any,
    val message: String,
    val success: Boolean
)