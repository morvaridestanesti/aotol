package ir.ncis.preline.ui.components.drawer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme

data class DrawerTheme(
    val background: Color,
    val stroke: Color,
    val title: Color,
    val closeBackground: Color,
    val closeIcon: Color,
    val scrim: Color,
) {
    sealed interface Side {
        object Start : Side
        object End : Side
        object Top : Side
        object Bottom : Side
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(): DrawerTheme {
    val colors = PrelineTheme.colors
    return DrawerTheme(
        background = colors.layer,
        stroke = colors.layerBorder,
        title = colors.foreground,
        closeBackground = colors.surface,
        closeIcon = colors.mutedForeground1,
        scrim = Palette.Gray.Gray900.copy(alpha = 0.5f)
    )
}
