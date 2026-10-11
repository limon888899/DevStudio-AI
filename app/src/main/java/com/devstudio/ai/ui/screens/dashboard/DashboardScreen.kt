package com.devstudio.ai.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devstudio.ai.ui.viewmodel.DashboardViewModel
import com.devstudio.ai.ui.viewmodel.ProjectState
import com.devstudio.ai.ui.theme.DarkBackground
import com.devstudio.ai.ui.theme.MutedText
import com.devstudio.ai.ui.theme.PrimaryCyan
import com.devstudio.ai.ui.theme.SurfaceCard
import com.devstudio.ai.ui.theme.SuccessGreen

@Composable
fun DashboardScreen(navController: NavController, dashboardViewModel: DashboardViewModel) {
    val projectState by dashboardViewModel.projectState.collectAsState()
    val activeProjects by dashboardViewModel.activeProjects.collectAsState()
    val deployments by dashboardViewModel.deployments.collectAsState()
    val aiTokens by dashboardViewModel.aiTokens.collectAsState()

    val metrics = listOf(
        "Active Projects" to activeProjects.toString(),
        "Recent Deploys" to deployments,
        "AI Tokens" to aiTokens
    )

    val features = listOf(
        "AI App Generator" to Icons.Default.Add,
        "Code Editor" to Icons.Default.Code,
        "APK Builder" to Icons.Default.Build,
        "GitHub Manager" to Icons.Default.Storage,
        "Vercel Deploy" to Icons.Default.Bolt,
        "Admin Hub" to Icons.Default.Settings
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 82.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
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
            }

            item {
                Text("Overview", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            }

            item {
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
            }

            item {
                Text("Quick Access", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            item {
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
            }

            item {
                Text("Recent Projects", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            when (projectState) {
                is ProjectState.Loading -> {
                    item {
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(color = PrimaryCyan)
                        }
                    }
                }
                is ProjectState.Success -> {
                    items((projectState as ProjectState.Success).projects) { project ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { navController.navigate("editor") },
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
                                        Text(project.name, color = Color.White, fontWeight = FontWeight.Medium)
                                        Text("Updated 2 hours ago", color = MutedText, fontSize = 12.sp)
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .background(
                                            when (project.status) {
                                                "Live" -> SuccessGreen.copy(alpha = 0.2f)
                                                "Building" -> PrimaryCyan.copy(alpha = 0.2f)
                                                else -> MutedText.copy(alpha = 0.2f)
                                            },
                                            RoundedCornerShape(20.dp)
                                        )
                                        .padding(horizontal = 10.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        project.status,
                                        color = when (project.status) {
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
                is ProjectState.Error -> {
                    item {
                        Text((projectState as ProjectState.Error).message, color = Color.Red)
                    }
                }
            }
        }

        BottomNavigationBar(navController = navController, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun FeatureTile(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        shape = RoundedCornerShape(16.dp)
    ) {
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
            Text(title, color = Color.White, fontWeight = FontWeight.Medium, fontSize = 13.sp)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        NavigationBar(containerColor = SurfaceCard, modifier = Modifier.fillMaxWidth()) {
            val items = listOf(
                "editor" to "Editor" to Icons.Default.Code,
                "assistant" to "AI" to Icons.Default.Add,
                "deployments" to "Deploy" to Icons.Default.Bolt,
                "admin" to "Admin" to Icons.Default.Storage,
                "settings" to "Vault" to Icons.Default.Settings
            )
            items.forEach { (route, label, icon) ->
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(route) },
                    icon = { Icon(imageVector = icon, contentDescription = label) },
                    label = { Text(label, fontSize = 11.sp) }
                )
            }
        }
    }
}
