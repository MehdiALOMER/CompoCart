package com.example.compocart.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compocart.R

@Composable
fun LoginScreen(
    onLogin: (String, String) -> Unit, // Giriş butonuna basıldığında username ve password'u döner
    onForgotPassword: () -> Unit,
    onSignUp: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo (Temsili Icon Kullanıldı)
        Icon(
            imageVector = Icons.Default.Person, // Material Icons'dan bir icon
            contentDescription = "App Logo",
            tint = Color(0xFF6200EE),
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Başlık
        Text(
            text = "Welcome Back!",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Kullanıcı Adı
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
//            colors = TextFieldDefaults.textFieldColors(
//                containerColor = Color(0xFFF7F7F7),
//                focusedIndicatorColor = Color(0xFF6200EE)
//            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person, // Temsili bir kullanıcı iconu
                    contentDescription = "Username Icon",
                    tint = Color.Gray
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Şifre
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//            colors = TextFieldDefaults.textFieldColors(
//                containerColor = Color(0xFFF7F7F7),
//                focusedIndicatorColor = Color(0xFF6200EE)
//            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock, // Material Icons'dan temsili bir şifre iconu
                    contentDescription = "Password Icon",
                    tint = Color.Gray
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Default.Lock else Icons.Default.Close,
                        contentDescription = if (passwordVisibility) "Hide Password" else "Show Password",
                        tint = Color.Gray
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Login Butonu
        Button(
            onClick = { onLogin(username, password) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
        ) {
            Text(text = "Login", color = Color.White, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Unutulan Şifre ve Kayıt Ol
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = { onForgotPassword() }) {
                Text(text = "Forgot Password?", color = Color(0xFF6200EE))
            }
            TextButton(onClick = { onSignUp() }) {
                Text(text = "Sign Up", color = Color(0xFF6200EE))
            }
        }
    }
}
