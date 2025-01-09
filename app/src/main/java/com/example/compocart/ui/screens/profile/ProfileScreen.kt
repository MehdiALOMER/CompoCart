package com.example.compocart.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProfileScreen(viewModel: ProfileViewModel, modifier: Modifier = Modifier) {

    LaunchedEffect(Unit) {
        viewModel.loadUserProfile()
    }

    val userProfile = viewModel.userProfile.collectAsState().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header Bölümü
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Profil",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Profil Resmi ve İsim
        userProfile?.let { profile ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileImage(imageUrl = profile.image)

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${profile.firstName} ${profile.lastName}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = profile.email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        } ?: run {
            // Yükleme göstergesi
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Hakkımda Bölümü (Statik)
        AboutSection()

        Spacer(modifier = Modifier.height(16.dp))

        // İstatistik Bölümü (Statik)
        StatsSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Butonlar
        ActionButtons()
    }
}

@Composable
fun AboutSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Hakkımda",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Bu alan, kullanıcının kendisi hakkında bilgi verebileceği bir alan olabilir. Statik içerik ya da dinamik bir biyografi buraya eklenebilir.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun StatsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatItem("Gönderiler", "42")
        StatItem("Takipçiler", "1200")
        StatItem("Takip Edilen", "180")
    }
}

@Composable
fun StatItem(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun ActionButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { /* TODO: Add Edit Profile functionality */ }) {
            Text("Profili Düzenle")
        }

        Button(onClick = { /* TODO: Add Logout functionality */ }) {
            Text("Çıkış Yap")
        }
    }
}

@Composable
fun ProfileImage(imageUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Profile Image",
        modifier = modifier
            .size(120.dp)
            .clip(CircleShape)
            .border(4.dp, MaterialTheme.colorScheme.primary, CircleShape),
        contentScale = ContentScale.Crop
    )
}