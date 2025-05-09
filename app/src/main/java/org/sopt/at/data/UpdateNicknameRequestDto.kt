package org.sopt.at.data

import kotlinx.serialization.SerialName

data class UpdateNicknameRequestDto(
    @SerialName("nickname")
    val nickname: String
)