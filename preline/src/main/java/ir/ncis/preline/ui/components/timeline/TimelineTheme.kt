package ir.ncis.preline.ui.components.timeline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import ir.ncis.preline.ui.theme.PrelineTheme

data class TimelineTheme(
    val line: Color,
    val dot: Color,
    val markerBorder: Color,
    val markerBackground: Color,
    val markerForeground: Color,
    val numberBorder: Color,
    val numberForeground: Color,
    val title: Color,
    val description: Color,
    val caption: Color,
    val highlight: Color,
) {
    sealed interface Marker {
        object Dot : Marker
        object Ring : Marker
        data class Initial(val text: String) : Marker
        data class Number(val value: Int) : Marker
        data class Icon(val painter: Painter) : Marker
        data class Image(val painter: Painter) : Marker
    }

    sealed interface TimePosition {
        object Start : TimePosition
        object Above : TimePosition
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(): TimelineTheme {
    val colors = PrelineTheme.colors
    return TimelineTheme(
        line = colors.layerBorder,
        dot = colors.mutedForeground,
        markerBorder = colors.layerBorder,
        markerBackground = colors.layer,
        markerForeground = colors.foreground,
        numberBorder = colors.blue.softHover,
        numberForeground = colors.primary,
        title = colors.foreground,
        description = colors.mutedForeground1,
        caption = colors.mutedForeground1,
        highlight = colors.layerHover
    )
}
