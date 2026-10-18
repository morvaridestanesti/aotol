package ir.aotol.app.ui.components.preline.badge

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

data class BadgeTheme(
    val background: ComposeColor,
    val foregroundColor: ComposeColor,
    val stroke: ComposeColor,
    val closeIconBackground: ComposeColor,
    val closeIconForegroundColor: ComposeColor,
    val height: Dp,
    val containerHorizontalPadding: Dp,
    val containerVerticalPadding: Dp,
    val avatarSize: Dp,
    val leadingIconSize: Dp,
    val trailingIconSize: Dp,
    val closeIconSize: Dp,
    val shape: ComposeShape,
) {
    sealed interface Color {
        object White : Color
        object Dark : Color
        object Gray : Color
        object Green : Color
        object Blue : Color
        object Red : Color
        object Yellow : Color
        object Light : Color
    }

    sealed interface Shape {
        object Rounded : Shape
        object Pilled : Shape
    }

    sealed interface Size {
        object Large : Size
        object Default : Size
        object Small : Size
    }

    sealed interface Type {
        object White : Type
        object Outlined : Type
        object Soft : Type
        object Solid : Type
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(type: BadgeTheme.Type, color: BadgeTheme.Color, size: BadgeTheme.Size, shape: BadgeTheme.Shape): BadgeTheme = BadgeTheme(
    background = getBackgroundColor(type, color),
    foregroundColor = getForegroundColor(type, color),
    stroke = getStrokeColor(type, color),
    closeIconBackground = getCloseIconBackgroundColor(type, color),
    closeIconForegroundColor = getCloseIconForegroundColor(type, color),
    height = getHeight(size),
    containerHorizontalPadding = getContainerPadding(size, true),
    containerVerticalPadding = getContainerPadding(size, false),
    avatarSize = getAvatarSize(size),
    leadingIconSize = getLeadingIconSize(size),
    trailingIconSize = getTrailingIconSize(size),
    closeIconSize = getCloseIconSize(size),
    shape = getShape(shape)
)

@Composable
@ReadOnlyComposable
private fun getBackgroundColor(type: BadgeTheme.Type, color: BadgeTheme.Color): ComposeColor {
    return when (type) {
        BadgeTheme.Type.White    -> PrelineTheme.colors.layer

        BadgeTheme.Type.Outlined -> when (color) {
            BadgeTheme.Color.White  -> PrelineTheme.colors.layer
            BadgeTheme.Color.Dark   -> Palette.Transparent
            BadgeTheme.Color.Gray   -> Palette.Transparent
            BadgeTheme.Color.Green  -> Palette.Transparent
            BadgeTheme.Color.Blue   -> Palette.Transparent
            BadgeTheme.Color.Red    -> Palette.Transparent
            BadgeTheme.Color.Yellow -> Palette.Transparent
            BadgeTheme.Color.Light  -> Palette.Transparent
        }

        BadgeTheme.Type.Soft     -> when (color) {
            BadgeTheme.Color.White  -> PrelineTheme.colors.layer
            BadgeTheme.Color.Dark   -> PrelineTheme.colors.surface
            BadgeTheme.Color.Gray   -> PrelineTheme.colors.muted
            BadgeTheme.Color.Green  -> PrelineTheme.colors.green.soft
            BadgeTheme.Color.Blue   -> PrelineTheme.colors.blue.soft
            BadgeTheme.Color.Red    -> PrelineTheme.colors.red.soft
            BadgeTheme.Color.Yellow -> PrelineTheme.colors.yellow.soft
            BadgeTheme.Color.Light  -> Palette.White10
        }

        BadgeTheme.Type.Solid    -> when (color) {
            BadgeTheme.Color.White  -> PrelineTheme.colors.layer
            BadgeTheme.Color.Dark   -> PrelineTheme.colors.secondary
            BadgeTheme.Color.Gray   -> PrelineTheme.colors.surface4
            BadgeTheme.Color.Green  -> PrelineTheme.colors.green.solid
            BadgeTheme.Color.Blue   -> PrelineTheme.colors.primary
            BadgeTheme.Color.Red    -> PrelineTheme.colors.red.solid
            BadgeTheme.Color.Yellow -> PrelineTheme.colors.yellow.solid
            BadgeTheme.Color.Light  -> Palette.White
        }
    }
}

@Composable
@ReadOnlyComposable
private fun getForegroundColor(type: BadgeTheme.Type, color: BadgeTheme.Color): ComposeColor {
    return when (type) {
        BadgeTheme.Type.White    -> PrelineTheme.colors.layerForeground

        BadgeTheme.Type.Outlined -> when (color) {
            BadgeTheme.Color.White  -> PrelineTheme.colors.layerForeground
            BadgeTheme.Color.Dark   -> PrelineTheme.colors.foreground
            BadgeTheme.Color.Gray   -> PrelineTheme.colors.mutedForeground1
            BadgeTheme.Color.Green  -> PrelineTheme.colors.green.solid
            BadgeTheme.Color.Blue   -> PrelineTheme.colors.primary
            BadgeTheme.Color.Red    -> PrelineTheme.colors.red.solid
            BadgeTheme.Color.Yellow -> PrelineTheme.colors.yellow.solid
            BadgeTheme.Color.Light  -> Palette.White
        }

        BadgeTheme.Type.Soft     -> when (color) {
            BadgeTheme.Color.White  -> PrelineTheme.colors.layerForeground
            BadgeTheme.Color.Dark   -> PrelineTheme.colors.surfaceForeground
            BadgeTheme.Color.Gray   -> PrelineTheme.colors.mutedForeground1
            BadgeTheme.Color.Green  -> PrelineTheme.colors.green.softForeground
            BadgeTheme.Color.Blue   -> PrelineTheme.colors.blue.softForeground
            BadgeTheme.Color.Red    -> PrelineTheme.colors.red.softForeground
            BadgeTheme.Color.Yellow -> PrelineTheme.colors.yellow.softForeground
            BadgeTheme.Color.Light  -> Palette.White
        }

        BadgeTheme.Type.Solid    -> when (color) {
            BadgeTheme.Color.White  -> PrelineTheme.colors.layerForeground
            BadgeTheme.Color.Dark   -> PrelineTheme.colors.secondaryForeground
            BadgeTheme.Color.Gray   -> Palette.White
            BadgeTheme.Color.Green  -> Palette.White
            BadgeTheme.Color.Blue   -> PrelineTheme.colors.primaryForeground
            BadgeTheme.Color.Red    -> Palette.White
            BadgeTheme.Color.Yellow -> Palette.White
            BadgeTheme.Color.Light  -> Palette.Gray.Gray800
        }
    }
}

@Composable
@ReadOnlyComposable
private fun getStrokeColor(type: BadgeTheme.Type, color: BadgeTheme.Color): ComposeColor {
    return when (type) {
        BadgeTheme.Type.White    -> PrelineTheme.colors.layerBorder

        BadgeTheme.Type.Outlined -> when (color) {
            BadgeTheme.Color.White  -> PrelineTheme.colors.layerBorder
            BadgeTheme.Color.Dark   -> PrelineTheme.colors.borderLine8
            BadgeTheme.Color.Gray   -> PrelineTheme.colors.borderLine5
            BadgeTheme.Color.Green  -> PrelineTheme.colors.green.solid
            BadgeTheme.Color.Blue   -> PrelineTheme.colors.primary
            BadgeTheme.Color.Red    -> PrelineTheme.colors.red.solid
            BadgeTheme.Color.Yellow -> PrelineTheme.colors.yellow.solid
            BadgeTheme.Color.Light  -> Palette.White20
        }

        BadgeTheme.Type.Soft     -> when (color) {
            BadgeTheme.Color.White  -> PrelineTheme.colors.layerBorder
            BadgeTheme.Color.Dark   -> Palette.Transparent
            BadgeTheme.Color.Gray   -> Palette.Transparent
            BadgeTheme.Color.Green  -> Palette.Transparent
            BadgeTheme.Color.Blue   -> Palette.Transparent
            BadgeTheme.Color.Red    -> Palette.Transparent
            BadgeTheme.Color.Yellow -> Palette.Transparent
            BadgeTheme.Color.Light  -> Palette.Transparent
        }

        BadgeTheme.Type.Solid    -> when (color) {
            BadgeTheme.Color.White  -> PrelineTheme.colors.layerBorder
            BadgeTheme.Color.Dark   -> Palette.Transparent
            BadgeTheme.Color.Gray   -> Palette.Transparent
            BadgeTheme.Color.Green  -> Palette.Transparent
            BadgeTheme.Color.Blue   -> Palette.Transparent
            BadgeTheme.Color.Red    -> Palette.Transparent
            BadgeTheme.Color.Yellow -> Palette.Transparent
            BadgeTheme.Color.Light  -> Palette.Transparent
        }
    }
}

@Composable
@ReadOnlyComposable
private fun getCloseIconBackgroundColor(type: BadgeTheme.Type, color: BadgeTheme.Color): ComposeColor {
    return when (type) {
        BadgeTheme.Type.White    -> PrelineTheme.colors.surface1

        BadgeTheme.Type.Outlined -> when (color) {
            BadgeTheme.Color.White  -> PrelineTheme.colors.surface1
            BadgeTheme.Color.Dark   -> PrelineTheme.colors.surface1
            BadgeTheme.Color.Gray   -> PrelineTheme.colors.surface1
            BadgeTheme.Color.Green  -> PrelineTheme.colors.green.soft
            BadgeTheme.Color.Blue   -> PrelineTheme.colors.blue.soft
            BadgeTheme.Color.Red    -> PrelineTheme.colors.red.soft
            BadgeTheme.Color.Yellow -> PrelineTheme.colors.yellow.soft
            BadgeTheme.Color.Light  -> Palette.White10
        }

        BadgeTheme.Type.Soft     -> when (color) {
            BadgeTheme.Color.White  -> PrelineTheme.colors.surface1
            BadgeTheme.Color.Dark   -> PrelineTheme.colors.surface
            BadgeTheme.Color.Gray   -> PrelineTheme.colors.surface1
            BadgeTheme.Color.Green  -> PrelineTheme.colors.green.softHover
            BadgeTheme.Color.Blue   -> PrelineTheme.colors.blue.softHover
            BadgeTheme.Color.Red    -> PrelineTheme.colors.red.softHover
            BadgeTheme.Color.Yellow -> PrelineTheme.colors.yellow.softHover
            BadgeTheme.Color.Light  -> Palette.White10
        }

        BadgeTheme.Type.Solid    -> when (color) {
            BadgeTheme.Color.White  -> PrelineTheme.colors.surface1
            BadgeTheme.Color.Dark   -> PrelineTheme.colors.background
            BadgeTheme.Color.Gray   -> PrelineTheme.colors.surface3
            BadgeTheme.Color.Green  -> Palette.White20
            BadgeTheme.Color.Blue   -> Palette.White20
            BadgeTheme.Color.Red    -> Palette.White20
            BadgeTheme.Color.Yellow -> Palette.White20
            BadgeTheme.Color.Light  -> Palette.Gray.Gray800
        }
    }
}

@Composable
@ReadOnlyComposable
private fun getCloseIconForegroundColor(type: BadgeTheme.Type, color: BadgeTheme.Color): ComposeColor {
    return when (type) {
        BadgeTheme.Type.White    -> PrelineTheme.colors.surfaceForeground

        BadgeTheme.Type.Outlined -> when (color) {
            BadgeTheme.Color.White  -> PrelineTheme.colors.surfaceForeground
            BadgeTheme.Color.Dark   -> PrelineTheme.colors.surfaceForeground
            BadgeTheme.Color.Gray   -> PrelineTheme.colors.surfaceForeground
            BadgeTheme.Color.Green  -> PrelineTheme.colors.green.softForeground
            BadgeTheme.Color.Blue   -> PrelineTheme.colors.blue.softForeground
            BadgeTheme.Color.Red    -> PrelineTheme.colors.red.softForeground
            BadgeTheme.Color.Yellow -> PrelineTheme.colors.yellow.softForeground
            BadgeTheme.Color.Light  -> Palette.White
        }

        BadgeTheme.Type.Soft     -> when (color) {
            BadgeTheme.Color.White  -> PrelineTheme.colors.surfaceForeground
            BadgeTheme.Color.Dark   -> PrelineTheme.colors.surfaceForeground
            BadgeTheme.Color.Gray   -> PrelineTheme.colors.surfaceForeground
            BadgeTheme.Color.Green  -> PrelineTheme.colors.green.softForeground
            BadgeTheme.Color.Blue   -> PrelineTheme.colors.blue.softForeground
            BadgeTheme.Color.Red    -> PrelineTheme.colors.red.softForeground
            BadgeTheme.Color.Yellow -> PrelineTheme.colors.yellow.softForeground
            BadgeTheme.Color.Light  -> Palette.White
        }

        BadgeTheme.Type.Solid    -> when (color) {
            BadgeTheme.Color.White  -> PrelineTheme.colors.surfaceForeground
            BadgeTheme.Color.Dark   -> PrelineTheme.colors.foreground
            BadgeTheme.Color.Gray   -> Palette.White
            BadgeTheme.Color.Green  -> Palette.White
            BadgeTheme.Color.Blue   -> PrelineTheme.colors.primaryForeground
            BadgeTheme.Color.Red    -> Palette.White
            BadgeTheme.Color.Yellow -> Palette.White
            BadgeTheme.Color.Light  -> Palette.White
        }
    }
}

private fun getHeight(size: BadgeTheme.Size): Dp = when (size) {
    BadgeTheme.Size.Large   -> 38.dp
    BadgeTheme.Size.Default -> 28.dp
    BadgeTheme.Size.Small   -> 18.dp
}

private fun getContainerPadding(size: BadgeTheme.Size, horizontal: Boolean): Dp = when (size) {
    BadgeTheme.Size.Large   -> if (horizontal) 16.dp else 12.dp
    BadgeTheme.Size.Default -> if (horizontal) 10.dp else 6.dp
    BadgeTheme.Size.Small   -> if (horizontal) 6.dp else 2.dp
}

private fun getAvatarSize(size: BadgeTheme.Size): Dp = when (size) {
    BadgeTheme.Size.Large, BadgeTheme.Size.Default -> 16.dp
    BadgeTheme.Size.Small                          -> 14.dp
}

private fun getLeadingIconSize(size: BadgeTheme.Size): Dp = when (size) {
    BadgeTheme.Size.Large   -> 14.dp
    BadgeTheme.Size.Default -> 12.dp
    BadgeTheme.Size.Small   -> 10.dp
}

private fun getTrailingIconSize(size: BadgeTheme.Size): Dp = when (size) {
    BadgeTheme.Size.Large   -> 14.dp
    BadgeTheme.Size.Default -> 12.dp
    BadgeTheme.Size.Small   -> 10.dp
}

private fun getCloseIconSize(size: BadgeTheme.Size): Dp = when (size) {
    BadgeTheme.Size.Large   -> 16.dp
    BadgeTheme.Size.Default -> 14.dp
    BadgeTheme.Size.Small   -> 12.dp
}

private fun getShape(shape: BadgeTheme.Shape): ComposeShape = when (shape) {
    BadgeTheme.Shape.Rounded -> RoundedCornerShape(6.dp)
    BadgeTheme.Shape.Pilled  -> CircleShape
}
