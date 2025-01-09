package com.example.compocart.data.datasource.remote.response

data class UserProfileResponse(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val image: String
)