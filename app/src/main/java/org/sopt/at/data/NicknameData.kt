package org.sopt.at.data
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class NicknameData(
    @SerialName("nickname")
    val nickname: String
)