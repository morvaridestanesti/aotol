package ir.aotol.app.ui.components.preline.avatar

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.graphics.Shape as ComposeShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.aotol.app.ui.components.preline.Palette
import ir.aotol.app.ui.components.preline.PrelineTheme
import ir.aotol.app.ui.components.preline.semiBold

data class AvatarTheme(
    val background: ComposeColor,
    val foreground: ComposeColor,
    val stroke: ComposeColor,
    val height: Dp,
    val statusSize: Dp,
    val textStyle: TextStyle,
    val shape: ComposeShape,
) {
    sealed interface Color {
        object Image : Color
        object Placeholder : Color
        object Dark : Color
        object Gray : Color
        object Green : Color
        object Blue : Color
        object Yellow : Color
        object Red : Color
        object Light : Color
        object White : Color
    }

    sealed interface Shape {
        object Rounded : Shape
        object Circular : Shape
    }

    sealed interface Size {
        object XS : Size
        object Small : Size
        object Default : Size
        object Large : Size
    }

    sealed interface Type {
        object Image : Type
        object Solid : Type
        object Soft : Type
        object White : Type
        object Outline : Type
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(type: AvatarTheme.Type, color: AvatarTheme.Color, size: AvatarTheme.Size, shape: AvatarTheme.Shape): AvatarTheme = AvatarTheme(
    background = getBackgroundColor(type, color),
    foreground = getForegroundColor(type, color),
    stroke = getStrokeColor(type, color),
    height = getHeight(size),
    statusSize = getStatusSize(size),
    textStyle = getTextStyle(size),
    shape = getShape(shape)
)

@Composable
@ReadOnlyComposable
private fun getBackgroundColor(type: AvatarTheme.Type, color: AvatarTheme.Color): ComposeColor {
    return when (type) {
        AvatarTheme.Type.Image   -> when (color) {
            AvatarTheme.Color.Image       -> PrelineTheme.colors.surface4
            AvatarTheme.Color.Placeholder -> PrelineTheme.colors.surface4
            else                          -> Palette.Transparent
        }

        AvatarTheme.Type.Solid   -> when (color) {
            AvatarTheme.Color.Image       -> PrelineTheme.colors.surface4
            AvatarTheme.Color.Placeholder -> PrelineTheme.colors.surface4
            AvatarTheme.Color.Dark        -> PrelineTheme.colors.secondary
            AvatarTheme.Color.Gray        -> PrelineTheme.colors.surface4
            AvatarTheme.Color.Green       -> PrelineTheme.colors.green.solid
            AvatarTheme.Color.Blue        -> PrelineTheme.colors.primary
            AvatarTheme.Color.Yellow      -> PrelineTheme.colors.yellow.solid
            AvatarTheme.Color.Red         -> PrelineTheme.colors.red.solid
            AvatarTheme.Color.Light       -> Palette.White
            AvatarTheme.Color.White       -> Palette.Transparent
        }

        AvatarTheme.Type.Soft    -> when (color) {
            AvatarTheme.Color.Image       -> PrelineTheme.colors.surface4
            AvatarTheme.Color.Placeholder -> PrelineTheme.colors.surface4
            AvatarTheme.Color.Dark        -> PrelineTheme.colors.surface
            AvatarTheme.Color.Gray        -> PrelineTheme.colors.muted
            AvatarTheme.Color.Green       -> PrelineTheme.colors.green.soft
            AvatarTheme.Color.Blue        -> PrelineTheme.colors.blue.soft
            AvatarTheme.Color.Yellow      -> PrelineTheme.colors.yellow.soft
            AvatarTheme.Color.Red         -> PrelineTheme.colors.red.soft
            AvatarTheme.Color.Light       -> Palette.White10
            AvatarTheme.Color.White       -> Palette.Transparent
        }

        AvatarTheme.Type.White   -> PrelineTheme.colors.layer
        AvatarTheme.Type.Outline -> when (color) {
            AvatarTheme.Color.Image       -> PrelineTheme.colors.surface4
            AvatarTheme.Color.Placeholder -> PrelineTheme.colors.surface4
            else                          -> Palette.Transparent
        }
    }
}

@Composable
@ReadOnlyComposable
private fun getForegroundColor(type: AvatarTheme.Type, color: AvatarTheme.Color): ComposeColor {
    return when (type) {
        AvatarTheme.Type.Image   -> when (color) {
            AvatarTheme.Color.Image       -> Palette.White
            AvatarTheme.Color.Placeholder -> Palette.White
            else                          -> Palette.Transparent
        }

        AvatarTheme.Type.Solid   -> when (color) {
            AvatarTheme.Color.Image       -> Palette.White
            AvatarTheme.Color.Placeholder -> Palette.White
            AvatarTheme.Color.Dark        -> PrelineTheme.colors.secondaryForeground
            AvatarTheme.Color.Gray        -> Palette.White
            AvatarTheme.Color.Green       -> Palette.White
            AvatarTheme.Color.Blue        -> PrelineTheme.colors.primaryForeground
            AvatarTheme.Color.Yellow      -> Palette.White
            AvatarTheme.Color.Red         -> Palette.White
            AvatarTheme.Color.Light       -> Palette.White
            AvatarTheme.Color.White       -> Palette.Transparent
        }

        AvatarTheme.Type.Soft    -> when (color) {
            AvatarTheme.Color.Image       -> Palette.White
            AvatarTheme.Color.Placeholder -> Palette.White
            AvatarTheme.Color.Dark        -> PrelineTheme.colors.surfaceForeground
            AvatarTheme.Color.Gray        -> PrelineTheme.colors.mutedForeground1
            AvatarTheme.Color.Green       -> PrelineTheme.colors.green.softForeground
            AvatarTheme.Color.Blue        -> PrelineTheme.colors.blue.softForeground
            AvatarTheme.Color.Yellow      -> PrelineTheme.colors.yellow.softForeground
            AvatarTheme.Color.Red         -> PrelineTheme.colors.red.softForeground
            AvatarTheme.Color.Light       -> Palette.White
            AvatarTheme.Color.White       -> Palette.Transparent
        }

        AvatarTheme.Type.White   -> PrelineTheme.colors.layerForeground
        AvatarTheme.Type.Outline -> when (color) {
            AvatarTheme.Color.Image       -> Palette.White
            AvatarTheme.Color.Placeholder -> Palette.White
            AvatarTheme.Color.Dark        -> PrelineTheme.colors.foreground
            AvatarTheme.Color.Gray        -> PrelineTheme.colors.mutedForeground1
            AvatarTheme.Color.Green       -> PrelineTheme.colors.green.solid
            AvatarTheme.Color.Blue        -> PrelineTheme.colors.primary
            AvatarTheme.Color.Yellow      -> PrelineTheme.colors.yellow.solid
            AvatarTheme.Color.Red         -> PrelineTheme.colors.red.solid
            AvatarTheme.Color.Light       -> Palette.White
            AvatarTheme.Color.White       -> Palette.Transparent
        }
    }
}

@Composable
@ReadOnlyComposable
private fun getStrokeColor(type: AvatarTheme.Type, color: AvatarTheme.Color): ComposeColor {
    return when (type) {
        AvatarTheme.Type.Outline -> getForegroundColor(type, color)
        else                     -> Palette.Transparent
    }
}

private fun getStatusSize(size: AvatarTheme.Size): Dp = when (size) {
    AvatarTheme.Size.XS      -> 8.dp
    AvatarTheme.Size.Small   -> 12.dp
    AvatarTheme.Size.Default -> 14.dp
    AvatarTheme.Size.Large   -> 16.dp
}

@Composable
@ReadOnlyComposable
private fun getTextStyle(size: AvatarTheme.Size): TextStyle = when (size) {
    AvatarTheme.Size.XS      -> PrelineTheme.typography.xs.semiBold
    AvatarTheme.Size.Small   -> PrelineTheme.typography.sm.semiBold
    AvatarTheme.Size.Default -> PrelineTheme.typography.base.semiBold
    AvatarTheme.Size.Large   -> PrelineTheme.typography.xl.semiBold
}

private fun getHeight(size: AvatarTheme.Size): Dp = when (size) {
    AvatarTheme.Size.XS      -> 24.dp
    AvatarTheme.Size.Small   -> 36.dp
    AvatarTheme.Size.Default -> 44.dp
    AvatarTheme.Size.Large   -> 60.dp
}

private fun getShape(shape: AvatarTheme.Shape): ComposeShape = when (shape) {
    AvatarTheme.Shape.Rounded  -> RoundedCornerShape(8.dp)
    AvatarTheme.Shape.Circular -> CircleShape
}
