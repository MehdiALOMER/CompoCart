package com.example.compocart.di

import com.example.compocart.data.datasource.remote.api.ApiService
import com.example.compocart.data.remote.api.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideApiService(): ApiService = ApiClient.create()
}