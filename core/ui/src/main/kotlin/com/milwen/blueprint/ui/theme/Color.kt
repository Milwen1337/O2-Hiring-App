package com.milwen.blueprint.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Transparent = Color(0x00000000)
val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)

val Grey1 = Color(0xFF141414)
val Grey2 = Color(0xFF242424)
val Grey3 = Color(0xFF333333)
val Grey4 = Color(0xFF535353)
val Grey5 = Color(0xFF929292)
val Grey6 = Color(0xFFB8B8B8)
val Grey7 = Color(0xFFDADADA)

val Red1 = Color(0xFFD50A0A)
val Red2 = Color(0xFFD35C5C)
val Green1 = Color(0xFF89FF00)
val Green2 = Color(0xFF98DB60)
val Blue1 = Color(0xFF2140F1)
val Blue2 = Color(0xFF6375DB)

@Immutable
data class MainColors(
    val transparent: Color = Transparent,
    val error: Color = Red1,
)

@Immutable
data class SurfaceColors(
    val primary: Color = Grey7,
    val secondary: Color = White,
    val tertiary: Color = Grey6,
    val inverse: Color = Grey1,
    val inverseSecondary: Color = Grey2,
    val inverseTertiary: Color = Grey3,
    val disabled: Color = Grey4,
    val primarySemiTransparent: Color = Grey7.copy(alpha = 0.6f),
    val inverseSemiTransparent: Color = Grey1.copy(alpha = 0.6f),
)

@Immutable
data class OnSurfaceColors(
    val primary: Color = Grey1,
    val secondary: Color = Grey5,
    val tertiary: Color = Grey4,
    val inverse: Color = White,
    val disabled: Color = Grey4,
)

@Immutable
data class BrandColors(
    val green: Color = Green1,
    val blue: Color = Blue1,
    val red: Color = Red1,
)

@Immutable
data class ApplicationColors(
    val main: MainColors = MainColors(),
    val surface: SurfaceColors = SurfaceColors(),
    val onSurface: OnSurfaceColors = OnSurfaceColors(),
    val brand: BrandColors = BrandColors(),
    val lightMode: Boolean,
)

internal val LightThemeColors = ApplicationColors(lightMode = true)
internal val DarkThemeColors = ApplicationColors(
    surface = SurfaceColors(
        primary = Grey1,
        secondary = Black,
        tertiary = Grey2,
        inverse = Grey7,
        inverseSecondary = Grey6,
        disabled = Grey3,
        primarySemiTransparent = Grey1.copy(alpha = 0.6f),
        inverseSemiTransparent = Grey7.copy(alpha = 0.6f),
    ),
    onSurface = OnSurfaceColors(
        primary = White,
        secondary = Grey4,
        tertiary = Grey5,
        inverse = Grey1,
        disabled = Grey3,
    ),
    brand = BrandColors(
        green = Green2,
        blue = Blue2,
        red = Red2,
    ),
    lightMode = false,
)

internal fun ApplicationColors.toMaterialColors(): ColorScheme = ColorScheme(
    primary = brand.blue,
    onPrimary = onSurface.primary,
    primaryContainer = brand.blue,
    onPrimaryContainer = onSurface.primary,
    inversePrimary = brand.blue,
    secondary = brand.green,
    onSecondary = onSurface.primary,
    secondaryContainer = brand.red,
    onSecondaryContainer = onSurface.primary,
    tertiary = surface.primary,
    onTertiary = onSurface.primary,
    tertiaryContainer = onSurface.tertiary,
    onTertiaryContainer = onSurface.primary,
    background = surface.primary,
    onBackground = onSurface.primary,
    surface = surface.primary,
    onSurface = onSurface.primary,
    surfaceVariant = surface.secondary,
    onSurfaceVariant = onSurface.secondary,
    surfaceTint = surface.primary,
    inverseSurface = surface.inverse,
    inverseOnSurface = onSurface.inverse,
    error = main.error,
    onError = onSurface.inverse,
    errorContainer = main.error,
    onErrorContainer = onSurface.inverse,
    outline = onSurface.secondary,
    outlineVariant = onSurface.tertiary,
    scrim = onSurface.primary,
)

internal val LocalColorScheme = staticCompositionLocalOf { LightThemeColors }

