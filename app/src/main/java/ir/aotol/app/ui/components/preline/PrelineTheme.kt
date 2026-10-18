package ir.aotol.app.ui.components.preline

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalPrelineColors = staticCompositionLocalOf { LightPrelineColors }
private val LocalPrelineTypography = staticCompositionLocalOf { DefaultPrelineTypography }
private val LocalPrelineShapes = staticCompositionLocalOf { PrelineShapes() }

@Composable
fun PrelineTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: PrelineColors = if (darkTheme) DarkPrelineColors else LightPrelineColors,
    typography: PrelineTypography = DefaultPrelineTypography,
    shapes: PrelineShapes = PrelineShapes(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalPrelineColors provides colors,
        LocalPrelineTypography provides typography,
        LocalPrelineShapes provides shapes,
    ) {
        MaterialTheme(
            colorScheme = colors.toColorScheme(),
            typography = typography.toMaterialTypography(),
            content = content
        )
    }
}

object PrelineTheme {

    val colors: PrelineColors
        @Composable
        @ReadOnlyComposable
        get() = LocalPrelineColors.current

    val typography: PrelineTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalPrelineTypography.current

    val shapes: PrelineShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalPrelineShapes.current
}

private fun PrelineColors.toColorScheme(): ColorScheme {
    val base = if (isDark) darkColorScheme() else lightColorScheme()
    return base.copy(
        primary = primary,
        onPrimary = primaryForeground,
        secondary = secondary,
        onSecondary = secondaryForeground,
        background = background,
        onBackground = foreground,
        surface = layer,
        onSurface = layerForeground,
        surfaceVariant = surface,
        onSurfaceVariant = surfaceForeground,
        outline = layerBorder,
        outlineVariant = border,
        error = destructive,
    )
}
