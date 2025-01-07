package com.example.compocart.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart

enum class BottomNavItem(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Home("home", "Home", Icons.Filled.Home),
    Categories("categories", "Categories", Icons.Filled.Menu),
    Cart("cart", "Cart", Icons.Filled.ShoppingCart),
    Settings("settings", "Settings", Icons.Filled.Settings),
    Profile("profile", "Profile", Icons.Filled.Person)
}