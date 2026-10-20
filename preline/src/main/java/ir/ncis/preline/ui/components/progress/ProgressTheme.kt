package ir.ncis.preline.ui.components.progress

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium

data class ProgressTheme(
    val titleColor: Color,
    val valueColor: Color,
    val withinValueColor: Color,
    val trackColor: Color,
    val emptyStepColor: Color,
    val progressColor: Color,
    val floatingBackground: Color,
    val floatingBorder: Color,
    val floatingTextColor: Color,
    val shape: Shape,
    val floatingShape: Shape,
    val barHeight: Dp,
    val withValueBarHeight: Dp,
    val stepHeight: Dp,
    val titleStyle: TextStyle,
    val valueStyle: TextStyle,
) {
    sealed interface Type {
        object Base : Type
        object TitleLabel : Type
        object TrailingLabel : Type
        object TopFloatingLabel : Type
        object BottomFloatingLabel : Type
        object WithinProgressBar : Type
        object Steps : Type
    }

    sealed interface Signal {
        object Bad : Signal
        object Poor : Signal
        object Good : Signal
        object Excellent : Signal
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(): ProgressTheme = ProgressTheme(
    titleColor = PrelineTheme.colors.foreground,
    valueColor = PrelineTheme.colors.foreground,
    withinValueColor = PrelineTheme.colors.primaryForeground,
    trackColor = PrelineTheme.colors.surface1,
    emptyStepColor = PrelineTheme.colors.surface2,
    progressColor = PrelineTheme.colors.primary,
    floatingBackground = PrelineTheme.colors.blue.soft,
    floatingBorder = PrelineTheme.colors.blue.softHover,
    floatingTextColor = PrelineTheme.colors.primary,
    shape = CircleShape,
    floatingShape = RoundedCornerShape(8.dp),
    barHeight = 8.dp,
    withValueBarHeight = 16.dp,
    stepHeight = 10.dp,
    titleStyle = PrelineTheme.typography.sm.medium,
    valueStyle = PrelineTheme.typography.sm
)

@Composable
@ReadOnlyComposable
internal fun getSignalColor(signal: ProgressTheme.Signal): Color = when (signal) {
    ProgressTheme.Signal.Bad       -> PrelineTheme.colors.red.solid
    ProgressTheme.Signal.Poor      -> PrelineTheme.colors.yellow.solid
    ProgressTheme.Signal.Good      -> PrelineTheme.colors.primary
    ProgressTheme.Signal.Excellent -> PrelineTheme.colors.green.solid
}

internal fun getSignalBars(signal: ProgressTheme.Signal): Int = when (signal) {
    ProgressTheme.Signal.Bad       -> 1
    ProgressTheme.Signal.Poor      -> 2
    ProgressTheme.Signal.Good      -> 3
    ProgressTheme.Signal.Excellent -> 4
}
