package ir.ncis.preline.ui.components.tree_view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import ir.ncis.preline.ui.theme.PrelineTheme

data class TreeViewTheme(
    val label: Color,
    val disabledLabel: Color,
    val guide: Color,
    val selectedBackground: Color,
)

@Composable
@ReadOnlyComposable
internal fun getTheme(): TreeViewTheme {
    val colors = PrelineTheme.colors
    return TreeViewTheme(
        label = colors.foreground,
        disabledLabel = colors.mutedForeground,
        guide = colors.layerBorder,
        selectedBackground = colors.surface
    )
}
