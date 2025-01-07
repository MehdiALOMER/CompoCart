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
import com.example.compocart.ui.screens.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(productId: Int?, viewModel: HomeViewModel) {
    var selectedColor by remember { mutableStateOf(Color.Red) }
    var selectedSize by remember { mutableStateOf(7) }
    var quantity by remember { mutableStateOf(1) }
    var isFavorite by remember { mutableStateOf(false) }

    if (productId == null) {
        Text("Product not found")
        return
    }

    // Ürünleri ViewModel'den al
    val products = viewModel.products.collectAsState().value
    val product = products.find { it.id == productId }

    if (product == null) {
        Text("Product not found")
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Product Details") },
                navigationIcon = {
//                    IconButton(onClick = { /* Navigate back */ }) {
//                        Icon(painter = painterResource(R.drawable.ic_back), contentDescription = "Back")
//                    }
                },
                actions = {
                    IconButton(onClick = { /* Navigate to cart */ }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart")
                    }
                }
            )
        },
        // Add to Cart Section
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
                Spacer(modifier = Modifier.width(16.dp))
                OutlinedButton(onClick = { if (quantity > 1) quantity-- }) {
                    Text(text = "-")
                }
                Text(
                    text = quantity.toString(),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                OutlinedButton(onClick = { quantity++ }) {
                    Text(text = "+")
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
                IconButton(onClick = { isFavorite = !isFavorite }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
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

            // Color Selection
            Text(text = "Colors:", fontWeight = FontWeight.Bold)
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(product.tags.size) { index ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp)
                            .background(
                                color = Color(product.tags[index].hashCode()),
                                shape = CircleShape
                            )
                            .clickable { selectedColor = Color(product.tags[index].hashCode()) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Size Selection
            Text(text = "Available Sizes:", fontWeight = FontWeight.Bold)
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(product.tags.size) { index ->
                    OutlinedButton(onClick = {
                        selectedSize = product.tags[index].toIntOrNull() ?: 0
                    }) {
                        Text(text = product.tags[index])
                    }
                }
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
