package org.sopt.at.data
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class LoginRequestDto(
    @SerialName("loginId")
    val userId: String,
    @SerialName("password")
    val password: String
)