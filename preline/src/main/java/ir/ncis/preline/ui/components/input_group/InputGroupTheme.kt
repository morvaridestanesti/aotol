package ir.ncis.preline.ui.components.input_group

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.PrelineTheme

data class InputGroupTheme(
    val background: Color,
    val stroke: Color,
    val addOnBackground: Color,
    val addOnForeground: Color,
    val text: Color,
    val placeholder: Color,
    val height: Dp,
    val horizontalPadding: Dp,
    val textStyle: TextStyle,
    val shape: Shape,
) {
    sealed interface Size {
        object Small : Size
        object Default : Size
        object Large : Size
    }

    sealed interface ButtonStyle {
        object Solid : ButtonStyle
        object White : ButtonStyle
    }

    sealed interface Segment {
        data class AddOn(val text: String) : Segment

        data class IconAddOn(val icon: Painter, val contentDescription: String? = null, val onClick: (() -> Unit)? = null) : Segment

        data class Field(
            val value: String,
            val onValueChange: (String) -> Unit,
            val placeholder: String? = null,
            val leadingIcon: Painter? = null,
            val weight: Float = 1f,
        ) : Segment

        data class Button(
            val label: String? = null,
            val icon: Painter? = null,
            val contentDescription: String? = null,
            val style: ButtonStyle = ButtonStyle.Solid,
            val loading: Boolean = false,
            val onClick: () -> Unit,
        ) : Segment
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(size: InputGroupTheme.Size): InputGroupTheme {
    val colors = PrelineTheme.colors
    return InputGroupTheme(
        background = colors.layer,
        stroke = colors.layerBorder,
        addOnBackground = colors.layerHover,
        addOnForeground = colors.mutedForeground1,
        text = colors.foreground,
        placeholder = colors.mutedForeground,
        height = when (size) {
            InputGroupTheme.Size.Small   -> 36.dp
            InputGroupTheme.Size.Default -> 44.dp
            InputGroupTheme.Size.Large   -> 60.dp
        },
        horizontalPadding = when (size) {
            InputGroupTheme.Size.Small   -> 10.dp
            InputGroupTheme.Size.Default -> 12.dp
            InputGroupTheme.Size.Large   -> 16.dp
        },
        textStyle = if (size == InputGroupTheme.Size.Large) PrelineTheme.typography.base else PrelineTheme.typography.sm,
        shape = RoundedCornerShape(8.dp)
    )
}
