package org.sopt.at.data

import kotlinx.serialization.SerialName

data class SignupRequestDto(
    @SerialName("loginId")
    val userId: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("password")
    val password: String
)