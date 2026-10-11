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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devstudio.ai.ui.theme.BorderLine
import com.devstudio.ai.ui.theme.DarkBackground
import com.devstudio.ai.ui.theme.MutedText
import com.devstudio.ai.ui.theme.PrimaryCyan
import com.devstudio.ai.ui.theme.SurfaceCard

@Composable
fun AssistantScreen(navController: NavController) {
    var prompt by remember { mutableStateOf("Build an e-commerce dashboard with sales charts") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text("AI Developer Assistant", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)

            Card(
                colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("AI Code Output", color = Color.White, fontWeight = FontWeight.Bold)
                    Text(
                        text = "Generate an e-commerce admin dashboard with KPI tiles, sales chart, and product table layout.",
                        color = MutedText,
                        fontSize = 14.sp
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(onClick = { navController.navigate("editor") }, colors = ButtonDefaults.buttonColors(containerColor = PrimaryCyan)) {
                            Text("Apply to Code Editor")
                        }
                        Button(onClick = { navController.navigate("deployments") }, colors = ButtonDefaults.buttonColors(containerColor = SurfaceCard)) {
                            Text("Deploy Now")
                        }
                    }
                }
            }

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

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { }, colors = ButtonDefaults.buttonColors(containerColor = SurfaceCard)) {
                    Icon(Icons.Default.Mic, contentDescription = null)
                    Text("Voice")
                }
                Button(onClick = { }, colors = ButtonDefaults.buttonColors(containerColor = PrimaryCyan)) {
                    Icon(Icons.Default.Send, contentDescription = null)
                    Text("Generate")
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Debugger", color = Color.White, fontWeight = FontWeight.Bold)
                    Text("Error detected in API response mapping", color = MutedText)
                    Text("Fix suggestion: resolve payload mismatch in orders schema", color = PrimaryCyan)
                }
            }
        }
    }
}
