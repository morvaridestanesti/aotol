package ir.aotol.app.ui.components.preline.accordion

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.aotol.app.ui.components.preline.Palette
import ir.aotol.app.ui.components.preline.PrelineTheme
import ir.aotol.app.ui.components.preline.semiBold

data class AccordionTheme(
    val borderColor: Color,
    val titleColor: Color,
    val expandedTitleColor: Color,
    val shape: Shape,
    val iconSize: Dp,
    val titleStyle: TextStyle,
) {
    sealed interface Style {
        object Normal : Style
        object Bordered : Style
        object BorderedOpen : Style
    }

    sealed interface Toggle {
        object StartPlus : Toggle
        object StartArrow : Toggle
        object EndArrow : Toggle
        object None : Toggle
    }

    sealed interface Type {
        object Basic : Type
        object AlwaysOpen : Type
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(style: AccordionTheme.Style, isExpanded: Boolean): AccordionTheme = AccordionTheme(
    borderColor = getBorderColor(style),
    titleColor = getTitleColor(isExpanded),
    expandedTitleColor = PrelineTheme.colors.primary,
    shape = RoundedCornerShape(12.dp),
    iconSize = 16.dp,
    titleStyle = PrelineTheme.typography.sm.semiBold
)

@Composable
@ReadOnlyComposable
private fun getBorderColor(style: AccordionTheme.Style): Color = when (style) {
    AccordionTheme.Style.Bordered, AccordionTheme.Style.BorderedOpen -> PrelineTheme.colors.layerBorder
    else                                                             -> Palette.Transparent
}

@Composable
@ReadOnlyComposable
private fun getTitleColor(isExpanded: Boolean): Color = if (isExpanded) PrelineTheme.colors.primary else PrelineTheme.colors.foreground
