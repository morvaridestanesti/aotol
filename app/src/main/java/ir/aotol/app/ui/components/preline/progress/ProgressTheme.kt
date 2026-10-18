package ir.aotol.app.ui.components.preline.progress

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.aotol.app.ui.components.preline.PrelineTheme
import ir.aotol.app.ui.components.preline.bold

data class ProgressTheme(
    val titleColor: Color,
    val valueColor: Color,
    val trackColor: Color,
    val progressColor: Color,
    val floatingBackground: Color,
    val floatingBorder: Color,
    val floatingTextColor: Color,
    val shape: Shape,
    val floatingShape: Shape,
    val barHeight: Dp,
    val withValueBarHeight: Dp,
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
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(): ProgressTheme = ProgressTheme(
    titleColor = PrelineTheme.colors.foreground,
    valueColor = PrelineTheme.colors.foreground,
    trackColor = PrelineTheme.colors.surface,
    progressColor = PrelineTheme.colors.primary,
    floatingBackground = PrelineTheme.colors.blue.soft,
    floatingBorder = PrelineTheme.colors.blue.softHover,
    floatingTextColor = PrelineTheme.colors.primary,
    shape = CircleShape,
    floatingShape = RoundedCornerShape(6.dp),
    barHeight = 10.dp,
    withValueBarHeight = 20.dp,
    titleStyle = PrelineTheme.typography.base.bold,
    valueStyle = PrelineTheme.typography.sm
)
