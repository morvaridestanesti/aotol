package ir.ncis.preline.ui.components.button_group

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape as ComposeShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme

data class ButtonGroupTheme(
    val background: Color,
    val hoverBackground: Color,
    val focusBackground: Color,
    val foreground: Color,
    val stroke: Color,
    val horizontalPadding: Dp,
    val verticalPadding: Dp,
    val shape: ComposeShape,
) {
    sealed interface Type {
        object Solid : Type
        object Outline : Type
        object Soft : Type
        object White : Type
    }

    sealed interface State {
        object Default : State
        object Hover : State
        object Focus : State
        object Disabled : State
    }

    sealed interface Size {
        object Small : Size
        object Default : Size
        object Large : Size
    }

    sealed interface Shape {
        object Rounded : Shape
        object Pilled : Shape
    }

    sealed interface Direction {
        object Horizontal : Direction
        object Vertical : Direction
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(type: ButtonGroupTheme.Type, size: ButtonGroupTheme.Size, shape: ButtonGroupTheme.Shape): ButtonGroupTheme {
    val colors = PrelineTheme.colors
    return when (type) {
        ButtonGroupTheme.Type.Solid   -> ButtonGroupTheme(
            background = colors.primary,
            hoverBackground = colors.primaryHover,
            focusBackground = colors.primaryFocus,
            foreground = colors.primaryForeground,
            stroke = Palette.Transparent,
            horizontalPadding = getHorizontalPadding(size),
            verticalPadding = getVerticalPadding(size),
            shape = getShape(shape)
        )

        ButtonGroupTheme.Type.Outline -> ButtonGroupTheme(
            background = Palette.Transparent,
            hoverBackground = colors.blue.soft,
            focusBackground = colors.blue.softHover,
            foreground = colors.primary,
            stroke = colors.primary,
            horizontalPadding = getHorizontalPadding(size),
            verticalPadding = getVerticalPadding(size),
            shape = getShape(shape)
        )

        ButtonGroupTheme.Type.Soft    -> ButtonGroupTheme(
            background = colors.blue.soft,
            hoverBackground = colors.blue.softHover,
            focusBackground = colors.blue.softHover,
            foreground = colors.primary,
            stroke = Palette.Transparent,
            horizontalPadding = getHorizontalPadding(size),
            verticalPadding = getVerticalPadding(size),
            shape = getShape(shape)
        )

        ButtonGroupTheme.Type.White   -> ButtonGroupTheme(
            background = colors.layer,
            hoverBackground = colors.layerHover,
            focusBackground = colors.layerFocus,
            foreground = colors.layerForeground,
            stroke = colors.layerBorder,
            horizontalPadding = getHorizontalPadding(size),
            verticalPadding = getVerticalPadding(size),
            shape = getShape(shape)
        )
    }
}

private fun getHorizontalPadding(size: ButtonGroupTheme.Size): Dp = when (size) {
    ButtonGroupTheme.Size.Small   -> 12.dp
    ButtonGroupTheme.Size.Default -> 16.dp
    ButtonGroupTheme.Size.Large   -> 20.dp
}

private fun getVerticalPadding(size: ButtonGroupTheme.Size): Dp = when (size) {
    ButtonGroupTheme.Size.Small   -> 8.dp
    ButtonGroupTheme.Size.Default -> 12.dp
    ButtonGroupTheme.Size.Large   -> 20.dp
}

private fun getShape(shape: ButtonGroupTheme.Shape): ComposeShape = when (shape) {
    ButtonGroupTheme.Shape.Rounded -> RoundedCornerShape(8.dp)
    ButtonGroupTheme.Shape.Pilled  -> CircleShape
}
