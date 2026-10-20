package ir.ncis.preline.ui.components.list_group

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme

data class ListGroupTheme(
    val background: Color,
    val stroke: Color,
    val divider: Color,
    val stripe: Color,
    val hoverBackground: Color,
    val horizontalPadding: Dp,
    val verticalPadding: Dp,
    val shape: Shape,
) {
    sealed interface Type {
        object Basic : Type
        object Flush : Type
        object NoGutters : Type
        object Bordered : Type
        object Striped : Type
        object StripedBordered : Type
    }

    sealed interface State {
        object Default : State
        object Active : State
        object Hover : State
        object Disabled : State
    }

    sealed interface BadgePosition {
        object AfterText : BadgePosition
        object End : BadgePosition
    }

    sealed interface Direction {
        object Vertical : Direction
        object Horizontal : Direction
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(type: ListGroupTheme.Type): ListGroupTheme {
    val colors = PrelineTheme.colors
    val boxed = type is ListGroupTheme.Type.Bordered || type is ListGroupTheme.Type.Striped || type is ListGroupTheme.Type.StripedBordered
    return ListGroupTheme(
        background = if (boxed) colors.layer else Palette.Transparent,
        stroke = if (boxed) colors.layerBorder else Palette.Transparent,
        divider = if (type is ListGroupTheme.Type.Basic || type is ListGroupTheme.Type.Striped) Palette.Transparent else colors.layerBorder,
        stripe = if (type is ListGroupTheme.Type.Striped || type is ListGroupTheme.Type.StripedBordered) getStripeColor() else Palette.Transparent,
        hoverBackground = if (boxed) colors.layerHover else Palette.Transparent,
        horizontalPadding = when (type) {
            ListGroupTheme.Type.Basic, ListGroupTheme.Type.NoGutters -> 0.dp
            else                                                     -> 16.dp
        },
        verticalPadding = if (type is ListGroupTheme.Type.Basic) 4.dp else 12.dp,
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
@ReadOnlyComposable
private fun getStripeColor(): Color = with(PrelineTheme.colors) { if (isDark) surface.copy(alpha = 0.5f) else surface }

@Composable
@ReadOnlyComposable
internal fun getTextColor(state: ListGroupTheme.State, theme: ListGroupTheme): Color = when {
    state == ListGroupTheme.State.Active                                       -> PrelineTheme.colors.primary
    state == ListGroupTheme.State.Hover && theme.hoverBackground.alpha == 0f -> PrelineTheme.colors.mutedForeground1
    else                                                                       -> PrelineTheme.colors.layerForeground
}
