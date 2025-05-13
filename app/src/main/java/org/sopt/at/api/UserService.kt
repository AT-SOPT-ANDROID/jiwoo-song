package org.sopt.at.api

import org.sopt.at.data.LoginRequestDto
import org.sopt.at.data.LoginResponseDto
import org.sopt.at.data.MyNicknameResponseDto
import org.sopt.at.data.SignupRequestDto
import org.sopt.at.data.SignupResponseDto
import org.sopt.at.data.UpdateNicknameRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface UserService {
    @POST("api/v1/auth/signup")
    suspend fun signUp(
        @Body request: SignupRequestDto
    ): Response<SignupResponseDto>

    @POST("api/v1/auth/signin")
    suspend fun login(
        @Body request: LoginRequestDto
    ): Response<LoginResponseDto>

    @GET("/api/v1/users/me")
    suspend fun getMyInfo(
        @retrofit2.http.Header("userId") userId: Long
    ): Response<MyNicknameResponseDto>

    @PATCH("/api/v1/users")
    suspend fun updateNickname(
        @retrofit2.http.Header("userId") userId: Long,
        @Body request: UpdateNicknameRequestDto
    ): Response<Unit>
}