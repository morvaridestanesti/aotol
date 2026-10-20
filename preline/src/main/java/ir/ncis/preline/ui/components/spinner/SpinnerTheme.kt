package ir.ncis.preline.ui.components.spinner

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium

data class SpinnerTheme(
    val strokeWidth: Dp,
    val spinnerSize: Dp,
    val dotSize: Dp,
    val textStyle: TextStyle,
    val trackColor: Color,
    val progressColor: Color,
    val dotColor: Color,
    val labelColor: Color,
) {
    sealed interface Type {
        object Line : Type
        object Circular : Type
        object Dots : Type
    }

    sealed interface Size {
        object Large : Size
        object Default : Size
        object Small : Size
        object XS : Size
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(size: SpinnerTheme.Size): SpinnerTheme = SpinnerTheme(
    strokeWidth = getStrokeWidth(size),
    spinnerSize = getSpinnerSize(size),
    dotSize = getDotSize(size),
    textStyle = getTextStyle(size),
    trackColor = PrelineTheme.colors.surface,
    progressColor = PrelineTheme.colors.primary,
    dotColor = PrelineTheme.colors.mutedForeground,
    labelColor = PrelineTheme.colors.foreground
)

private fun getStrokeWidth(size: SpinnerTheme.Size): Dp = when (size) {
    SpinnerTheme.Size.Large   -> 5.dp
    SpinnerTheme.Size.Default -> 4.dp
    SpinnerTheme.Size.Small   -> 3.dp
    SpinnerTheme.Size.XS      -> 2.dp
}

@Composable
@ReadOnlyComposable
private fun getTextStyle(size: SpinnerTheme.Size): TextStyle = when (size) {
    SpinnerTheme.Size.Large   -> PrelineTheme.typography.lg.medium
    SpinnerTheme.Size.Default -> PrelineTheme.typography.base.medium
    SpinnerTheme.Size.Small   -> PrelineTheme.typography.sm.medium
    SpinnerTheme.Size.XS      -> PrelineTheme.typography.xs.medium
}

private fun getSpinnerSize(size: SpinnerTheme.Size): Dp = when (size) {
    SpinnerTheme.Size.Large   -> 48.dp
    SpinnerTheme.Size.Default -> 32.dp
    SpinnerTheme.Size.Small   -> 24.dp
    SpinnerTheme.Size.XS      -> 16.dp
}

private fun getDotSize(size: SpinnerTheme.Size): Dp = when (size) {
    SpinnerTheme.Size.Large   -> 12.dp
    SpinnerTheme.Size.Default -> 8.dp
    SpinnerTheme.Size.Small   -> 6.dp
    SpinnerTheme.Size.XS      -> 4.dp
}
