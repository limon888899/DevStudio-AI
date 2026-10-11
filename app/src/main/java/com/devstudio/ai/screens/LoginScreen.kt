package com.devstudio.ai.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devstudio.ai.AuthState
import com.devstudio.ai.AuthViewModel
import com.devstudio.ai.ui.theme.DarkBackground
import com.devstudio.ai.ui.theme.MutedText
import com.devstudio.ai.ui.theme.PrimaryCyan

@Composable
fun SplashScreen(navController: NavController, authViewModel: AuthViewModel) {
    val authState by authViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> navController.navigate("dashboard") {
                popUpTo("splash") { inclusive = true }
            }
            is AuthState.Unauthenticated -> navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
            else -> Unit
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = android.R.drawable.star_big_on),
                contentDescription = "App Logo",
                modifier = Modifier.size(96.dp)
            )
            Text(
                text = "DevStudio AI",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Code, Deploy & Build Anywhere",
                style = MaterialTheme.typography.bodyLarge,
                color = MutedText
            )
            CircularProgressIndicator(
                color = PrimaryCyan,
                modifier = Modifier.padding(top = 24.dp)
            )
        }
    }
}
