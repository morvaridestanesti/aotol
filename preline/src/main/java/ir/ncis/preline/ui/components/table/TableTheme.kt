package ir.ncis.preline.ui.components.table

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme

data class TableTheme(
    val background: Color,
    val stroke: Color,
    val divider: Color,
    val stripe: Color,
    val headerBackground: Color,
    val headerForeground: Color,
    val foreground: Color,
    val shape: Shape,
) {
    sealed interface Border {
        object None : Border
        object Square : Border
        object Rounded : Border
    }

    sealed interface Highlight {
        object Blue : Highlight
        object Yellow : Highlight
        object Red : Highlight
        object Gray : Highlight
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(border: TableTheme.Border, headerBackground: Boolean): TableTheme {
    val colors = PrelineTheme.colors
    return TableTheme(
        background = if (border == TableTheme.Border.None) Palette.Transparent else colors.layer,
        stroke = if (border == TableTheme.Border.None) Palette.Transparent else colors.layerBorder,
        divider = colors.layerBorder,
        stripe = if (colors.isDark) colors.surface.copy(alpha = 0.5f) else colors.surface,
        headerBackground = if (headerBackground) colors.layerHover else Palette.Transparent,
        headerForeground = colors.mutedForeground1,
        foreground = colors.foreground,
        shape = if (border == TableTheme.Border.Rounded) RoundedCornerShape(8.dp) else RectangleShape
    )
}

@Composable
@ReadOnlyComposable
internal fun getHighlightColor(highlight: TableTheme.Highlight): Color = with(PrelineTheme.colors) {
    when (highlight) {
        TableTheme.Highlight.Blue   -> blue.soft
        TableTheme.Highlight.Yellow -> yellow.soft
        TableTheme.Highlight.Red    -> red.soft
        TableTheme.Highlight.Gray   -> surface
    }
}
