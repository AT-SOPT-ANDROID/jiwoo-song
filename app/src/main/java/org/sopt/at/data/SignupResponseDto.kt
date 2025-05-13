package org.sopt.at.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SignupResponseDto(
    @SerialName("code")
    val code: String,
    @SerialName("data")
    val data: SignupData,
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val success: Boolean
)

@Serializable
data class SignupData(
    @SerialName("loginId")
    val userId: Long,
    @SerialName("nickname")
    val nickname: String
)