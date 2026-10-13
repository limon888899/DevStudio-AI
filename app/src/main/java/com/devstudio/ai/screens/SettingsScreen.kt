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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun AdminHubScreen(navController: NavController) {
    val uriHandler = LocalUriHandler.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Unified Admin Hub", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { uriHandler.openUri("https://console.firebase.google.com") }, colors = ButtonDefaults.buttonColors(containerColor = PrimaryCyan)) {
                    Text("Firebase")
                }
                Button(onClick = { uriHandler.openUri("https://supabase.com/dashboard") }, colors = ButtonDefaults.buttonColors(containerColor = SurfaceCard)) {
                    Text("Supabase")
                }
                Button(onClick = { uriHandler.openUri("https://strapi.io") }, colors = ButtonDefaults.buttonColors(containerColor = SurfaceCard)) {
                    Text("Strapi")
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth().height(420.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Connected Backends", color = Color.White, fontWeight = FontWeight.Bold)
                    Text("Firebase Console", color = MutedText)
                    Text("Supabase Dashboard", color = MutedText)
                    Text("Strapi CMS", color = MutedText)
                    Text("Custom Admin URL", color = MutedText)
                    Text("Tap a button above to open the dashboard", color = PrimaryCyan, fontSize = 14.sp)
                }
            }
        }
    }
}
