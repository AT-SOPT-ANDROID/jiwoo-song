package org.sopt.at.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class LoginData(
    @SerialName("userId")
    val userId: Long
)

@Serializable
data class LoginResponseDto(
    @SerialName("code")
    val code: String,
    @SerialName("data")
    val data: LoginData,
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val success: Boolean
)