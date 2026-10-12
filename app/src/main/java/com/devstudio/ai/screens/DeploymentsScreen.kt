package com.devstudio.ai.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devstudio.ai.data.ai.AiClient
import com.devstudio.ai.data.ai.AiKeyStore
import com.devstudio.ai.ui.theme.BorderLine
import com.devstudio.ai.ui.theme.DangerRed
import com.devstudio.ai.ui.theme.DarkBackground
import com.devstudio.ai.ui.theme.MutedText
import com.devstudio.ai.ui.theme.PrimaryCyan
import com.devstudio.ai.ui.theme.SurfaceCard
import kotlinx.coroutines.launch

@Composable
fun AssistantScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var prompt by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

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
            Text("AI Developer Assistant", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)

            OutlinedTextField(
                value = prompt,
                onValueChange = { prompt = it },
                modifier = Modifier.fillMaxWidth().height(120.dp),
                label = { Text("Prompt") },
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

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {
                        prompt = ""
                        output = ""
                        error = ""
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = SurfaceCard)
                ) {
                    Text("Clear")
                }
                Button(
                    onClick = {
                        scope.launch {
                            loading = true
                            error = ""
                            try {
                                val key = AiKeyStore.load(context)
                                if (key.isBlank()) {
                                    error = "Please save your API key first (Key button)."
                                } else {
                                    output = AiClient.ask(key, prompt)
                                }
                            } catch (e: Exception) {
                                error = e.message ?: "Unknown error"
                            }
                            loading = false
                        }
                    },
                    enabled = !loading && prompt.isNotBlank(),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryCyan)
                ) {
                    Icon(Icons.Default.Send, contentDescription = null)
                    Text(if (loading) "Thinking..." else "Generate")
                }
                Button(
                    onClick = { navController.navigate("settings") },
                    colors = ButtonDefaults.buttonColors(containerColor = SurfaceCard)
                ) {
                    Text("Key")
                }
            }

            if (loading) {
                CircularProgressIndicator(color = PrimaryCyan)
            }

            if (error.isNotEmpty()) {
                Text(error, color = DangerRed)
            }

            if (output.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("AI Output", color = Color.White, fontWeight = FontWeight.Bold)
                        Text(output, color = Color.White, fontSize = 14.sp)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(
                                onClick = { navController.navigate("editor") },
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryCyan)
                            ) {
                                Text("Code Editor")
                            }
                            Button(
                                onClick = { navController.navigate("deployments") },
                                colors = ButtonDefaults.buttonColors(containerColor = SurfaceCard)
                            ) {
                                Text("Deploy")
                            }
                        }
                    }
                }
            }
        }
    }
}
