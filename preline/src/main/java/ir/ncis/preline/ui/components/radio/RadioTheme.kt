package ir.ncis.preline.ui.components.radio

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.AccentColors
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme

data class RadioTheme(
    val cardBackground: Color,
    val cardBorder: Color,
    val radioBackground: Color,
    val dotColor: Color,
    val strokeColor: Color,
    val focusRingColor: Color,
    val labelColor: Color,
    val secondaryLabelColor: Color,
    val iconTint: Color,
    val rowPadding: Dp,
    val radioSize: Dp,
    val radioIconSize: Dp,
    val iconSize: Dp,
    val shape: Shape,
    val radioShape: Shape,
) {
    sealed interface Size {
        object Small : Size
        object Default : Size
        object Large : Size
    }

    sealed interface State {
        object Default : State
        object Focus : State
        object Disabled : State
        object Invalid : State
        object Success : State
    }

    sealed interface Style {
        object Plain : Style
        object WithinInput : Style
    }

    sealed interface Type {
        object Solid : Type
        object Soft : Type
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(checked: Boolean, type: RadioTheme.Type, style: RadioTheme.Style, state: RadioTheme.State, size: RadioTheme.Size): RadioTheme = RadioTheme(
    cardBackground = when {
        style != RadioTheme.Style.WithinInput -> Palette.Transparent
        state == RadioTheme.State.Disabled    -> PrelineTheme.colors.muted
        else                                  -> PrelineTheme.colors.layer
    },
    cardBorder = if (style == RadioTheme.Style.WithinInput) PrelineTheme.colors.layerBorder else Palette.Transparent,
    radioBackground = getRadioBackgroundColor(checked, type, state),
    dotColor = getRadioForegroundColor(type, state),
    strokeColor = getRadioStrokeColor(checked, state),
    focusRingColor = PrelineTheme.colors.blue.softHover,
    labelColor = getLabelColor(state, PrelineTheme.colors.foreground),
    secondaryLabelColor = getLabelColor(state, PrelineTheme.colors.mutedForeground1),
    iconTint = PrelineTheme.colors.mutedForeground1,
    rowPadding = getRowPadding(style, size),
    radioSize = getRadioSize(style, size),
    radioIconSize = getRadioIconSize(size, style),
    iconSize = getIconSize(size),
    shape = if (style == RadioTheme.Style.WithinInput) RoundedCornerShape(8.dp) else RectangleShape,
    radioShape = CircleShape
)

private fun getRowPadding(style: RadioTheme.Style, size: RadioTheme.Size) = when (style) {
    RadioTheme.Style.Plain       -> 0.dp
    RadioTheme.Style.WithinInput -> when (size) {
        RadioTheme.Size.Small   -> 10.dp
        RadioTheme.Size.Default -> 16.dp
        RadioTheme.Size.Large   -> 22.dp
    }
}

private fun getRadioSize(style: RadioTheme.Style, size: RadioTheme.Size) = when (style) {
    RadioTheme.Style.Plain -> 20.dp
    else                   -> when (size) {
        RadioTheme.Size.Small   -> 16.dp
        RadioTheme.Size.Default -> 20.dp
        RadioTheme.Size.Large   -> 24.dp
    }
}

private fun getRadioIconSize(size: RadioTheme.Size, style: RadioTheme.Style) = when (style) {
    RadioTheme.Style.Plain -> 8.dp
    else                   -> when (size) {
        RadioTheme.Size.Small   -> 6.dp
        RadioTheme.Size.Default -> 8.dp
        RadioTheme.Size.Large   -> 10.dp
    }
}

private fun getIconSize(size: RadioTheme.Size) = when (size) {
    RadioTheme.Size.Small   -> 16.dp
    RadioTheme.Size.Default -> 20.dp
    RadioTheme.Size.Large   -> 24.dp
}

@Composable
@ReadOnlyComposable
private fun getAccent(state: RadioTheme.State): AccentColors = when (state) {
    RadioTheme.State.Success -> PrelineTheme.colors.green
    RadioTheme.State.Invalid -> PrelineTheme.colors.red
    else                     -> PrelineTheme.colors.blue
}

@Composable
@ReadOnlyComposable
private fun getLabelColor(state: RadioTheme.State, default: Color): Color = when (state) {
    RadioTheme.State.Success, RadioTheme.State.Invalid -> getAccent(state).solid
    else                                               -> default
}

@Composable
@ReadOnlyComposable
private fun getRadioBackgroundColor(checked: Boolean, type: RadioTheme.Type, state: RadioTheme.State): Color = when {
    !checked                      -> PrelineTheme.colors.layer
    type == RadioTheme.Type.Solid -> getAccent(state).solid
    else                          -> getAccent(state).soft
}

@Composable
@ReadOnlyComposable
private fun getRadioForegroundColor(type: RadioTheme.Type, state: RadioTheme.State): Color = when (type) {
    RadioTheme.Type.Solid -> Palette.White
    RadioTheme.Type.Soft  -> getAccent(state).solid
}

@Composable
@ReadOnlyComposable
private fun getRadioStrokeColor(checked: Boolean, state: RadioTheme.State): Color = when {
    checked                                                                -> getAccent(state).solid
    state == RadioTheme.State.Success || state == RadioTheme.State.Invalid -> getAccent(state).solid
    else                                                                   -> PrelineTheme.colors.layerBorder
}
