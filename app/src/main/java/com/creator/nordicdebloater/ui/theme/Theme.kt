package com.creator.nordicdebloater.ui.theme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun NordicTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = darkColorScheme(primary = FrostBlue, background = PolarNight, surface = PolarNight, onBackground = SnowStorm), content = content)
}
