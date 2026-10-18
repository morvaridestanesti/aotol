package ir.aotol.app.ui.components.preline.styled_icon

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.graphics.Shape as ComposeShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.aotol.app.ui.components.preline.Palette
import ir.aotol.app.ui.components.preline.PrelineTheme

data class StyledIconTheme(
    val containerColor: ComposeColor,
    val contentColor: ComposeColor,
    val foregroundColor: ComposeColor,
    val strokeColor: ComposeColor,
    val strokeWidth: Dp,
    val height: Dp,
    val containerPadding: Dp,
    val contentPadding: Dp,
    val shape: ComposeShape,
) {
    sealed interface Color {
        object Dark : Color
        object Gray : Color
        object Green : Color
        object Blue : Color
        object Red : Color
        object Yellow : Color
        object Light : Color
        object White : Color
    }

    sealed interface Shape {
        object Rounded : Shape
        object Circular : Shape
    }

    sealed interface Size {
        object Large : Size
        object Default : Size
        object Small : Size
        object XS : Size
    }

    sealed interface Type {
        object Base : Type
        object Solid : Type
        object Soft : Type
        object SoftOutlined : Type
        object Outlined : Type
        object White : Type
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(type: StyledIconTheme.Type, color: StyledIconTheme.Color, size: StyledIconTheme.Size, shape: StyledIconTheme.Shape): StyledIconTheme = StyledIconTheme(
    containerColor = getContainerColor(type, color),
    contentColor = getContentColor(type, color),
    foregroundColor = getForegroundCol(type, color),
    strokeColor = getStrokeCol(type, color),
    strokeWidth = if (type == StyledIconTheme.Type.SoftOutlined) 7.dp else 1.dp,
    height = getHeight(size),
    containerPadding = getContainerPadding(size),
    contentPadding = getContentPadding(size),
    shape = getShape(shape, size)
)

@Composable
@ReadOnlyComposable
private fun getContainerColor(type: StyledIconTheme.Type, color: StyledIconTheme.Color): ComposeColor {
    return when (type) {
        StyledIconTheme.Type.Base         -> when (color) {
            StyledIconTheme.Color.White -> PrelineTheme.colors.layer
            else                        -> Palette.Transparent
        }

        StyledIconTheme.Type.Solid        -> when (color) {
            StyledIconTheme.Color.Dark   -> PrelineTheme.colors.secondary
            StyledIconTheme.Color.Gray   -> PrelineTheme.colors.surface4
            StyledIconTheme.Color.Green  -> PrelineTheme.colors.green.solid
            StyledIconTheme.Color.Blue   -> PrelineTheme.colors.primary
            StyledIconTheme.Color.Red    -> PrelineTheme.colors.red.solid
            StyledIconTheme.Color.Yellow -> PrelineTheme.colors.yellow.solid
            StyledIconTheme.Color.Light  -> Palette.White
            StyledIconTheme.Color.White  -> PrelineTheme.colors.layer
            else                         -> Palette.Transparent
        }

        StyledIconTheme.Type.Soft         -> when (color) {
            StyledIconTheme.Color.Dark   -> PrelineTheme.colors.surface
            StyledIconTheme.Color.Gray   -> PrelineTheme.colors.muted
            StyledIconTheme.Color.Green  -> PrelineTheme.colors.green.soft
            StyledIconTheme.Color.Blue   -> PrelineTheme.colors.blue.soft
            StyledIconTheme.Color.Red    -> PrelineTheme.colors.red.soft
            StyledIconTheme.Color.Yellow -> PrelineTheme.colors.yellow.soft
            StyledIconTheme.Color.Light  -> Palette.White10
            StyledIconTheme.Color.White  -> PrelineTheme.colors.layer
            else                         -> Palette.Transparent
        }

        StyledIconTheme.Type.SoftOutlined -> when (color) {
            StyledIconTheme.Color.Dark   -> PrelineTheme.colors.surface2
            StyledIconTheme.Color.Gray   -> PrelineTheme.colors.surface2
            StyledIconTheme.Color.Green  -> PrelineTheme.colors.green.soft
            StyledIconTheme.Color.Blue   -> PrelineTheme.colors.blue.soft
            StyledIconTheme.Color.Red    -> PrelineTheme.colors.red.soft
            StyledIconTheme.Color.Yellow -> PrelineTheme.colors.yellow.soft
            StyledIconTheme.Color.Light  -> Palette.White10
            StyledIconTheme.Color.White  -> PrelineTheme.colors.layer
            else                         -> Palette.Transparent
        }

        StyledIconTheme.Type.Outlined     -> when (color) {
            StyledIconTheme.Color.White -> PrelineTheme.colors.layer
            else                        -> Palette.Transparent
        }

        StyledIconTheme.Type.White -> PrelineTheme.colors.layer
        else                       -> Palette.Transparent
    }
}

@Composable
@ReadOnlyComposable
private fun getContentColor(type: StyledIconTheme.Type, color: StyledIconTheme.Color): ComposeColor {
    return when (type) {
        StyledIconTheme.Type.Base         -> when (color) {
            StyledIconTheme.Color.White -> PrelineTheme.colors.layer
            else                        -> Palette.Transparent
        }

        StyledIconTheme.Type.Solid        -> when (color) {
            StyledIconTheme.Color.Dark   -> PrelineTheme.colors.secondary
            StyledIconTheme.Color.Gray   -> PrelineTheme.colors.surface4
            StyledIconTheme.Color.Green  -> PrelineTheme.colors.green.solid
            StyledIconTheme.Color.Blue   -> PrelineTheme.colors.primary
            StyledIconTheme.Color.Red    -> PrelineTheme.colors.red.solid
            StyledIconTheme.Color.Yellow -> PrelineTheme.colors.yellow.solid
            StyledIconTheme.Color.Light  -> Palette.White
            StyledIconTheme.Color.White  -> PrelineTheme.colors.layer
            else                         -> Palette.Transparent
        }

        StyledIconTheme.Type.Soft         -> when (color) {
            StyledIconTheme.Color.Dark   -> PrelineTheme.colors.surface
            StyledIconTheme.Color.Gray   -> PrelineTheme.colors.muted
            StyledIconTheme.Color.Green  -> PrelineTheme.colors.green.soft
            StyledIconTheme.Color.Blue   -> PrelineTheme.colors.blue.soft
            StyledIconTheme.Color.Red    -> PrelineTheme.colors.red.soft
            StyledIconTheme.Color.Yellow -> PrelineTheme.colors.yellow.soft
            StyledIconTheme.Color.Light  -> Palette.White10
            StyledIconTheme.Color.White  -> PrelineTheme.colors.layer
            else                         -> Palette.Transparent
        }

        StyledIconTheme.Type.SoftOutlined -> when (color) {
            StyledIconTheme.Color.Dark   -> PrelineTheme.colors.surface1
            StyledIconTheme.Color.Gray   -> PrelineTheme.colors.surface
            StyledIconTheme.Color.Green  -> PrelineTheme.colors.green.softHover
            StyledIconTheme.Color.Blue   -> PrelineTheme.colors.blue.softHover
            StyledIconTheme.Color.Red    -> PrelineTheme.colors.red.softHover
            StyledIconTheme.Color.Yellow -> PrelineTheme.colors.yellow.softHover
            StyledIconTheme.Color.Light  -> Palette.Transparent
            StyledIconTheme.Color.White  -> PrelineTheme.colors.layer
            else                         -> Palette.Transparent
        }

        StyledIconTheme.Type.Outlined     -> when (color) {
            StyledIconTheme.Color.White -> PrelineTheme.colors.layer
            else                        -> Palette.Transparent
        }

        StyledIconTheme.Type.White -> PrelineTheme.colors.layer
        else                       -> Palette.Transparent
    }
}

@Composable
@ReadOnlyComposable
private fun getForegroundCol(type: StyledIconTheme.Type, color: StyledIconTheme.Color): ComposeColor {
    return when (type) {
        StyledIconTheme.Type.Base         -> when (color) {
            StyledIconTheme.Color.Dark   -> PrelineTheme.colors.foreground
            StyledIconTheme.Color.Gray   -> PrelineTheme.colors.mutedForeground1
            StyledIconTheme.Color.Green  -> PrelineTheme.colors.green.solid
            StyledIconTheme.Color.Blue   -> PrelineTheme.colors.primary
            StyledIconTheme.Color.Red    -> PrelineTheme.colors.red.solid
            StyledIconTheme.Color.Yellow -> PrelineTheme.colors.yellow.solid
            StyledIconTheme.Color.Light  -> Palette.White
            StyledIconTheme.Color.White  -> PrelineTheme.colors.layerForeground
            else                         -> Palette.Transparent
        }

        StyledIconTheme.Type.Solid        -> when (color) {
            StyledIconTheme.Color.Dark   -> PrelineTheme.colors.secondaryForeground
            StyledIconTheme.Color.Gray   -> Palette.White
            StyledIconTheme.Color.Green  -> Palette.White
            StyledIconTheme.Color.Blue   -> PrelineTheme.colors.primaryForeground
            StyledIconTheme.Color.Red    -> Palette.White
            StyledIconTheme.Color.Yellow -> Palette.White
            StyledIconTheme.Color.Light  -> Palette.Gray.Gray800
            StyledIconTheme.Color.White  -> PrelineTheme.colors.layerForeground
            else                         -> Palette.Transparent
        }

        StyledIconTheme.Type.Soft         -> when (color) {
            StyledIconTheme.Color.Dark   -> PrelineTheme.colors.surfaceForeground
            StyledIconTheme.Color.Gray   -> PrelineTheme.colors.foreground
            StyledIconTheme.Color.Green  -> PrelineTheme.colors.green.softForeground
            StyledIconTheme.Color.Blue   -> PrelineTheme.colors.blue.softForeground
            StyledIconTheme.Color.Red    -> PrelineTheme.colors.red.softForeground
            StyledIconTheme.Color.Yellow -> PrelineTheme.colors.yellow.softForeground
            StyledIconTheme.Color.Light  -> Palette.White
            StyledIconTheme.Color.White  -> PrelineTheme.colors.layerForeground
            else                         -> Palette.Transparent
        }

        StyledIconTheme.Type.SoftOutlined -> when (color) {
            StyledIconTheme.Color.Dark   -> PrelineTheme.colors.surfaceForeground
            StyledIconTheme.Color.Gray   -> PrelineTheme.colors.mutedForeground1
            StyledIconTheme.Color.Green  -> PrelineTheme.colors.green.softForeground
            StyledIconTheme.Color.Blue   -> PrelineTheme.colors.blue.softForeground
            StyledIconTheme.Color.Red    -> PrelineTheme.colors.red.softForeground
            StyledIconTheme.Color.Yellow -> PrelineTheme.colors.yellow.softForeground
            StyledIconTheme.Color.Light  -> Palette.White
            StyledIconTheme.Color.White  -> PrelineTheme.colors.layerForeground
            else                         -> Palette.Transparent
        }

        StyledIconTheme.Type.Outlined     -> when (color) {
            StyledIconTheme.Color.Dark   -> PrelineTheme.colors.foreground
            StyledIconTheme.Color.Gray   -> PrelineTheme.colors.mutedForeground1
            StyledIconTheme.Color.Green  -> PrelineTheme.colors.green.solid
            StyledIconTheme.Color.Blue   -> PrelineTheme.colors.primary
            StyledIconTheme.Color.Red    -> PrelineTheme.colors.red.solid
            StyledIconTheme.Color.Yellow -> PrelineTheme.colors.yellow.solid
            StyledIconTheme.Color.Light  -> Palette.White
            StyledIconTheme.Color.White  -> PrelineTheme.colors.layerForeground
            else                         -> Palette.Transparent
        }

        StyledIconTheme.Type.White -> PrelineTheme.colors.layerForeground
        else                       -> Palette.Transparent
    }
}

@Composable
@ReadOnlyComposable
private fun getStrokeCol(type: StyledIconTheme.Type, color: StyledIconTheme.Color): ComposeColor {
    return when (type) {
        StyledIconTheme.Type.Outlined     -> when (color) {
            StyledIconTheme.Color.Dark   -> PrelineTheme.colors.borderLine8
            StyledIconTheme.Color.Gray   -> PrelineTheme.colors.borderLine5
            StyledIconTheme.Color.Green  -> PrelineTheme.colors.green.solid
            StyledIconTheme.Color.Blue   -> PrelineTheme.colors.primary
            StyledIconTheme.Color.Red    -> PrelineTheme.colors.red.solid
            StyledIconTheme.Color.Yellow -> PrelineTheme.colors.yellow.solid
            StyledIconTheme.Color.Light  -> Palette.White
            StyledIconTheme.Color.White  -> PrelineTheme.colors.layerBorder
            else                         -> Palette.Transparent
        }

        StyledIconTheme.Type.SoftOutlined -> when (color) {
            StyledIconTheme.Color.Light -> Palette.White10
            StyledIconTheme.Color.White -> PrelineTheme.colors.layerBorder
            else                        -> Palette.Transparent
        }

        StyledIconTheme.Type.White -> PrelineTheme.colors.layerBorder
        else                       -> Palette.Transparent
    }
}

private fun getContainerPadding(size: StyledIconTheme.Size): Dp = when (size) {
    StyledIconTheme.Size.Large                          -> 8.dp
    StyledIconTheme.Size.Default                        -> 6.dp
    StyledIconTheme.Size.Small, StyledIconTheme.Size.XS -> 4.dp
}

private fun getContentPadding(size: StyledIconTheme.Size): Dp = when (size) {
    StyledIconTheme.Size.Large   -> 8.dp
    StyledIconTheme.Size.Default -> 4.dp
    StyledIconTheme.Size.Small   -> 2.dp
    StyledIconTheme.Size.XS      -> 0.dp
}

private fun getHeight(size: StyledIconTheme.Size): Dp = when (size) {
    StyledIconTheme.Size.Large   -> 60.dp
    StyledIconTheme.Size.Default -> 44.dp
    StyledIconTheme.Size.Small   -> 36.dp
    StyledIconTheme.Size.XS      -> 24.dp
}

private fun getShape(shape: StyledIconTheme.Shape, size: StyledIconTheme.Size): ComposeShape = when (shape) {
    StyledIconTheme.Shape.Rounded  -> RoundedCornerShape(
        when (size) {
            StyledIconTheme.Size.Large, StyledIconTheme.Size.Default, StyledIconTheme.Size.Small -> 8.dp
            StyledIconTheme.Size.XS                                                              -> 6.dp
        }
    )

    StyledIconTheme.Shape.Circular -> CircleShape
}
