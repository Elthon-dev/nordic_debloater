package com.creator.nordicdebloater.ui.theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val NordicColorScheme = darkColorScheme(
    primary = FrostBlue,
    background = PolarNight,
    surface = PolarNight,
    onBackground = SnowStorm,
    onSurface = SnowStorm
)

@Composable
fun NordicTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = NordicColorScheme, content = content)
}
