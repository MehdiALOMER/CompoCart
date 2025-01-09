package com.example.compocart.data.repository

import com.example.compocart.data.datasource.local.dao.AuthTokenDao
import com.example.compocart.data.datasource.remote.api.ApiService
import com.example.compocart.data.datasource.remote.request.LoginRequest
import com.example.compocart.data.datasource.remote.response.LoginResponse
import com.example.compocart.data.model.AuthToken
import javax.inject.Inject
import retrofit2.Response

class AuthRepository @Inject constructor(
    private val apiService: ApiService,
    private val authTokenDao: AuthTokenDao
) {

    suspend fun loginUser(username: String, password: String): Response<LoginResponse> {
        val response = apiService.loginUser(LoginRequest(username, password))
        if (response.isSuccessful) {
            val token = response.body()?.accessToken

            println("LoginUser: Token received -> $token")

            println("LoginUser: response received -> ${response.body()}")

            if (token != null) {
                authTokenDao.deleteToken() // Eski token'ları sil
                authTokenDao.insertToken(AuthToken(accessToken = token)) // Yeni token'ı kaydet
                println("LoginUser: Token saved to Room -> $token")
            }
            else {
                println("LoginUser: Token is null, not saved to Room.")
            }
            return response
        } else {
            throw Exception("Login failed: ${response.errorBody()?.string()}")
        }
    }

    suspend fun getToken(): String? {
        return authTokenDao.getToken()
    }

    suspend fun clearToken() {
        authTokenDao.deleteToken()
    }

}