package com.example.compocart.ui.screens.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.compocart.R
import com.example.compocart.data.model.Product
import com.example.compocart.ui.screens.favorite.FavoritesViewModel
import com.example.compocart.ui.screens.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: Int?,
    homeViewModel: HomeViewModel,
    favoritesViewModel: FavoritesViewModel
) {
    if (productId == null) {
        Text("Product not found")
        return
    }

    // Ürünleri ViewModel'den al
    val products = homeViewModel.products.collectAsState().value
    val product = products.find { it.id == productId }

    if (product == null) {
        Text("Product not found")
        return
    }

    // `isFavorite` durumunu ViewModel'den kontrol et
    val isFavorite = remember { mutableStateOf(product.isFavorite) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Product Details") },
                actions = {
                    IconButton(onClick = { /* Navigate to cart */ }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart")
                    }
                }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Add to cart functionality */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Add to Cart")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Product Image
            AsyncImage(
                model = product.images?.firstOrNull(),
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Product Title and Favorite Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = {
                    isFavorite.value = !isFavorite.value
                    // Favori durumu ViewModel'e kaydedilir
                    favoritesViewModel.toggleFavorite(product.id, isFavorite.value)
                }) {
                    Icon(
                        imageVector = if (isFavorite.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorite"
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Price and Stock
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (product.stock > 0) "In Stock" else "Out of Stock",
                    color = if (product.stock > 0) Color.Green else Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Product Description
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}