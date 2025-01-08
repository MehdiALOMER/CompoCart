package com.example.compocart.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.compocart.data.model.AuthToken

@Dao
interface AuthTokenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(authToken: AuthToken)

    @Query("SELECT accessToken FROM auth_token LIMIT 1")
    suspend fun getToken(): String?

    @Query("DELETE FROM auth_token")
    suspend fun deleteToken()
}