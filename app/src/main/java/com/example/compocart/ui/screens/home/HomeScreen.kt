package com.example.compocart.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.compocart.data.model.Product

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onCategoryClick: (String) -> Unit,
    onProductClick: (Product) -> Unit
) {
    val categories = viewModel.categories.collectAsState().value
    val products = viewModel.products.collectAsState().value
    val searchQuery = viewModel.searchQuery.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Arama Kutusu
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.searchProducts(it) },
            label = { Text("Search Products") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Üstte Kategoriler
        Text(
            text = "Trending Products",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(8.dp),
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            items(categories.size) { index ->
//                OutlinedButton(onClick = { onCategoryClick(categories[index]) }) {
//                    Text(text = categories[index], fontSize = 16.sp)
//                }
//            }
//        }

        Spacer(modifier = Modifier.height(16.dp))

        // Ürünler Izgarası
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(products.size) { index ->
                ProductCard(product = products[index], onProductClick = onProductClick)
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onProductClick: (Product) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onProductClick(product) },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            // Ürün Görseli
            AsyncImage(
                model = product.images?.firstOrNull(),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Ürün Bilgileri
            Text(
                text = product.title,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Price: \$${product.price}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Green
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (product.stock > 0) "In Stock" else "Out of Stock",
                color = if (product.stock > 0) Color.Green else Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}