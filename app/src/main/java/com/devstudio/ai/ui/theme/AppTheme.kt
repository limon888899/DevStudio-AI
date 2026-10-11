package com.devstudio.ai.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DevStudioDarkColors = darkColorScheme(
    primary = PrimaryCyan,
    onPrimary = DarkBackground,
    background = DarkBackground,
    onBackground = BrightWhite,
    surface = SurfaceCard,
    onSurface = BrightWhite,
    error = DangerRed
)

@Composable
fun DevStudioAITheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DevStudioDarkColors,
        typography = Typography,
        content = content
    )
}
