package com.example.compocart.data.repository

import com.example.compocart.data.datasource.remote.api.ApiService
import com.example.compocart.data.local.dao.ProductDao
import com.example.compocart.data.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService,
    private val productDao: ProductDao
) {
    suspend fun getProducts(): List<Product> {
        val response = apiService.getProducts()
        return response.products
    }
    fun getLocalProducts(): Flow<List<Product>> = productDao.getAllProducts()

    suspend fun insertProduct(product: Product) = productDao.insertProduct(product)

    suspend fun deleteProduct(product: Product) = productDao.deleteProduct(product)


    suspend fun searchProducts(query: String): List<Product> {
        val response = apiService.searchProducts(query)
        return response.products
    }

}
