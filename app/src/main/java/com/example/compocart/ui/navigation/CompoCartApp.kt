package com.example.compocart.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compocart.ui.components.BottomNavigationBar
import com.example.compocart.ui.screens.CartScreen
import com.example.compocart.ui.screens.home.HomeScreen
import com.example.compocart.ui.screens.profile.ProfileScreen
import com.example.compocart.ui.screens.SettingsScreen
import com.example.compocart.ui.screens.categories.CategoryScreen
import com.example.compocart.ui.screens.home.HomeViewModel
import com.example.compocart.ui.screens.product.ProductDetailScreen
import com.example.compocart.ui.screens.LoginScreen
import com.example.compocart.ui.screens.profile.ProfileViewModel

@Composable
fun CompoCartApp() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route


    val showBottomBar = currentRoute != "login" && !(currentRoute?.startsWith("product/") ?: false)



//    var drawerState = remember { mutableStateOf(false) } // Yan menü açılıp kapanma durumu

//    ModalNavigationDrawer(
//        drawerState = if (drawerState.value) DrawerState.Open else DrawerState.Closed,
//        drawerContent = {
//            NavigationDrawerContent(onItemClick = { route ->
//                navController.navigate(route)
//                drawerState.value = false // Yan menüyü kapat
//            })
//        }
//    ) {
    Scaffold(
//            bottomBar = { BottomNavigationBar(navController) }
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") {
                LoginScreen(
                    onNavigateToHome = {
                        navController.navigate("home") {
                            popUpTo("login") {
                                inclusive = true
                            } // Geri tuşunda login ekranına dönülmemesi için ( Giriş ekranını stack'ten kaldırır)
                            launchSingleTop = true // "home" ekranını birden fazla kez yüklemez
                        }
                    }
                )
            }

            composable("home") {
                HomeScreen(
                    onCategoryClick = { category ->
                        navController.navigate("category/$category")
                    },
                    onProductClick = { product ->
                        navController.navigate("product/${product.id}")
                    }
                )
            }

            composable("categories") {
                CategoryScreen(onCategoryClick = { category ->
                    // Kategori tıklama olayını burada işleyin, örneğin:
                    println("Clicked on: ${category.name}")
                })
            }
            composable("cart") { CartScreen() }
            composable("settings") { SettingsScreen() }
            composable("profile") {
                val profileViewModel = hiltViewModel<ProfileViewModel>()
                ProfileScreen(viewModel = profileViewModel)
            }

            composable("product/{productId}") { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("home")
                }
                val homeViewModel: HomeViewModel = hiltViewModel(parentEntry)
                ProductDetailScreen(productId = productId, viewModel = homeViewModel)
            }
        }
    }
//    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCompoCartApp() {
    MaterialTheme {
        CompoCartApp()
    }
}