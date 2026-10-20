package ir.ncis.preline.ui.components.breadcrumb

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.semiBold

data class BreadcrumbTheme(
    val borderColor: Color,
    val dividerColor: Color,
    val separatorColor: Color,
    val itemIconColor: Color,
    val lastItemTextColor: Color,
    val itemTextColor: Color,
    val triggerBackground: Color,
    val triggerBorder: Color,
    val triggerIconColor: Color,
    val triggerShape: Shape,
    val triggerSize: Dp,
    val iconSize: Dp,
    val separatorSize: Dp,
    val verticalPadding: Dp,
    val horizontalPadding: Dp,
    val lastItemStyle: TextStyle,
    val itemStyle: TextStyle,
) {
    sealed interface SeparatorType {
        object Slash : SeparatorType
        object Chevron : SeparatorType
    }

    sealed interface Type {
        object Base : Type
        object Bordered : Type
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(type: BreadcrumbTheme.Type, isLastItem: Boolean): BreadcrumbTheme = BreadcrumbTheme(
    borderColor = PrelineTheme.colors.border,
    dividerColor = PrelineTheme.colors.border,
    separatorColor = PrelineTheme.colors.mutedForeground1,
    itemIconColor = PrelineTheme.colors.mutedForeground1,
    lastItemTextColor = PrelineTheme.colors.foreground,
    itemTextColor = PrelineTheme.colors.mutedForeground1,
    triggerBackground = PrelineTheme.colors.layer,
    triggerBorder = PrelineTheme.colors.layerBorder,
    triggerIconColor = PrelineTheme.colors.mutedForeground1,
    triggerShape = RoundedCornerShape(8.dp),
    triggerSize = 28.dp,
    iconSize = 16.dp,
    separatorSize = 16.dp,
    verticalPadding = 12.dp,
    horizontalPadding = 12.dp,
    lastItemStyle = PrelineTheme.typography.sm.semiBold,
    itemStyle = PrelineTheme.typography.sm
)
