package org.sopt.at.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateNicknameResponsetDto(
    @SerialName("code")
    val code: String,
    @SerialName("data")
    val data: UpdatedNicknameData,
    @SerialName("message")
    val message: String,
    @SerialName("success")
    val success: Boolean
)

@Serializable
data class UpdatedNicknameData(
    @SerialName("nickname")
    val nickname: String
)