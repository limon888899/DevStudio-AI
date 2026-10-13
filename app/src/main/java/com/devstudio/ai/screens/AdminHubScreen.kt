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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devstudio.ai.ui.theme.DarkBackground
import com.devstudio.ai.ui.theme.MutedText
import com.devstudio.ai.ui.theme.PrimaryCyan
import com.devstudio.ai.ui.theme.SurfaceCard

@Composable
fun DeploymentsScreen(navController: NavController) {
    val uriHandler = LocalUriHandler.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Deployments & APK Builder", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { uriHandler.openUri("https://vercel.com/dashboard") }, colors = ButtonDefaults.buttonColors(containerColor = PrimaryCyan)) {
                    Text("Vercel")
                }
                Button(onClick = { uriHandler.openUri("https://github.com/limon888899/DevStudio-AI/actions") }, colors = ButtonDefaults.buttonColors(containerColor = SurfaceCard)) {
                    Text("APK Builder")
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("studio-app.vercel.app", color = Color.White, fontWeight = FontWeight.Bold)
                    Text("Custom domain: app.devstudio.ai", color = MutedText)
                    Text("Realtime build logs", color = MutedText)
                    LinearProgressIndicator(
                        progress = { 0.74f },
                        modifier = Modifier.fillMaxWidth().height(8.dp),
                        color = PrimaryCyan
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Native APK Build", color = Color.White, fontWeight = FontWeight.Bold)
                    Text("Compiling Kotlin sources", color = MutedText)
                    Text("Running Gradle tasks", color = MutedText)
                    Text("Signing APK", color = MutedText)
                    Button(
                        onClick = { uriHandler.openUri("https://github.com/limon888899/DevStudio-AI/actions") },
                        modifier = Modifier.align(Alignment.End),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryCyan)
                    ) {
                        Text("Download APK")
                    }
                }
            }
        }
    }
}
