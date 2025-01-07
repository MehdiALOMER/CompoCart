package com.example.compocart.data.datasource.remote.api

import com.example.compocart.data.datasource.remote.response.ProductResponse
import com.example.compocart.data.model.Category
import com.example.compocart.data.model.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products/categories")
    suspend fun getCategories(): List<Category>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): List<Product>

    @GET("products/search")
    suspend fun searchProducts(@Query("q") query: String): ProductResponse
}