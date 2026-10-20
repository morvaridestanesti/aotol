package ir.ncis.preline.ui.components.input

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape as ComposeShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme

data class InputTheme(
    val labelColor: Color,
    val secondaryLabelColor: Color,
    val borderColor: Color,
    val borderWidth: Dp,
    val backgroundColor: Color,
    val textColor: Color,
    val placeholderColor: Color,
    val feedbackTextColor: Color,
    val containerHeight: Dp,
    val containerPadding: Dp,
    val contentSpacing: Dp,
    val textStyle: TextStyle,
    val iconSize: Dp,
    val shape: ComposeShape,
) {
    sealed interface InteractionState {
        object Default : InteractionState
        object Hover : InteractionState
        object Focus : InteractionState
        object FocusFilled : InteractionState
        object Disabled : InteractionState
    }

    sealed interface Shape {
        object None : Shape
        object Rounded : Shape
        object Pilled : Shape
    }

    sealed interface Size {
        object Default : Size
        object Large : Size
        object Small : Size
    }

    sealed interface Type {
        object Bordered : Type
        object Underline : Type
        object Light : Type
    }

    sealed interface ValidationState {
        object None : ValidationState
        object Invalid : ValidationState
        object Success : ValidationState
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(type: InputTheme.Type, interactionState: InputTheme.InteractionState, validationState: InputTheme.ValidationState, size: InputTheme.Size, shape: InputTheme.Shape): InputTheme = InputTheme(
    labelColor = PrelineTheme.colors.foreground,
    secondaryLabelColor = PrelineTheme.colors.mutedForeground1,
    borderColor = getBorderColor(type, interactionState, validationState),
    borderWidth = if (interactionState in listOf(InputTheme.InteractionState.Focus, InputTheme.InteractionState.FocusFilled)) 2.dp else 1.dp,
    backgroundColor = getBackgroundColor(type, interactionState),
    textColor = PrelineTheme.colors.foreground,
    placeholderColor = PrelineTheme.colors.mutedForeground1,
    feedbackTextColor = getFeedbackTextColor(validationState, interactionState),
    containerHeight = getContainerHeight(size),
    containerPadding = getContainerPadding(size),
    contentSpacing = getContentSpacing(size),
    textStyle = getTextStyle(size),
    iconSize = getIconSize(size),
    shape = getCornerRadius(if (type == InputTheme.Type.Underline) InputTheme.Shape.None else shape)
)

@Composable
@ReadOnlyComposable
private fun getBorderColor(
    type: InputTheme.Type,
    interactionState: InputTheme.InteractionState,
    validationState: InputTheme.ValidationState
): Color {
    return when {
        validationState == InputTheme.ValidationState.Invalid                                                  -> PrelineTheme.colors.destructive
        validationState == InputTheme.ValidationState.Success                                                  -> PrelineTheme.colors.green.solid
        interactionState == InputTheme.InteractionState.Hover                                                  -> PrelineTheme.colors.borderLine5
        interactionState in listOf(InputTheme.InteractionState.Focus, InputTheme.InteractionState.FocusFilled) -> PrelineTheme.colors.primaryFocus
        type == InputTheme.Type.Light                                                                          -> Palette.Transparent
        else                                                                                                   -> PrelineTheme.colors.layerBorder
    }
}

@Composable
@ReadOnlyComposable
private fun getBackgroundColor(type: InputTheme.Type, interactionState: InputTheme.InteractionState): Color {
    return when {
        type == InputTheme.Type.Light     -> PrelineTheme.colors.surface
        type == InputTheme.Type.Underline -> Palette.Transparent
        else                              -> PrelineTheme.colors.layer
    }
}

@Composable
@ReadOnlyComposable
private fun getFeedbackTextColor(validationState: InputTheme.ValidationState, interactionState: InputTheme.InteractionState): Color {
    return when {
        validationState == InputTheme.ValidationState.Invalid -> PrelineTheme.colors.destructive
        validationState == InputTheme.ValidationState.Success -> PrelineTheme.colors.green.solid
        else                                                  -> PrelineTheme.colors.mutedForeground1
    }
}

private fun getContainerHeight(size: InputTheme.Size): Dp = when (size) {
    InputTheme.Size.Small   -> 36.dp
    InputTheme.Size.Default -> 44.dp
    InputTheme.Size.Large   -> 60.dp
}

private fun getContainerPadding(size: InputTheme.Size): Dp = when (size) {
    InputTheme.Size.Small   -> 10.dp
    InputTheme.Size.Default -> 12.dp
    InputTheme.Size.Large   -> 16.dp
}

private fun getContentSpacing(size: InputTheme.Size): Dp = when (size) {
    InputTheme.Size.Small   -> 6.dp
    InputTheme.Size.Default -> 8.dp
    InputTheme.Size.Large   -> 10.dp
}

@Composable
@ReadOnlyComposable
private fun getTextStyle(size: InputTheme.Size): TextStyle = when (size) {
    InputTheme.Size.Small   -> PrelineTheme.typography.sm
    InputTheme.Size.Default -> PrelineTheme.typography.sm
    InputTheme.Size.Large   -> PrelineTheme.typography.base
}

private fun getIconSize(size: InputTheme.Size): Dp = when (size) {
    InputTheme.Size.Small   -> 16.dp
    InputTheme.Size.Default -> 18.dp
    InputTheme.Size.Large   -> 20.dp
}

private fun getCornerRadius(shape: InputTheme.Shape): ComposeShape = when (shape) {
    InputTheme.Shape.None    -> RoundedCornerShape(0.dp)
    InputTheme.Shape.Rounded -> RoundedCornerShape(8.dp)
    InputTheme.Shape.Pilled  -> CircleShape
}
