package org.sopt.at.data

data class NicknameListResponseDto(
    val code: String,
    val `data`: DataX,
    val message: String,
    val success: Boolean
)