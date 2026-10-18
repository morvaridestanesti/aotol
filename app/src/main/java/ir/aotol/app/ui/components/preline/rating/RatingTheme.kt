package ir.aotol.app.ui.components.preline.rating

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import ir.aotol.app.ui.components.preline.Palette
import ir.aotol.app.ui.components.preline.PrelineTheme

data class RatingTheme(
    val emptyColor: Color,
    val filledColor: Color,
    val height: Dp,
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
        RatingTheme.Shape.Star  -> Palette.Yellow.Yellow400
        RatingTheme.Shape.Heart -> PrelineTheme.colors.red.solid
    },
    height = height
)
