package com.example.compocart.ui.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compocart.data.model.Product
import com.example.compocart.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _favorites = MutableStateFlow<List<Product>>(emptyList())
    val favorites: StateFlow<List<Product>> = _favorites.asStateFlow()

    init {
        fetchFavorites()
    }

    private fun fetchFavorites() {
        viewModelScope.launch {
            productRepository.getLocalProducts().collect { products ->
                _favorites.value = products.filter { it.isFavorite }
            }
        }
    }

    fun toggleFavorite(productId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            productRepository.updateFavoriteStatus(productId, isFavorite)
        }
    }
}