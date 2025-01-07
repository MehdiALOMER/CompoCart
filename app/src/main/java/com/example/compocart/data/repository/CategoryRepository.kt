package com.example.compocart.data.repository

import com.example.compocart.data.datasource.remote.api.ApiService
import com.example.compocart.data.model.Category
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun fetchCategories(): List<Category> {
        return apiService.getCategories()
    }
}