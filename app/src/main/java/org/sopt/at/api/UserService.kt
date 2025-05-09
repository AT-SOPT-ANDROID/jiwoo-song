package org.sopt.at.api

import org.sopt.at.data.MyNicknameResponseDto
import org.sopt.at.data.UpdateNicknameRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface UserService {
    @GET("/api/v1/users/me")
    suspend fun getMyInfo(): Response<MyNicknameResponseDto>

    @PATCH("/api/v1/users")
    suspend fun updateNickname(
        @Body request: UpdateNicknameRequestDto
    ): Response<Unit>
}