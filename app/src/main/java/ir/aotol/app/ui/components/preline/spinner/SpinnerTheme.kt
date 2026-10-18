package ir.aotol.app.ui.components.preline.spinner

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.aotol.app.ui.components.preline.PrelineTheme
import ir.aotol.app.ui.components.preline.medium

data class SpinnerTheme(
    val strokeWidth: Dp,
    val spinnerSize: Dp,
    val textStyle: TextStyle,
    val trackColor: Color,
    val progressColor: Color,
    val labelColor: Color,
) {
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
    textStyle = getTextStyle(size),
    trackColor = PrelineTheme.colors.surface,
    progressColor = PrelineTheme.colors.primary,
    labelColor = PrelineTheme.colors.foreground
)

private fun getStrokeWidth(size: SpinnerTheme.Size): Dp = when (size) {
    SpinnerTheme.Size.Large   -> 8.dp
    SpinnerTheme.Size.Default -> 6.dp
    SpinnerTheme.Size.Small   -> 4.dp
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
    SpinnerTheme.Size.Large   -> 64.dp
    SpinnerTheme.Size.Default -> 44.dp
    SpinnerTheme.Size.Small   -> 36.dp
    SpinnerTheme.Size.XS      -> 20.dp
}
