package ir.ncis.preline.ui.components.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.components.styled_icon.StyledIconTheme
import ir.ncis.preline.ui.theme.PrelineTheme

data class ListTheme(
    val textColor: ComposeColor,
    val iconColor: ComposeColor,
    val separatorColor: ComposeColor,
    val borderColor: ComposeColor,
    val decimalTextColor: ComposeColor,
    val discBackground: ComposeColor,
    val textStyle: TextStyle,
    val iconSize: Dp,
    val discSize: Dp,
    val featuredIconType: StyledIconTheme.Type,
    val featuredIconColor: StyledIconTheme.Color,
    val featuredIconShape: StyledIconTheme.Shape,
    val featuredIconSize: StyledIconTheme.Size,
) {
    sealed interface Color {
        object Blue : Color
        object Dark : Color
        object Gray : Color
        object Green : Color
        object Light : Color
        object Red : Color
        object Yellow : Color
    }

    sealed interface Direction {
        object Horizontal : Direction
        object Vertical : Direction
    }

    sealed interface Shape {
        object Circular : Shape
        object Rounded : Shape
    }

    sealed interface State {
        object Active : State
        object Default : State
        object Disabled : State
        object Hover : State
    }

    sealed interface Style {
        object Default : Style
        object Outlined : Style
        object Soft : Style
        object SoftOutlined : Style
        object Solid : Style
        object White : Style
    }

    sealed interface Type {
        object Decimal : Type
        object Disc : Type
        object None : Type
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(style: ListTheme.Style, color: ListTheme.Color, shape: ListTheme.Shape, state: ListTheme.State): ListTheme = ListTheme(
    textColor = getTextColor(state),
    iconColor = getTextColor(state),
    separatorColor = getSeparatorColor(state),
    borderColor = PrelineTheme.colors.border,
    decimalTextColor = PrelineTheme.colors.foreground,
    discBackground = PrelineTheme.colors.mutedForeground,
    textStyle = PrelineTheme.typography.sm,
    iconSize = 16.dp,
    discSize = 6.dp,
    featuredIconType = getFeaturedIconType(style),
    featuredIconColor = getFeaturedIconColor(color),
    featuredIconShape = getFeaturedIconShape(shape),
    featuredIconSize = StyledIconTheme.Size.XS
)

private fun getFeaturedIconColor(color: ListTheme.Color): StyledIconTheme.Color = when (color) {
    ListTheme.Color.Blue   -> StyledIconTheme.Color.Blue
    ListTheme.Color.Dark   -> StyledIconTheme.Color.Dark
    ListTheme.Color.Gray   -> StyledIconTheme.Color.Gray
    ListTheme.Color.Green  -> StyledIconTheme.Color.Green
    ListTheme.Color.Light  -> StyledIconTheme.Color.Light
    ListTheme.Color.Red    -> StyledIconTheme.Color.Red
    ListTheme.Color.Yellow -> StyledIconTheme.Color.Yellow
}

private fun getFeaturedIconType(style: ListTheme.Style): StyledIconTheme.Type = when (style) {
    ListTheme.Style.Default      -> StyledIconTheme.Type.Base
    ListTheme.Style.Outlined     -> StyledIconTheme.Type.Outlined
    ListTheme.Style.Soft         -> StyledIconTheme.Type.Soft
    ListTheme.Style.SoftOutlined -> StyledIconTheme.Type.SoftOutlined
    ListTheme.Style.Solid        -> StyledIconTheme.Type.Solid
    ListTheme.Style.White        -> StyledIconTheme.Type.White
}

private fun getFeaturedIconShape(shape: ListTheme.Shape): StyledIconTheme.Shape = when (shape) {
    ListTheme.Shape.Rounded  -> StyledIconTheme.Shape.Rounded
    ListTheme.Shape.Circular -> StyledIconTheme.Shape.Circular
}

@Composable
@ReadOnlyComposable
private fun getTextColor(state: ListTheme.State): ComposeColor = when (state) {
    ListTheme.State.Active                            -> PrelineTheme.colors.primary
    ListTheme.State.Default, ListTheme.State.Disabled -> PrelineTheme.colors.foreground
    ListTheme.State.Hover                             -> PrelineTheme.colors.mutedForeground1
    else                                              -> PrelineTheme.colors.foreground
}

@Composable
@ReadOnlyComposable
private fun getSeparatorColor(state: ListTheme.State): ComposeColor = when (state) {
    ListTheme.State.Active -> PrelineTheme.colors.primary
    else                   -> PrelineTheme.colors.mutedForeground
}
