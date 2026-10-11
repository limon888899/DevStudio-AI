package com.devstudio.ai

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devstudio.ai.screens.AdminHubScreen
import com.devstudio.ai.screens.AssistantScreen
import com.devstudio.ai.screens.DashboardScreen
import com.devstudio.ai.screens.DeploymentsScreen
import com.devstudio.ai.screens.EditorScreen
import com.devstudio.ai.screens.LoginScreen
import com.devstudio.ai.screens.SettingsScreen
import com.devstudio.ai.screens.SplashScreen

@Composable
fun AppNavigation(authViewModel: AuthViewModel) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("login") {
            LoginScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("dashboard") {
            DashboardScreen(navController = navController)
        }
        composable("editor") {
            EditorScreen(navController = navController)
        }
        composable("assistant") {
            AssistantScreen(navController = navController)
        }
        composable("deployments") {
            DeploymentsScreen(navController = navController)
        }
        composable("admin") {
            AdminHubScreen(navController = navController)
        }
        composable("settings") {
            SettingsScreen(navController = navController, authViewModel = authViewModel)
        }
    }
}
