package ir.ncis.preline.ui.components.slider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme

data class SliderTheme(
    val arrowBackground: Color,
    val arrowIcon: Color,
    val dot: Color,
    val activeDot: Color,
    val insideDot: Color,
    val insideActiveDot: Color,
    val fraction: Color,
    val fractionTotal: Color,
    val insideFraction: Color,
) {
    sealed interface Arrows {
        object None : Arrows
        object Both : Arrows
        object Previous : Arrows
        object Next : Arrows
    }

    sealed interface Indicator {
        object None : Indicator
        object Outside : Indicator
        object Inside : Indicator
        object InsideVertical : Indicator
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(): SliderTheme {
    val colors = PrelineTheme.colors
    return SliderTheme(
        arrowBackground = colors.layer.copy(alpha = 0.8f),
        arrowIcon = colors.foreground,
        dot = colors.surface1,
        activeDot = colors.primary,
        insideDot = Palette.White.copy(alpha = 0.5f),
        insideActiveDot = Palette.White,
        fraction = colors.foreground,
        fractionTotal = colors.mutedForeground1,
        insideFraction = Palette.White
    )
}
