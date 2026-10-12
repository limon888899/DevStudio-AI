package com.devstudio.ai.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devstudio.ai.AuthViewModel
import com.devstudio.ai.data.ai.AiKeyStore
import com.devstudio.ai.ui.theme.BorderLine
import com.devstudio.ai.ui.theme.DangerRed
import com.devstudio.ai.ui.theme.DarkBackground
import com.devstudio.ai.ui.theme.MutedText
import com.devstudio.ai.ui.theme.PrimaryCyan
import com.devstudio.ai.ui.theme.SuccessGreen
import com.devstudio.ai.ui.theme.SurfaceCard
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(navController: NavController, authViewModel: AuthViewModel) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var apiKey by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        apiKey = AiKeyStore.load(context)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Settings & Vault", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("AI API Key (Anthropic)", color = Color.White, fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = apiKey,
                        onValueChange = { apiKey = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        label = { Text("API key") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryCyan,
                            unfocusedBorderColor = BorderLine,
                            focusedLabelColor = PrimaryCyan,
                            cursorColor = PrimaryCyan,
                            unfocusedLabelColor = MutedText,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                        )
                    )
                    Button(
                        onClick = {
                            scope.launch {
                                AiKeyStore.save(context, apiKey)
                                status = "Saved"
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryCyan)
                    ) {
                        Text("Save key")
                    }
                    if (status.isNotEmpty()) {
                        Text(status, color = SuccessGreen)
                    }
                    Text("The key is stored only on this device", color = MutedText, fontSize = 13.sp)
                }
            }

            Button(
                onClick = {
                    scope.launch { authViewModel.logout() }
                    navController.navigate("login") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = DangerRed)
            ) {
                Text("Log out")
            }
        }
    }
}
