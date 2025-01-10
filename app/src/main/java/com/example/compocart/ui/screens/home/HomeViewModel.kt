package com.example.compocart.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compocart.data.model.Category
import com.example.compocart.data.repository.ProductRepository
import com.example.compocart.data.model.Product
import com.example.compocart.data.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        fetchCategories()
        fetchProducts()
        syncProducts()
    }

    private fun syncProducts() {
        viewModelScope.launch {
            productRepository.syncProductsWithFavorites()
            productRepository.getLocalProducts().collect { productList ->
                _products.value = productList
            }
        }
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val result = categoryRepository.fetchCategories()
                _categories.value = result
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val fetchedProducts = productRepository.getProducts()
                _products.value = fetchedProducts
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchProducts(query: String) {
        _searchQuery.value = query
        if (query.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    val searchResults = productRepository.searchProducts(query)
                    _products.value = searchResults
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } else {
            fetchProducts()
        }
    }
}