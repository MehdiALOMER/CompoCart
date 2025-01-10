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

    // API'den ürünleri çek ve favori durumunu eşleştir
    suspend fun syncProductsWithFavorites() {
        val apiProducts = apiService.getProducts().products // API'den ürünler
        val localProducts = productDao.getAllProductsOnce() // Room'daki mevcut ürünler

        // Favori durumunu eşleştir
        val updatedProducts = apiProducts.map { apiProduct ->
            val localProduct = localProducts.find { it.id == apiProduct.id }
            if (localProduct != null) {
                // Eğer ürün Room'da varsa, `isFavorite` durumunu koru
                apiProduct.copy(isFavorite = localProduct.isFavorite)
            } else {
                // Eğer ürün Room'da yoksa, varsayılan `isFavorite` durumunu false olarak setle
                apiProduct.copy(isFavorite = false)
            }
        }

        // Güncellenmiş ürünleri Room veritabanına kaydet
        productDao.insertProducts(updatedProducts)
    }

    // Room'dan tüm ürünleri al
    fun getLocalProducts(): Flow<List<Product>> = productDao.getAllProducts()

    // Belirli bir ürünü favorilere ekle
    suspend fun updateFavoriteStatus(productId: Int, isFavorite: Boolean) {
        productDao.updateFavoriteStatus(productId, isFavorite)
    }


    suspend fun searchProducts(query: String): List<Product> {
        val response = apiService.searchProducts(query)
        return response.products
    }

}
