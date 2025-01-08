package com.example.compocart.data.datasource.remote.response

data class LoginResponse(
    val id: Int?,
    val username: String?,
    val email: String?,
    val accessToken: String?
)