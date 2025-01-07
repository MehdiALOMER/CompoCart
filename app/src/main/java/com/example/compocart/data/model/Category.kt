package com.example.compocart.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey val slug: String,
    val name: String,
    val url: String
)