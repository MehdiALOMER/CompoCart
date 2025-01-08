package com.example.compocart.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_token")
data class AuthToken(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val accessToken: String
)