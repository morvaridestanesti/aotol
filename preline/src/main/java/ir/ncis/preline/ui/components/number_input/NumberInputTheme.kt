package ir.ncis.preline.ui.components.number_input

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme

data class NumberInputTheme(
    val background: Color,
    val stroke: Color,
    val text: Color,
    val label: Color,
    val buttonBackground: Color,
    val buttonStroke: Color,
    val buttonShape: Shape,
    val feedback: Color,
    val shape: Shape,
) {
    sealed interface Type {
        object Gray : Type
        object Input : Type
        object VerticalButtons : Type
        object HorizontalButtons : Type
        object Mini : Type
        object Plain : Type
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(type: NumberInputTheme.Type, invalid: Boolean): NumberInputTheme {
    val colors = PrelineTheme.colors
    return NumberInputTheme(
        background = when (type) {
            NumberInputTheme.Type.Gray  -> colors.surface
            NumberInputTheme.Type.Plain -> Palette.Transparent
            else                        -> colors.layer
        },
        stroke = when {
            invalid                                                                   -> colors.red.solid
            type is NumberInputTheme.Type.Gray || type is NumberInputTheme.Type.Plain -> Palette.Transparent
            else                                                                      -> colors.layerBorder
        },
        text = colors.foreground,
        label = colors.mutedForeground1,
        buttonBackground = colors.layer,
        buttonStroke = colors.layerBorder,
        buttonShape = if (type is NumberInputTheme.Type.Input || type is NumberInputTheme.Type.Plain) CircleShape else RoundedCornerShape(6.dp),
        feedback = colors.red.solid,
        shape = RoundedCornerShape(8.dp)
    )
}
