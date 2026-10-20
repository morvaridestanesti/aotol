package ir.ncis.preline.ui.components.switch

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape as ComposeShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.AccentColors
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme

data class SwitchTheme(
    val trackColor: Color,
    val thumbColor: Color,
    val thumbIconColor: Color,
    val trackIconColor: Color,
    val labelColor: Color,
    val focusRingColor: Color,
    val trackWidth: Dp,
    val trackHeight: Dp,
    val thumbSize: Dp,
    val iconSize: Dp,
    val trackShape: ComposeShape,
    val thumbShape: ComposeShape,
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

    sealed interface Type {
        object Solid : Type
        object Soft : Type
    }

    sealed interface Shape {
        object Pilled : Shape
        object Rounded : Shape
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(
    checked: Boolean,
    type: SwitchTheme.Type,
    state: SwitchTheme.State,
    size: SwitchTheme.Size,
    shape: SwitchTheme.Shape,
): SwitchTheme {
    val accented = checked || state == SwitchTheme.State.Invalid || state == SwitchTheme.State.Success
    val accent = getAccent(state)
    return SwitchTheme(
        trackColor = when {
            !accented                      -> PrelineTheme.colors.surface1
            type == SwitchTheme.Type.Solid -> accent.solid
            else                           -> accent.softHover
        },
        thumbColor = if (type == SwitchTheme.Type.Soft && accented) accent.solid else Palette.White,
        thumbIconColor = when {
            !accented                      -> PrelineTheme.colors.mutedForeground1
            type == SwitchTheme.Type.Solid -> accent.solid
            else                           -> Palette.White
        },
        trackIconColor = when {
            !accented                      -> PrelineTheme.colors.mutedForeground1
            type == SwitchTheme.Type.Solid -> Palette.White
            else                           -> accent.solid
        },
        labelColor = PrelineTheme.colors.mutedForeground1,
        focusRingColor = PrelineTheme.colors.blue.softHover,
        trackWidth = getTrackWidth(size),
        trackHeight = getTrackHeight(size),
        thumbSize = getTrackHeight(size) - 4.dp,
        iconSize = getIconSize(size),
        trackShape = if (shape == SwitchTheme.Shape.Pilled) CircleShape else RoundedCornerShape(6.dp),
        thumbShape = if (shape == SwitchTheme.Shape.Pilled) CircleShape else RoundedCornerShape(4.dp),
    )
}

@Composable
@ReadOnlyComposable
private fun getAccent(state: SwitchTheme.State): AccentColors = when (state) {
    SwitchTheme.State.Success -> PrelineTheme.colors.green
    SwitchTheme.State.Invalid -> PrelineTheme.colors.red
    else                      -> PrelineTheme.colors.blue
}

private fun getTrackWidth(size: SwitchTheme.Size): Dp = when (size) {
    SwitchTheme.Size.Small   -> 36.dp
    SwitchTheme.Size.Default -> 44.dp
    SwitchTheme.Size.Large   -> 56.dp
}

private fun getTrackHeight(size: SwitchTheme.Size): Dp = when (size) {
    SwitchTheme.Size.Small   -> 20.dp
    SwitchTheme.Size.Default -> 24.dp
    SwitchTheme.Size.Large   -> 30.dp
}

private fun getIconSize(size: SwitchTheme.Size): Dp = when (size) {
    SwitchTheme.Size.Small   -> 8.dp
    SwitchTheme.Size.Default -> 10.dp
    SwitchTheme.Size.Large   -> 12.dp
}
