package ir.ncis.preline.ui.components.range_slider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import ir.ncis.preline.ui.theme.PrelineTheme

data class RangeSliderTheme(
    val track: Color,
    val activeTrack: Color,
    val thumb: Color,
    val thumbBorder: Color,
    val tooltipBackground: Color,
    val tooltipBorder: Color,
    val tooltipText: Color,
    val tick: Color,
    val label: Color,
)

@Composable
@ReadOnlyComposable
internal fun getTheme(): RangeSliderTheme {
    val colors = PrelineTheme.colors
    return RangeSliderTheme(
        track = colors.surface,
        activeTrack = colors.primary,
        thumb = colors.layer,
        thumbBorder = colors.primary,
        tooltipBackground = colors.layer,
        tooltipBorder = colors.layerBorder,
        tooltipText = colors.foreground,
        tick = colors.mutedForeground,
        label = colors.mutedForeground1
    )
}
