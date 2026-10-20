package ir.ncis.preline.ui.components.checkbox

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

data class CheckboxTheme(
    val cardBackground: Color,
    val cardBorder: Color,
    val checkboxBackground: Color,
    val checkboxForeground: Color,
    val checkboxStroke: Color,
    val focusRingColor: Color,
    val labelColor: Color,
    val secondaryLabelColor: Color,
    val iconTint: Color,
    val rowPadding: Dp,
    val checkboxSize: Dp,
    val checkboxIconSize: Dp,
    val iconSize: Dp,
    val shape: Shape,
    val checkboxShape: Shape,
) {
    sealed interface Size {
        object Small : Size
        object Default : Size
        object Large : Size
    }

    sealed interface State {
        object Default : State
        object Indeterminate : State
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
internal fun getTheme(checked: Boolean, type: CheckboxTheme.Type, style: CheckboxTheme.Style, state: CheckboxTheme.State, size: CheckboxTheme.Size): CheckboxTheme = CheckboxTheme(
    cardBackground = when {
        style != CheckboxTheme.Style.WithinInput -> Palette.Transparent
        state == CheckboxTheme.State.Disabled    -> PrelineTheme.colors.muted
        else                                     -> PrelineTheme.colors.layer
    },
    cardBorder = if (style == CheckboxTheme.Style.WithinInput) PrelineTheme.colors.layerBorder else Palette.Transparent,
    checkboxBackground = getCheckboxBackgroundColor(checked, type, state),
    checkboxForeground = getCheckboxForegroundColor(type, state),
    checkboxStroke = getCheckboxStrokeColor(checked, state),
    focusRingColor = PrelineTheme.colors.blue.softHover,
    labelColor = getLabelColor(state, PrelineTheme.colors.foreground),
    secondaryLabelColor = getLabelColor(state, PrelineTheme.colors.mutedForeground1),
    iconTint = PrelineTheme.colors.mutedForeground1,
    rowPadding = getRowPadding(style, size),
    checkboxSize = getCheckboxSize(style, size),
    checkboxIconSize = getCheckboxIconSize(size, style),
    iconSize = getIconSize(size),
    shape = if (style == CheckboxTheme.Style.WithinInput) RoundedCornerShape(8.dp) else RectangleShape,
    checkboxShape = RoundedCornerShape(4.dp)
)

private fun getRowPadding(style: CheckboxTheme.Style, size: CheckboxTheme.Size) = when (style) {
    CheckboxTheme.Style.Plain       -> 0.dp
    CheckboxTheme.Style.WithinInput -> when (size) {
        CheckboxTheme.Size.Small   -> 10.dp
        CheckboxTheme.Size.Default -> 16.dp
        CheckboxTheme.Size.Large   -> 22.dp
    }

    else              -> 0.dp
}

private fun getCheckboxSize(style: CheckboxTheme.Style, size: CheckboxTheme.Size) = when (style) {
    CheckboxTheme.Style.Plain -> 20.dp
    else                      -> when (size) {
        CheckboxTheme.Size.Small   -> 16.dp
        CheckboxTheme.Size.Default -> 20.dp
        CheckboxTheme.Size.Large   -> 24.dp
    }
}

private fun getCheckboxIconSize(size: CheckboxTheme.Size, style: CheckboxTheme.Style) = when (style) {
    CheckboxTheme.Style.Plain -> 12.dp
    else                      -> when (size) {
        CheckboxTheme.Size.Small   -> 10.dp
        CheckboxTheme.Size.Default -> 12.dp
        CheckboxTheme.Size.Large   -> 14.dp
    }
}

private fun getIconSize(size: CheckboxTheme.Size) = when (size) {
    CheckboxTheme.Size.Small   -> 16.dp
    CheckboxTheme.Size.Default -> 20.dp
    CheckboxTheme.Size.Large   -> 24.dp
}

@Composable
@ReadOnlyComposable
private fun getAccent(state: CheckboxTheme.State): AccentColors = when (state) {
    CheckboxTheme.State.Success -> PrelineTheme.colors.green
    CheckboxTheme.State.Invalid -> PrelineTheme.colors.red
    else                        -> PrelineTheme.colors.blue
}

@Composable
@ReadOnlyComposable
private fun getLabelColor(state: CheckboxTheme.State, default: Color): Color = when (state) {
    CheckboxTheme.State.Success, CheckboxTheme.State.Invalid -> getAccent(state).solid
    else                                                     -> default
}

@Composable
@ReadOnlyComposable
private fun getCheckboxBackgroundColor(checked: Boolean, type: CheckboxTheme.Type, state: CheckboxTheme.State): Color = when {
    !isFilled(checked, state)        -> PrelineTheme.colors.layer
    type == CheckboxTheme.Type.Solid -> getAccent(state).solid
    else                             -> getAccent(state).soft
}

@Composable
@ReadOnlyComposable
private fun getCheckboxForegroundColor(type: CheckboxTheme.Type, state: CheckboxTheme.State): Color = when (type) {
    CheckboxTheme.Type.Solid -> Palette.White
    CheckboxTheme.Type.Soft  -> getAccent(state).solid
}

@Composable
@ReadOnlyComposable
private fun getCheckboxStrokeColor(checked: Boolean, state: CheckboxTheme.State): Color = when {
    isFilled(checked, state)                                                     -> getAccent(state).solid
    state == CheckboxTheme.State.Success || state == CheckboxTheme.State.Invalid -> getAccent(state).solid
    else                                                                         -> PrelineTheme.colors.layerBorder
}

private fun isFilled(checked: Boolean, state: CheckboxTheme.State) = checked || state == CheckboxTheme.State.Indeterminate
