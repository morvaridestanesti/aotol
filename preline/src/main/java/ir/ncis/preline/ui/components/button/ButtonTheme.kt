package ir.ncis.preline.ui.components.button

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.graphics.Shape as ComposeShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.AccentColors
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineColors
import ir.ncis.preline.ui.theme.PrelineTheme

data class ButtonTheme(
    val background: ComposeColor,
    val foreground: ComposeColor,
    val stroke: ComposeColor,
    val horizontalPadding: Dp,
    val verticalPadding: Dp,
    val shape: ComposeShape,
) {
    sealed interface Color {
        object Dark : Color
        object Gray : Color
        object Green : Color
        object Blue : Color
        object Red : Color
        object Yellow : Color
        object Light : Color
    }

    sealed interface Size {
        object Default : Size
        object Large : Size
        object Small : Size
    }

    sealed interface State {
        object Default : State
        object Disabled : State
        object Focus : State
        object Hover : State
    }

    sealed interface Shape {
        object Rounded : Shape
        object Pilled : Shape
    }

    sealed interface Style {
        object Ghost : Style
        object Outlined : Style
        object Soft : Style
        object Solid : Style
        object Link : Style
        object White : Style
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(style: ButtonTheme.Style, color: ButtonTheme.Color, state: ButtonTheme.State, size: ButtonTheme.Size, shape: ButtonTheme.Shape): ButtonTheme {
    val colors = PrelineTheme.colors
    val visualState = if (state == ButtonTheme.State.Disabled) ButtonTheme.State.Default else state
    return ButtonTheme(
        background = colors.getBackgroundColor(style, color, visualState),
        foreground = colors.getForegroundColor(style, color, visualState),
        stroke = colors.getStrokeColor(style, color, visualState),
        horizontalPadding = getHorizontalPadding(size),
        verticalPadding = getVerticalPadding(size),
        shape = if (shape == ButtonTheme.Shape.Pilled) CircleShape else RoundedCornerShape(8.dp)
    )
}

private fun PrelineColors.accent(color: ButtonTheme.Color): AccentColors? = when (color) {
    ButtonTheme.Color.Green  -> green
    ButtonTheme.Color.Blue   -> blue
    ButtonTheme.Color.Red    -> red
    ButtonTheme.Color.Yellow -> yellow
    else                     -> null
}

private fun PrelineColors.getBackgroundColor(style: ButtonTheme.Style, color: ButtonTheme.Color, state: ButtonTheme.State): ComposeColor {
    val accent = accent(color)
    val idle = state == ButtonTheme.State.Default
    return when (style) {
        ButtonTheme.Style.Ghost    -> when {
            idle                             -> Palette.Transparent
            accent != null                   -> accent.soft
            color == ButtonTheme.Color.Light -> Palette.White10
            state == ButtonTheme.State.Focus -> mutedFocus
            else                             -> mutedHover
        }

        ButtonTheme.Style.Outlined,
        ButtonTheme.Style.Link     -> if (state == ButtonTheme.State.Focus) Palette.White1 else Palette.Transparent

        ButtonTheme.Style.Soft     -> when {
            accent != null                   -> if (idle) accent.soft else accent.softHover
            color == ButtonTheme.Color.Light -> if (idle) Palette.White10 else Palette.White20
            state == ButtonTheme.State.Focus -> surfaceFocus
            state == ButtonTheme.State.Hover -> surfaceHover
            else                             -> surface
        }

        ButtonTheme.Style.Solid    -> when (color) {
            ButtonTheme.Color.Dark  -> when (state) {
                ButtonTheme.State.Focus -> secondaryFocus
                ButtonTheme.State.Hover -> secondaryHover
                else                    -> secondary
            }

            ButtonTheme.Color.Gray -> if (idle) surface4 else surface5
            ButtonTheme.Color.Blue -> when (state) {
                ButtonTheme.State.Focus -> primaryFocus
                ButtonTheme.State.Hover -> primaryHover
                else                    -> primary
            }

            ButtonTheme.Color.Light -> if (idle) Palette.White else Palette.White90
            else                    -> if (idle) accent!!.solid else accent!!.solidHover
        }

        ButtonTheme.Style.White    -> when {
            idle                             -> layer
            color == ButtonTheme.Color.Light -> Palette.White90
            state == ButtonTheme.State.Focus -> layerFocus
            else                             -> layerHover
        }
    }
}

private fun PrelineColors.getForegroundColor(style: ButtonTheme.Style, color: ButtonTheme.Color, state: ButtonTheme.State): ComposeColor {
    val accent = accent(color)
    val idle = state == ButtonTheme.State.Default
    return when (style) {
        ButtonTheme.Style.Ghost    -> if (!idle && accent != null) accent.softForeground else getDefaultForeground(color)

        ButtonTheme.Style.Outlined -> when {
            idle                             -> getDefaultForeground(color)
            color == ButtonTheme.Color.Dark  -> if (state == ButtonTheme.State.Focus) primaryFocus else primaryHover
            color == ButtonTheme.Color.Gray  -> foreground
            color == ButtonTheme.Color.Light -> Palette.White70
            else                             -> accent!!.outlineHover
        }

        ButtonTheme.Style.Soft     -> when (color) {
            ButtonTheme.Color.Dark  -> surfaceForeground
            ButtonTheme.Color.Gray  -> mutedForeground1
            ButtonTheme.Color.Light -> Palette.White
            else                    -> accent!!.softForeground
        }

        ButtonTheme.Style.Solid    -> when (color) {
            ButtonTheme.Color.Dark  -> secondaryForeground
            ButtonTheme.Color.Blue  -> primaryForeground
            ButtonTheme.Color.Light -> Palette.Gray.Gray800
            else                    -> Palette.White
        }

        ButtonTheme.Style.Link     -> when {
            idle                             -> getDefaultForeground(color)
            color == ButtonTheme.Color.Light -> Palette.White
            color == ButtonTheme.Color.Gray  -> primaryFocus
            color == ButtonTheme.Color.Dark  -> if (state == ButtonTheme.State.Focus) primaryFocus else primaryHover
            else                             -> accent!!.solidHover
        }

        ButtonTheme.Style.White    -> when (color) {
            ButtonTheme.Color.Dark  -> layerForeground
            ButtonTheme.Color.Light -> Palette.Gray.Gray800
            else                    -> getDefaultForeground(color)
        }
    }
}

private fun PrelineColors.getDefaultForeground(color: ButtonTheme.Color): ComposeColor = when (color) {
    ButtonTheme.Color.Dark  -> foreground
    ButtonTheme.Color.Gray  -> mutedForeground1
    ButtonTheme.Color.Light -> Palette.White
    else                    -> accent(color)!!.solid
}

private fun PrelineColors.getStrokeColor(style: ButtonTheme.Style, color: ButtonTheme.Color, state: ButtonTheme.State): ComposeColor {
    val accent = accent(color)
    return when (style) {
        ButtonTheme.Style.Outlined -> when (color) {
            ButtonTheme.Color.Dark  -> if (state == ButtonTheme.State.Default) borderLine8 else getForegroundColor(style, color, state)
            ButtonTheme.Color.Gray  -> if (state == ButtonTheme.State.Default) borderLine5 else borderLine8
            ButtonTheme.Color.Light -> Palette.White
            else                    -> getForegroundColor(style, color, state)
        }

        ButtonTheme.Style.White -> if (color == ButtonTheme.Color.Light && state != ButtonTheme.State.Default) Palette.Transparent else border
        else                    -> when {
            state != ButtonTheme.State.Focus                                    -> Palette.Transparent
            accent != null                                                      -> accent.solid
            color == ButtonTheme.Color.Light                                    -> Palette.White
            style == ButtonTheme.Style.Ghost || style == ButtonTheme.Style.Link -> border
            else                                                                -> Palette.Transparent
        }
    }
}

private fun getHorizontalPadding(size: ButtonTheme.Size): Dp = when (size) {
    ButtonTheme.Size.Default -> 16.dp
    ButtonTheme.Size.Large   -> 20.dp
    ButtonTheme.Size.Small   -> 12.dp
}

private fun getVerticalPadding(size: ButtonTheme.Size): Dp = when (size) {
    ButtonTheme.Size.Default -> 12.dp
    ButtonTheme.Size.Large   -> 20.dp
    ButtonTheme.Size.Small   -> 8.dp
}
