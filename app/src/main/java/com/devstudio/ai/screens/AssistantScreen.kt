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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devstudio.ai.ui.theme.DarkBackground
import com.devstudio.ai.ui.theme.MutedText
import com.devstudio.ai.ui.theme.PrimaryCyan
import com.devstudio.ai.ui.theme.SurfaceCard

@Composable
fun EditorScreen(navController: NavController) {
    var previewMode by remember { mutableStateOf(false) }

    val sampleCode = """
        const App = () => {
          return (
            <div className="app-shell">
              <h1>DevStudio AI</h1>
              <p>Build, deploy and ship faster.</p>
            </div>
          );
        };
    """.trimIndent()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Code Workspace", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                OutlinedButton(onClick = { previewMode = !previewMode }) {
                    Icon(Icons.Default.Preview, contentDescription = null)
                    Text(if (previewMode) "Editor" else "Live Preview")
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { navController.navigate("assistant") }, colors = ButtonDefaults.buttonColors(containerColor = PrimaryCyan)) {
                    Text("AI Assist")
                }
                Button(onClick = { navController.navigate("deployments") }, colors = ButtonDefaults.buttonColors(containerColor = SurfaceCard)) {
                    Text("Deploy")
                }
            }

            if (previewMode) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(520.dp)
                        .background(SurfaceCard)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Code, contentDescription = null, tint = PrimaryCyan)
                        Text("Live Preview", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Text("Embedded WebView with hot reload", color = MutedText)
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(SurfaceCard)
                        .padding(12.dp)
                ) {
                    Text(
                        text = sampleCode,
                        color = Color.White,
                        modifier = Modifier.verticalScroll(rememberScrollState()),
                        fontSize = 14.sp,
                        lineHeight = 22.sp
                    )
                }
            }
        }
    }
}
