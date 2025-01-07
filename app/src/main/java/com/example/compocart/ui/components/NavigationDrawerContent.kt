package com.example.compocart.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun NavigationDrawerContent(onItemClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "CompoCart",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Menü öğeleri
        DrawerMenuItem(
            label = "Home",
            icon = Icons.Filled.Home,
            onClick = { onItemClick("home") }
        )
        DrawerMenuItem(
            label = "Cart",
            icon = Icons.Filled.ShoppingCart,
            onClick = { onItemClick("cart") }
        )
        DrawerMenuItem(
            label = "Profile",
            icon = Icons.Filled.Person,
            onClick = { onItemClick("profile") }
        )
        DrawerMenuItem(
            label = "Settings",
            icon = Icons.Filled.Settings,
            onClick = { onItemClick("settings") }
        )
    }
}

@Composable
fun DrawerMenuItem(label: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = label, modifier = Modifier.padding(end = 16.dp))
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
    }
}