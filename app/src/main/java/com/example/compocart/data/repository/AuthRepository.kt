package com.example.compocart.data.repository

import com.example.compocart.data.datasource.remote.api.ApiService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService
) {
//    suspend fun login(username: String, password: String) = apiService.login(username, password)
}