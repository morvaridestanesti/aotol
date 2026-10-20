package ir.ncis.preline.ui.components.accordion

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.semiBold

data class AccordionTheme(
    val borderColor: Color,
    val titleColor: Color,
    val shape: Shape,
    val iconSize: Dp,
    val gap: Dp,
    val horizontalPadding: Dp,
    val titleStyle: TextStyle,
) {
    sealed interface Style {
        object Normal : Style
        object Divider : Style
        object Bordered : Style
        object BorderedOpen : Style
    }

    sealed interface Toggle {
        object StartPlus : Toggle
        object StartArrow : Toggle
        object EndArrow : Toggle
        object EndPlus : Toggle
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
    shape = RoundedCornerShape(12.dp),
    iconSize = 16.dp,
    gap = 12.dp,
    horizontalPadding = when (style) {
        AccordionTheme.Style.Bordered, AccordionTheme.Style.BorderedOpen -> 16.dp
        else                                                             -> 0.dp
    },
    titleStyle = PrelineTheme.typography.sm.semiBold
)

@Composable
@ReadOnlyComposable
private fun getBorderColor(style: AccordionTheme.Style): Color = when (style) {
    AccordionTheme.Style.Bordered -> PrelineTheme.colors.layerBorder
    else                          -> Palette.Transparent
}

@Composable
@ReadOnlyComposable
private fun getTitleColor(isExpanded: Boolean): Color = if (isExpanded) PrelineTheme.colors.primary else PrelineTheme.colors.foreground
