package ir.ncis.preline.ui.components.sidebar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme

data class SidebarTheme(
    val background: Color,
    val stroke: Color,
    val text: Color,
    val icon: Color,
    val selectedBackground: Color,
    val selectedText: Color,
    val guide: Color,
) {
    sealed interface Type {
        object Plain : Type
        object Gray : Type
        object Inverse : Type
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(type: SidebarTheme.Type): SidebarTheme {
    val colors = PrelineTheme.colors
    return when (type) {
        SidebarTheme.Type.Inverse -> SidebarTheme(
            background = Palette.Blue.Blue950,
            stroke = Palette.Transparent,
            text = Palette.White.copy(alpha = 0.8f),
            icon = Palette.White.copy(alpha = 0.8f),
            selectedBackground = Palette.White.copy(alpha = 0.1f),
            selectedText = Palette.White,
            guide = Palette.White.copy(alpha = 0.2f)
        )

        else                      -> SidebarTheme(
            background = if (type == SidebarTheme.Type.Gray) colors.muted else colors.layer,
            stroke = colors.layerBorder,
            text = colors.foreground,
            icon = colors.foreground,
            selectedBackground = colors.surface,
            selectedText = colors.foreground,
            guide = colors.layerBorder
        )
    }
}
