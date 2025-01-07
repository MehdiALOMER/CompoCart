package com.example.compocart.di

import com.example.compocart.data.datasource.remote.api.ApiService
import com.example.compocart.data.local.dao.ProductDao
import com.example.compocart.data.repository.CategoryRepository
import com.example.compocart.data.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductRepository(
        apiService: ApiService,
        productDao: ProductDao
    ): ProductRepository {
        return ProductRepository(apiService, productDao)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(apiService: ApiService): CategoryRepository {
        return CategoryRepository(apiService)
    }
}