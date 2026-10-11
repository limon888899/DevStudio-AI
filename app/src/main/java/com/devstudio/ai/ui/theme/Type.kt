package com.devstudio.ai.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = PrimaryCyan,
    secondary = AccentBlue,
    background = DarkBackground,
    surface = SurfaceCard,
    onPrimary = BrightWhite,
    onSecondary = BrightWhite,
    onBackground = BrightWhite,
    onSurface = BrightWhite,
)

private val LightColorPalette = lightColorScheme(
    primary = PrimaryCyan,
    secondary = AccentBlue,
    background = DarkBackground,
    surface = SurfaceCard,
    onPrimary = BrightWhite,
    onSecondary = BrightWhite,
    onBackground = BrightWhite,
    onSurface = BrightWhite,
)

@Composable
fun DevStudioAITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
