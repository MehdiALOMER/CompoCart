package com.example.compocart.data.repository

import com.example.compocart.data.datasource.remote.api.ApiService
import com.example.compocart.data.datasource.remote.response.UserProfileResponse
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val apiService: ApiService,
    private val authRepository: AuthRepository
) {
    suspend fun fetchUserProfile(): UserProfileResponse {
        val token = authRepository.getToken()
        println("Token: $token")
        if (token == null) throw IllegalStateException("Token bulunamadı!")

        val authHeader = "Bearer $token"
        val response = apiService.getUserProfile(authHeader)
        println("API Response: $response")
        return response
    }
}