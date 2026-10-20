package ir.ncis.preline.ui.components.avatar

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.graphics.Shape as ComposeShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.semiBold

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

    sealed interface Status {
        object Offline : Status
        object Online : Status
        object Away : Status
        object DoNotDisturb : Status
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
            AvatarTheme.Color.Placeholder -> PrelineTheme.colors.surface
            else                          -> Palette.Transparent
        }

        AvatarTheme.Type.Solid   -> when (color) {
            AvatarTheme.Color.Image       -> PrelineTheme.colors.surface4
            AvatarTheme.Color.Placeholder -> PrelineTheme.colors.surface
            AvatarTheme.Color.Dark        -> PrelineTheme.colors.secondary
            AvatarTheme.Color.Gray        -> PrelineTheme.colors.surface4
            AvatarTheme.Color.Green       -> PrelineTheme.colors.green.solid
            AvatarTheme.Color.Blue        -> PrelineTheme.colors.primary
            AvatarTheme.Color.Yellow      -> PrelineTheme.colors.yellow.solid
            AvatarTheme.Color.Red         -> PrelineTheme.colors.red.solid
            AvatarTheme.Color.Light       -> Palette.White
            AvatarTheme.Color.White       -> PrelineTheme.colors.layer
        }

        AvatarTheme.Type.Soft    -> when (color) {
            AvatarTheme.Color.Image       -> PrelineTheme.colors.surface4
            AvatarTheme.Color.Placeholder -> PrelineTheme.colors.surface
            AvatarTheme.Color.Dark        -> PrelineTheme.colors.surface
            AvatarTheme.Color.Gray        -> PrelineTheme.colors.muted
            AvatarTheme.Color.Green       -> PrelineTheme.colors.green.soft
            AvatarTheme.Color.Blue        -> PrelineTheme.colors.blue.soft
            AvatarTheme.Color.Yellow      -> PrelineTheme.colors.yellow.soft
            AvatarTheme.Color.Red         -> PrelineTheme.colors.red.soft
            AvatarTheme.Color.Light       -> Palette.White10
            AvatarTheme.Color.White       -> PrelineTheme.colors.layer
        }

        AvatarTheme.Type.White   -> PrelineTheme.colors.layer
        AvatarTheme.Type.Outline -> when (color) {
            AvatarTheme.Color.Image       -> PrelineTheme.colors.surface4
            AvatarTheme.Color.Placeholder -> PrelineTheme.colors.surface
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
            AvatarTheme.Color.Placeholder -> PrelineTheme.colors.mutedForeground
            else                          -> Palette.Transparent
        }

        AvatarTheme.Type.Solid   -> when (color) {
            AvatarTheme.Color.Image       -> Palette.White
            AvatarTheme.Color.Placeholder -> PrelineTheme.colors.mutedForeground
            AvatarTheme.Color.Dark        -> PrelineTheme.colors.secondaryForeground
            AvatarTheme.Color.Gray        -> Palette.White
            AvatarTheme.Color.Green       -> Palette.White
            AvatarTheme.Color.Blue        -> PrelineTheme.colors.primaryForeground
            AvatarTheme.Color.Yellow      -> Palette.White
            AvatarTheme.Color.Red         -> Palette.White
            AvatarTheme.Color.Light       -> Palette.Gray.Gray800
            AvatarTheme.Color.White       -> PrelineTheme.colors.layerForeground
        }

        AvatarTheme.Type.Soft    -> when (color) {
            AvatarTheme.Color.Image       -> Palette.White
            AvatarTheme.Color.Placeholder -> PrelineTheme.colors.mutedForeground
            AvatarTheme.Color.Dark        -> PrelineTheme.colors.surfaceForeground
            AvatarTheme.Color.Gray        -> PrelineTheme.colors.mutedForeground1
            AvatarTheme.Color.Green       -> PrelineTheme.colors.green.softForeground
            AvatarTheme.Color.Blue        -> PrelineTheme.colors.blue.softForeground
            AvatarTheme.Color.Yellow      -> PrelineTheme.colors.yellow.softForeground
            AvatarTheme.Color.Red         -> PrelineTheme.colors.red.softForeground
            AvatarTheme.Color.Light       -> Palette.White
            AvatarTheme.Color.White       -> PrelineTheme.colors.layerForeground
        }

        AvatarTheme.Type.White   -> PrelineTheme.colors.layerForeground
        AvatarTheme.Type.Outline -> when (color) {
            AvatarTheme.Color.Image       -> Palette.White
            AvatarTheme.Color.Placeholder -> PrelineTheme.colors.mutedForeground
            AvatarTheme.Color.Dark        -> PrelineTheme.colors.foreground
            AvatarTheme.Color.Gray        -> PrelineTheme.colors.mutedForeground1
            AvatarTheme.Color.Green       -> PrelineTheme.colors.green.solid
            AvatarTheme.Color.Blue        -> PrelineTheme.colors.primary
            AvatarTheme.Color.Yellow      -> PrelineTheme.colors.yellow.solid
            AvatarTheme.Color.Red         -> PrelineTheme.colors.red.solid
            AvatarTheme.Color.Light       -> Palette.White
            AvatarTheme.Color.White       -> PrelineTheme.colors.layerForeground
        }
    }
}

@Composable
@ReadOnlyComposable
private fun getStrokeColor(type: AvatarTheme.Type, color: AvatarTheme.Color): ComposeColor {
    return when (type) {
        AvatarTheme.Type.White   -> PrelineTheme.colors.layerBorder
        AvatarTheme.Type.Outline -> if (color == AvatarTheme.Color.White) PrelineTheme.colors.layerBorder else getForegroundColor(type, color)
        else                     -> Palette.Transparent
    }
}

@Composable
@ReadOnlyComposable
internal fun getStatusColor(status: AvatarTheme.Status): ComposeColor = when (status) {
    AvatarTheme.Status.Offline      -> PrelineTheme.colors.surface3
    AvatarTheme.Status.Online       -> Palette.Teal.Teal400
    AvatarTheme.Status.Away         -> Palette.Yellow.Yellow400
    AvatarTheme.Status.DoNotDisturb -> Palette.Red.Red400
}

private fun getStatusSize(size: AvatarTheme.Size): Dp = when (size) {
    AvatarTheme.Size.XS      -> 6.dp
    AvatarTheme.Size.Small   -> 10.dp
    AvatarTheme.Size.Default -> 12.dp
    AvatarTheme.Size.Large   -> 14.dp
}

@Composable
@ReadOnlyComposable
private fun getTextStyle(size: AvatarTheme.Size): TextStyle = when (size) {
    AvatarTheme.Size.XS      -> PrelineTheme.typography.xs.semiBold
    AvatarTheme.Size.Small   -> PrelineTheme.typography.sm.semiBold
    AvatarTheme.Size.Default -> PrelineTheme.typography.base.semiBold
    AvatarTheme.Size.Large   -> PrelineTheme.typography.lg.semiBold
}

private fun getHeight(size: AvatarTheme.Size): Dp = when (size) {
    AvatarTheme.Size.XS      -> 24.dp
    AvatarTheme.Size.Small   -> 38.dp
    AvatarTheme.Size.Default -> 44.dp
    AvatarTheme.Size.Large   -> 62.dp
}

private fun getShape(shape: AvatarTheme.Shape): ComposeShape = when (shape) {
    AvatarTheme.Shape.Rounded  -> RoundedCornerShape(8.dp)
    AvatarTheme.Shape.Circular -> CircleShape
}
