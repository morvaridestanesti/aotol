package ir.ncis.preline.ui.components.rating

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.PrelineTheme

data class RatingTheme(
    val emptyColor: Color,
    val filledColor: Color,
    val height: Dp,
    val gap: Dp,
) {
    sealed interface Shape {
        object Heart : Shape
        object Star : Shape
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(shape: RatingTheme.Shape, height: Dp): RatingTheme = RatingTheme(
    emptyColor = PrelineTheme.colors.surface2,
    filledColor = when (shape) {
        RatingTheme.Shape.Star  -> PrelineTheme.colors.primary
        RatingTheme.Shape.Heart -> PrelineTheme.colors.red.solid
    },
    height = height,
    gap = 4.dp
)
