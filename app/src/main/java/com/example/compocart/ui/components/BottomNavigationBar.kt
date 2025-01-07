package com.example.compocart.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.compocart.ui.navigation.BottomNavItem

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        tonalElevation = 8.dp,
        containerColor = Color.White
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination?.route

        BottomNavItem.values().forEach { item ->
            if (item.route == "cart") {
                // Cart Item with special styling
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .size(56.dp),
                    contentAlignment = Alignment.Center
                ) {
                    FloatingActionButton(
                        onClick = { navController.navigate(item.route) },
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        BadgeBox(badgeContent = {
                            Text("6", color = Color.White, style = MaterialTheme.typography.labelSmall)
                        }) {
                            Icon(item.icon, contentDescription = item.label, tint = Color.White)
                        }
                    }
                }
            } else {
                // Regular navigation items
                NavigationBarItem(
                    selected = currentDestination == item.route,
                    onClick = { navController.navigate(item.route) },
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(item.label) }
                )
            }
        }
    }
}

@Composable
fun BadgeBox(
    badgeContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopEnd
    ) {
        content()
        Box(
            modifier = Modifier
                .padding(top = 4.dp, end = 4.dp)
                .size(16.dp),
            contentAlignment = Alignment.Center
        ) {
            badgeContent()
        }
    }
}