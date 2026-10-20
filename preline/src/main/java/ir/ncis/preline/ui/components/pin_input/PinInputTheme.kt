package ir.ncis.preline.ui.components.pin_input

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape as ComposeShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium

data class PinInputTheme(
    val background: Color,
    val stroke: Color,
    val focusedStroke: Color,
    val invalidStroke: Color,
    val text: Color,
    val placeholder: Color,
    val height: Dp,
    val spacing: Dp,
    val textStyle: TextStyle,
    val shape: ComposeShape,
) {
    sealed interface Shape {
        object Rounded : Shape
        object Circle : Shape
        object Underline : Shape
        object Light : Shape
    }

    sealed interface Size {
        object Small : Size
        object Default : Size
        object Large : Size
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(shape: PinInputTheme.Shape, size: PinInputTheme.Size): PinInputTheme {
    val colors = PrelineTheme.colors
    return PinInputTheme(
        background = when (shape) {
            PinInputTheme.Shape.Light     -> colors.layerHover
            PinInputTheme.Shape.Underline -> Palette.Transparent
            else                          -> colors.layer
        },
        stroke = if (shape == PinInputTheme.Shape.Light) Palette.Transparent else colors.layerBorder,
        focusedStroke = colors.primary,
        invalidStroke = colors.red.solid,
        text = colors.foreground,
        placeholder = colors.mutedForeground,
        height = when (size) {
            PinInputTheme.Size.Small   -> 36.dp
            PinInputTheme.Size.Default -> 44.dp
            PinInputTheme.Size.Large   -> 56.dp
        },
        spacing = when (size) {
            PinInputTheme.Size.Small   -> 8.dp
            PinInputTheme.Size.Default -> 10.dp
            PinInputTheme.Size.Large   -> 12.dp
        },
        textStyle = (if (size == PinInputTheme.Size.Large) PrelineTheme.typography.lg else PrelineTheme.typography.base).medium,
        shape = when (shape) {
            PinInputTheme.Shape.Rounded, PinInputTheme.Shape.Light -> RoundedCornerShape(8.dp)
            PinInputTheme.Shape.Circle                              -> CircleShape
            PinInputTheme.Shape.Underline                           -> RectangleShape
        }
    )
}
