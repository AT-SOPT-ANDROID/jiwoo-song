package org.sopt.at.data

import kotlinx.serialization.SerialName

data class MyNicknameResponseDto(
    @SerialName("code")
    val code: String,
    @SerialName("data")
    val data: Data,
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val success: Boolean
)