package ir.aotol.app.ui.components.preline.badge

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.aotol.app.ui.components.preline.Colors
import ir.aotol.app.ui.components.preline.badge.BadgeTheme.Color
import ir.aotol.app.ui.components.preline.badge.BadgeTheme.Shape
import ir.aotol.app.ui.components.preline.badge.BadgeTheme.Size
import ir.aotol.app.ui.components.preline.badge.BadgeTheme.Type
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.graphics.Shape as ComposeShape

data class BadgeTheme(
    val background: ComposeColor,
    val foregroundColor: ComposeColor,
    val stroke: ComposeColor,
    val closeIconBackground: ComposeColor,
    val closeIconForegroundColor: ComposeColor,
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

internal fun getTheme(type: Type, color: Color, size: Size, shape: Shape): BadgeTheme = BadgeTheme(
    background = getBackgroundColor(type, color),
    foregroundColor = getForegroundColor(type, color),
    stroke = getStrokeColor(type, color),
    closeIconBackground = getCloseIconBackgroundColor(type, color),
    closeIconForegroundColor = getCloseIconForegroundColor(type, color),
    containerHorizontalPadding = getContainerPadding(size, true),
    containerVerticalPadding = getContainerPadding(size, false),
    avatarSize = getAvatarSize(size),
    leadingIconSize = getLeadingIconSize(size),
    trailingIconSize = getTrailingIconSize(size),
    closeIconSize = getCloseIconSize(size),
    shape = getShape(shape)
)

private fun getBackgroundColor(type: Type, color: Color): ComposeColor {
    return when (type) {
        Type.White    -> Colors.Theme.Layer.Layer

        Type.Outlined -> when (color) {
            Color.White  -> Colors.Theme.Layer.Layer
            Color.Dark   -> Colors.Transparent
            Color.Gray   -> Colors.Transparent
            Color.Green  -> Colors.Transparent
            Color.Blue   -> Colors.Transparent
            Color.Red    -> Colors.Transparent
            Color.Yellow -> Colors.Transparent
            Color.Light  -> Colors.Transparent
        }

        Type.Soft     -> when (color) {
            Color.White  -> Colors.Theme.Layer.Layer
            Color.Dark   -> Colors.Theme.Surface.Surface
            Color.Gray   -> Colors.Theme.Muted.Muted
            Color.Green  -> Colors.Theme.Accent.Green.Green3
            Color.Blue   -> Colors.Theme.Accent.Primary.Primary3
            Color.Red    -> Colors.Theme.Accent.Red.Red3
            Color.Yellow -> Colors.Theme.Accent.Yellow.Yellow3
            Color.Light  -> Colors.White.White10
        }

        Type.Solid    -> when (color) {
            Color.White  -> Colors.Theme.Layer.Layer
            Color.Dark   -> Colors.Theme.Secondary.Secondary
            Color.Gray   -> Colors.Theme.Surface.Surface4
            Color.Green  -> Colors.Teal.Teal500
            Color.Blue   -> Colors.Theme.Default.Primary
            Color.Red    -> Colors.Red.Red500
            Color.Yellow -> Colors.Yellow.Yellow500
            Color.Light  -> Colors.Theme.Background.BackgroundPlain
        }
    }
}

private fun getForegroundColor(type: Type, color: Color): ComposeColor {
    return when (type) {
        Type.White    -> Colors.Theme.Layer.LayerForeground

        Type.Outlined -> when (color) {
            Color.White  -> Colors.Theme.Layer.LayerForeground
            Color.Dark   -> Colors.Theme.Foreground.Foreground
            Color.Gray   -> Colors.Theme.Muted.MutedForeground1
            Color.Green  -> Colors.Teal.Teal500
            Color.Blue   -> Colors.Theme.Default.Primary
            Color.Red    -> Colors.Red.Red500
            Color.Yellow -> Colors.Yellow.Yellow500
            Color.Light  -> Colors.Theme.Foreground.ForegroundInverse
        }

        Type.Soft     -> when (color) {
            Color.White  -> Colors.Theme.Layer.LayerForeground
            Color.Dark   -> Colors.Theme.Surface.SurfaceForeground
            Color.Gray   -> Colors.Theme.Muted.MutedForeground1
            Color.Green  -> Colors.Theme.Accent.Green.Green7
            Color.Blue   -> Colors.Theme.Accent.Primary.Primary7
            Color.Red    -> Colors.Theme.Accent.Red.Red7
            Color.Yellow -> Colors.Theme.Accent.Yellow.Yellow7
            Color.Light  -> Colors.Theme.Foreground.ForegroundInverse
        }

        Type.Solid    -> when (color) {
            Color.White  -> Colors.Theme.Layer.LayerForeground
            Color.Dark   -> Colors.Theme.Secondary.SecondaryForeground
            Color.Gray   -> Colors.Theme.Foreground.ForegroundInverse
            Color.Green  -> Colors.Theme.Foreground.ForegroundInverse
            Color.Blue   -> Colors.Theme.Default.PrimaryForeground
            Color.Red    -> Colors.Theme.Foreground.ForegroundInverse
            Color.Yellow -> Colors.Theme.Foreground.ForegroundInverse
            Color.Light  -> Colors.Theme.Foreground.ForegroundInverse
        }
    }
}

private fun getStrokeColor(type: Type, color: Color): ComposeColor {
    return when (type) {
        Type.White    -> Colors.Theme.Layer.LayerBorder

        Type.Outlined -> when (color) {
            Color.White  -> Colors.Theme.Layer.LayerBorder
            Color.Dark   -> Colors.Theme.Border.Border8
            Color.Gray   -> Colors.Theme.Border.Border5
            Color.Green  -> Colors.Teal.Teal500
            Color.Blue   -> Colors.Theme.Default.Primary
            Color.Red    -> Colors.Red.Red500
            Color.Yellow -> Colors.Yellow.Yellow500
            Color.Light  -> Colors.White.White20
        }

        Type.Soft     -> when (color) {
            Color.White  -> Colors.Theme.Layer.LayerBorder
            Color.Dark   -> Colors.Transparent
            Color.Gray   -> Colors.Transparent
            Color.Green  -> Colors.Transparent
            Color.Blue   -> Colors.Transparent
            Color.Red    -> Colors.Transparent
            Color.Yellow -> Colors.Transparent
            Color.Light  -> Colors.Transparent
        }

        Type.Solid    -> when (color) {
            Color.White  -> Colors.Theme.Layer.LayerBorder
            Color.Dark   -> Colors.Transparent
            Color.Gray   -> Colors.Transparent
            Color.Green  -> Colors.Transparent
            Color.Blue   -> Colors.Transparent
            Color.Red    -> Colors.Transparent
            Color.Yellow -> Colors.Transparent
            Color.Light  -> Colors.Transparent
        }
    }
}

private fun getCloseIconBackgroundColor(type: Type, color: Color): ComposeColor {
    return when (type) {
        Type.White    -> Colors.Theme.Surface.Surface1

        Type.Outlined -> when (color) {
            Color.White  -> Colors.Theme.Surface.Surface1
            Color.Dark   -> Colors.Theme.Surface.Surface1
            Color.Gray   -> Colors.Theme.Surface.Surface1
            Color.Green  -> Colors.Theme.Accent.Green.Green3
            Color.Blue   -> Colors.Theme.Accent.Primary.Primary3
            Color.Red    -> Colors.Theme.Accent.Red.Red3
            Color.Yellow -> Colors.Theme.Accent.Yellow.Yellow3
            Color.Light  -> Colors.White.White10
        }

        Type.Soft     -> when (color) {
            Color.White  -> Colors.Theme.Surface.Surface1
            Color.Dark   -> Colors.Theme.Surface.Surface
            Color.Gray   -> Colors.Theme.Surface.Surface1
            Color.Green  -> Colors.Theme.Accent.Green.Green6
            Color.Blue   -> Colors.Theme.Accent.Primary.Primary6
            Color.Red    -> Colors.Theme.Accent.Red.Red6
            Color.Yellow -> Colors.Theme.Accent.Yellow.Yellow6
            Color.Light  -> Colors.White.White10
        }

        Type.Solid    -> when (color) {
            Color.White  -> Colors.Theme.Surface.Surface1
            Color.Dark   -> Colors.Theme.Background.Background
            Color.Gray   -> Colors.Theme.Surface.Surface3
            Color.Green  -> Colors.Solid.GreenCloseIcon
            Color.Blue   -> Colors.Solid.BlueCloseIcon
            Color.Red    -> Colors.Solid.RedCloseIcon
            Color.Yellow -> Colors.Solid.YellowCloseIcon
            Color.Light  -> Colors.Theme.Background.BackgroundInverse
        }
    }
}

private fun getCloseIconForegroundColor(type: Type, color: Color): ComposeColor {
    return when (type) {
        Type.White    -> Colors.Theme.Surface.SurfaceForeground

        Type.Outlined -> when (color) {
            Color.White  -> Colors.Theme.Surface.SurfaceForeground
            Color.Dark   -> Colors.Theme.Surface.SurfaceForeground
            Color.Gray   -> Colors.Theme.Surface.SurfaceForeground
            Color.Green  -> Colors.Theme.Accent.Green.Green7
            Color.Blue   -> Colors.Theme.Accent.Primary.Primary7
            Color.Red    -> Colors.Theme.Accent.Red.Red7
            Color.Yellow -> Colors.Theme.Accent.Yellow.Yellow7
            Color.Light  -> Colors.Theme.Foreground.ForegroundInverse
        }

        Type.Soft     -> when (color) {
            Color.White  -> Colors.Theme.Surface.SurfaceForeground
            Color.Dark   -> Colors.Theme.Surface.SurfaceForeground
            Color.Gray   -> Colors.Theme.Surface.SurfaceForeground
            Color.Green  -> Colors.Theme.Accent.Green.Green7
            Color.Blue   -> Colors.Theme.Accent.Primary.Primary7
            Color.Red    -> Colors.Theme.Accent.Red.Red7
            Color.Yellow -> Colors.Theme.Accent.Yellow.Yellow7
            Color.Light  -> Colors.Theme.Foreground.ForegroundInverse
        }

        Type.Solid    -> when (color) {
            Color.White  -> Colors.Theme.Surface.SurfaceForeground
            Color.Dark   -> Colors.Theme.Foreground.Foreground
            Color.Gray   -> Colors.Theme.Foreground.ForegroundInverse
            Color.Green  -> Colors.Theme.Foreground.ForegroundInverse
            Color.Blue   -> Colors.Theme.Default.PrimaryForeground
            Color.Red    -> Colors.Theme.Foreground.ForegroundInverse
            Color.Yellow -> Colors.Theme.Foreground.ForegroundInverse
            Color.Light  -> Colors.Theme.Foreground.ForegroundInverse
        }
    }
}

private fun getContainerPadding(size: Size, horizontal: Boolean): Dp = when (size) {
    Size.Large   -> if (horizontal) 16.dp else 12.dp
    Size.Default -> if (horizontal) 12.dp else 8.dp
    Size.Small   -> if (horizontal) 8.dp else 4.dp
}

private fun getAvatarSize(size: Size): Dp = when (size) {
    Size.Large, Size.Default -> 16.dp
    Size.Small               -> 14.dp
}

private fun getLeadingIconSize(size: Size): Dp = when (size) {
    Size.Large   -> 14.dp
    Size.Default -> 12.dp
    Size.Small   -> 10.dp
}

private fun getTrailingIconSize(size: Size): Dp = when (size) {
    Size.Large   -> 14.dp
    Size.Default -> 12.dp
    Size.Small   -> 10.dp
}

private fun getCloseIconSize(size: Size): Dp = when (size) {
    Size.Large   -> 16.dp
    Size.Default -> 14.dp
    Size.Small   -> 12.dp
}

private fun getShape(shape: Shape): ComposeShape = when (shape) {
    Shape.Rounded -> RoundedCornerShape(6.dp)
    Shape.Pilled  -> CircleShape
}
