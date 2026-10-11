package com.devstudio.ai.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devstudio.ai.ui.theme.DarkBackground
import com.devstudio.ai.ui.theme.MutedText
import com.devstudio.ai.ui.theme.PrimaryCyan
import com.devstudio.ai.ui.theme.SurfaceCard
import com.devstudio.ai.ui.theme.SuccessGreen

@Composable
fun DashboardScreen(navController: NavController) {
    val metrics = listOf(
        "Active Projects" to "12",
        "Recent Deploys" to "8 Live",
        "AI Tokens" to "48.2K"
    )

    val features = listOf(
        "AI App Generator" to Icons.Default.Add,
        "Code Editor" to Icons.Default.Code,
        "APK Builder" to Icons.Default.Build,
        "GitHub Manager" to Icons.Default.Storage,
        "Vercel Deploy" to Icons.Default.Bolt,
        "Admin Hub" to Icons.Default.Settings
    )

    val projects = listOf(
        "mobile-commerce" to "Live",
        "api-console" to "Building",
        "agent-dashboard" to "Draft",
        "studio-admin" to "Live",
        "design-system" to "Building"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 82.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(PrimaryCyan),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("DS", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Column {
                        Text("Workspace", color = MutedText, fontSize = 12.sp)
                        Text("DevStudio Team", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)
                    }
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(Icons.Default.Settings, contentDescription = null, tint = Color.White)
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Overview", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Icon(Icons.Default.SwapHoriz, contentDescription = null, tint = MutedText)
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                metrics.forEach { (label, value) ->
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(label, color = MutedText, fontSize = 12.sp)
                            Text(value, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Text("Quick Access", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.weight(1f)) {
                    features.take(3).forEach { (title, icon) ->
                        FeatureTile(title, icon) {
                            when (title) {
                                "AI App Generator" -> navController.navigate("assistant")
                                "Code Editor" -> navController.navigate("editor")
                                "APK Builder" -> navController.navigate("deployments")
                            }
                        }
                    }
                }
                Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.weight(1f)) {
                    features.drop(3).forEach { (title, icon) ->
                        FeatureTile(title, icon) {
                            when (title) {
                                "GitHub Manager" -> navController.navigate("editor")
                                "Vercel Deploy" -> navController.navigate("deployments")
                                "Admin Hub" -> navController.navigate("admin")
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Recent Projects", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = MutedText)
            }

            projects.forEach { (name, status) ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, contentDescription = null, tint = PrimaryCyan)
                            Spacer(modifier = Modifier.size(10.dp))
                            Column {
                                Text(name, color = Color.White, fontWeight = FontWeight.Medium)
                                Text("Updated 2 hours ago", color = MutedText, fontSize = 12.sp)
                            }
                        }
                        Box(
                            modifier = Modifier
                                .background(
                                    when (status) {
                                        "Live" -> SuccessGreen.copy(alpha = 0.2f)
                                        "Building" -> PrimaryCyan.copy(alpha = 0.2f)
                                        else -> MutedText.copy(alpha = 0.2f)
                                    },
                                    RoundedCornerShape(20.dp)
                                )
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(
                                status,
                                color = when (status) {
                                    "Live" -> SuccessGreen
                                    "Building" -> PrimaryCyan
                                    else -> MutedText
                                },
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        BottomNavigationBar(navController = navController)
    }
}

@Composable
private fun FeatureTile(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        shape = RoundedCornerShape(16.dp)
    ) {
        androidx.compose.foundation.clickable { onClick() }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(PrimaryCyan.copy(alpha = 0.15f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = PrimaryCyan)
            }
            Spacer(modifier = Modifier.size(12.dp))
            Text(title, color = Color.White, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        NavigationBar(containerColor = SurfaceCard) {
            val items = listOf(
                "editor" to "Editor",
                "assistant" to "AI",
                "deployments" to "Deploy",
                "admin" to "Admin",
                "settings" to "Vault"
            )
            items.forEach { (route, label) ->
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(route) },
                    icon = {
                        Icon(
                            imageVector = when (route) {
                                "editor" -> Icons.Default.Code
                                "assistant" -> Icons.Default.Add
                                "deployments" -> Icons.Default.Bolt
                                "admin" -> Icons.Default.Storage
                                else -> Icons.Default.Settings
                            },
                            contentDescription = label
                        )
                    },
                    label = { Text(label) }
                )
            }
        }
    }
}
