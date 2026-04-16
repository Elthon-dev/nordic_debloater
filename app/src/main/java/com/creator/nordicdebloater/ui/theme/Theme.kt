package com.creator.nordicdebloater.ui.theme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = FrostBlue,
    background = PolarNight,
    surface = PolarNight,
    onBackground = SnowStorm,
    onSurface = SnowStorm
)

@Composable
fun NordicTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = DarkColorScheme, content = content)
}
