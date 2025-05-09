package org.sopt.at.data
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    val loginId: String,
    val password: String
)