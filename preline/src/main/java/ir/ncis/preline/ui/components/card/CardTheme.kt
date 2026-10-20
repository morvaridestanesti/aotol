package ir.ncis.preline.ui.components.card

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme

data class CardTheme(
    val background: Color,
    val border: Color,
    val sectionBackground: Color,
    val divider: Color,
    val accent: Color,
    val shape: Shape,
    val padding: Dp,
    val sectionVerticalPadding: Dp,
    val accentBorderWidth: Dp,
) {
    sealed interface Style {
        object Bordered : Style
        object Soft : Style
    }

    sealed interface MediaPosition {
        object Top : MediaPosition
        object Bottom : MediaPosition
        object Start : MediaPosition
        object Background : MediaPosition
    }

    sealed interface Direction {
        object Horizontal : Direction
        object Vertical : Direction
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(style: CardTheme.Style): CardTheme = CardTheme(
    background = when (style) {
        CardTheme.Style.Bordered -> PrelineTheme.colors.card
        CardTheme.Style.Soft     -> PrelineTheme.colors.surface
    },
    border = when (style) {
        CardTheme.Style.Bordered -> PrelineTheme.colors.cardBorder
        CardTheme.Style.Soft     -> Palette.Transparent
    },
    sectionBackground = PrelineTheme.colors.surface,
    divider = PrelineTheme.colors.cardBorder,
    accent = PrelineTheme.colors.primary,
    shape = RoundedCornerShape(12.dp),
    padding = 16.dp,
    sectionVerticalPadding = 12.dp,
    accentBorderWidth = 4.dp,
)
