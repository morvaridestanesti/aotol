package ir.aotol.app.ui.components.preline.card

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.aotol.app.ui.components.preline.PrelineTheme
import ir.aotol.app.ui.components.preline.medium
import ir.aotol.app.ui.components.preline.semiBold

data class CardTheme(
    val cardBackground: Color,
    val cardBorder: Color,
    val headerTitleColor: Color,
    val titleColor: Color,
    val subtitleColor: Color,
    val contentColor: Color,
    val footerBackground: Color,
    val footerTextColor: Color,
    val shape: Shape,
    val headerTitleStyle: TextStyle,
    val titleStyle: TextStyle,
    val subtitleStyle: TextStyle,
    val contentStyle: TextStyle,
    val footerStyle: TextStyle,
    val padding: Dp,
) {
    sealed interface Style {
        object Default : Style
        object Simple : Style
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(): CardTheme = CardTheme(
    cardBackground = PrelineTheme.colors.card,
    cardBorder = PrelineTheme.colors.cardBorder,
    headerTitleColor = PrelineTheme.colors.foreground,
    titleColor = PrelineTheme.colors.layerForeground,
    subtitleColor = PrelineTheme.colors.mutedForeground1,
    contentColor = PrelineTheme.colors.mutedForeground1,
    footerBackground = PrelineTheme.colors.muted,
    footerTextColor = PrelineTheme.colors.mutedForeground,
    shape = RoundedCornerShape(12.dp),
    headerTitleStyle = PrelineTheme.typography.lg.semiBold,
    titleStyle = PrelineTheme.typography.lg.semiBold,
    subtitleStyle = PrelineTheme.typography.sm.semiBold,
    contentStyle = PrelineTheme.typography.base.medium,
    footerStyle = PrelineTheme.typography.sm.medium,
    padding = 24.dp
)
