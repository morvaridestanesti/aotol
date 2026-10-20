package ir.ncis.preline.ui.components.legend

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color as ComposeColor
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme

data class LegendTheme(
    val indicatorColor: ComposeColor,
    val labelColor: ComposeColor,
) {
    sealed interface Type {
        object Circled : Type
        object Solid : Type
        object Striped : Type
    }

    sealed interface Color {
        object Dark : Color
        object Gray : Color
        object Green : Color
        object Blue : Color
        object Red : Color
        object Yellow : Color
        object Light : Color
        data class Custom(val value: ComposeColor) : Color
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(color: LegendTheme.Color): LegendTheme {
    val colors = PrelineTheme.colors
    return LegendTheme(
        indicatorColor = when (color) {
            LegendTheme.Color.Dark      -> colors.foreground
            LegendTheme.Color.Gray      -> colors.mutedForeground1
            LegendTheme.Color.Green     -> colors.green.solid
            LegendTheme.Color.Blue      -> colors.primary
            LegendTheme.Color.Red       -> colors.red.solid
            LegendTheme.Color.Yellow    -> colors.yellow.solid
            LegendTheme.Color.Light     -> Palette.White
            is LegendTheme.Color.Custom -> color.value
        },
        labelColor = if (color is LegendTheme.Color.Dark || color is LegendTheme.Color.Custom) colors.foreground else colors.mutedForeground1
    )
}
