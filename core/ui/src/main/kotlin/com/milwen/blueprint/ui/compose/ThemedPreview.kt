package com.milwen.blueprint.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.milwen.blueprint.ui.theme.MainTheme

@Composable
inline fun ThemedPreview(
    isDark: Boolean = true,
    crossinline content: @Composable () -> Unit,
) = MainTheme(
    isDark = isDark
) {
  CompositionLocalProvider(
      value = LocalContentColor provides MainTheme.colors.onSurface.primary
  ) {
      Box(
        modifier = Modifier.wrapContentSize().background(if (isDark) Color.DarkGray else Color.LightGray)
      ) {
          content()
      }
  }
}