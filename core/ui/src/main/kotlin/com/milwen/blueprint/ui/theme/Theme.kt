package com.milwen.blueprint.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

@Composable
fun MainTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = remember(isDark) {
        if (isDark) {
            DarkThemeColors
        } else {
            LightThemeColors
        }
    }

    MaterialTheme(
      colorScheme = colors.toMaterialColors(),
    ) {
        CompositionLocalProvider(
            LocalColorScheme provides colors,
        ) {
            content()
        }
    }

}

object MainTheme {
    val colors: ApplicationColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current
}