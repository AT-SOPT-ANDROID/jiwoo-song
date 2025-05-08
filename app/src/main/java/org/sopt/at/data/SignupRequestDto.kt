package org.sopt.at.data

data class SignupRequestDto(
    val loginId: String,
    val nickname: String,
    val password: String
)