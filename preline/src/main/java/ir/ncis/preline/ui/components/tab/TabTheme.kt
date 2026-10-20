package ir.ncis.preline.ui.components.tab

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.components.badge.BadgeTheme
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium

data class TabTheme(
    val itemColor: Color,
    val dividerColor: Color,
    val cardBackground: Color,
    val badgeType: BadgeTheme.Type,
    val badgeColor: BadgeTheme.Color,
    val badgeSize: BadgeTheme.Size,
    val badgeShape: BadgeTheme.Shape,
    val shape: Shape,
    val textStyle: TextStyle,
    val iconSize: Dp,
    val verticalPadding: Dp,
) {
    sealed interface Direction {
        object Horizontal : Direction
        object Vertical : Direction
    }

    sealed interface Fill {
        object None : Fill
        object Filled : Fill
    }

    sealed interface Type {
        object Base : Type
        object Bordered : Type
        object BrandPills : Type
        object Segment : Type
        object GrayPills : Type
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(type: TabTheme.Type, isSelected: Boolean, enabled: Boolean): TabTheme = TabTheme(
    itemColor = getItemColor(type, isSelected),
    dividerColor = getDividerColor(type, isSelected),
    cardBackground = getCardBackground(type, isSelected),
    badgeType = getBadgeType(type),
    badgeColor = getBadgeColor(type),
    badgeSize = BadgeTheme.Size.Small,
    badgeShape = BadgeTheme.Shape.Pilled,
    shape = RoundedCornerShape(8.dp),
    textStyle = PrelineTheme.typography.sm.medium,
    iconSize = 20.dp,
    verticalPadding = 8.dp
)

@Composable
@ReadOnlyComposable
private fun getItemColor(type: TabTheme.Type, isSelected: Boolean): Color {
    if (!isSelected) return PrelineTheme.colors.foreground
    return when (type) {
        TabTheme.Type.BrandPills -> PrelineTheme.colors.primaryForeground
        TabTheme.Type.GrayPills  -> PrelineTheme.colors.surfaceForeground
        TabTheme.Type.Segment    -> PrelineTheme.colors.surfaceForeground
        else                     -> PrelineTheme.colors.primary
    }
}

@Composable
@ReadOnlyComposable
private fun getDividerColor(type: TabTheme.Type, isSelected: Boolean): Color {
    if (type != TabTheme.Type.Bordered) return Palette.Transparent
    return if (isSelected) PrelineTheme.colors.primaryActive else PrelineTheme.colors.border
}

@Composable
@ReadOnlyComposable
private fun getCardBackground(type: TabTheme.Type, isSelected: Boolean): Color {
    return when (type) {
        TabTheme.Type.BrandPills -> PrelineTheme.colors.primaryActive
        TabTheme.Type.GrayPills  -> PrelineTheme.colors.surface1
        TabTheme.Type.Segment    -> PrelineTheme.colors.layer
        else                     -> Palette.Transparent
    }
}

private fun getBadgeType(type: TabTheme.Type): BadgeTheme.Type {
    return when (type) {
        TabTheme.Type.BrandPills, TabTheme.Type.GrayPills -> BadgeTheme.Type.White
        else                                              -> BadgeTheme.Type.Soft
    }
}

private fun getBadgeColor(type: TabTheme.Type): BadgeTheme.Color {
    return when (type) {
        TabTheme.Type.BrandPills, TabTheme.Type.GrayPills -> BadgeTheme.Color.White
        else                                              -> BadgeTheme.Color.Blue
    }
}
