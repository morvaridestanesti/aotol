package ir.aotol.app.ui.components.preline.alert

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color as ComposeColor
import ir.aotol.app.ui.components.preline.styled_icon.StyledIconTheme
import ir.aotol.app.ui.components.preline.Palette
import ir.aotol.app.ui.components.preline.PrelineTheme

data class AlertTheme(
    val container: ComposeColor,
    val primaryForeground: ComposeColor,
    val secondaryForeground: ComposeColor,
    val stroke: ComposeColor,
    val leadingIcon: ComposeColor,
    val featuredIcon: StyledIconTheme.Color,
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

    sealed interface Type {
        object Solid : Type
        object Soft : Type
        object White : Type
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(type: AlertTheme.Type, color: AlertTheme.Color): AlertTheme = AlertTheme(
    container = getContainerColor(type, color),
    primaryForeground = getPrimaryForegroundColor(type, color),
    secondaryForeground = getSecondaryForegroundColor(type, color),
    stroke = getStrokeColor(type, color),
    leadingIcon = getLeadingIconColor(type, color),
    featuredIcon = getFeaturedIconColor(type, color)
)

@Composable
@ReadOnlyComposable
private fun getContainerColor(type: AlertTheme.Type, color: AlertTheme.Color): ComposeColor {
    return when (type) {
        AlertTheme.Type.Solid -> when (color) {
            AlertTheme.Color.White  -> PrelineTheme.colors.layer
            AlertTheme.Color.Dark   -> PrelineTheme.colors.secondary
            AlertTheme.Color.Gray   -> PrelineTheme.colors.surface4
            AlertTheme.Color.Green  -> PrelineTheme.colors.green.solid
            AlertTheme.Color.Blue   -> PrelineTheme.colors.primary
            AlertTheme.Color.Red    -> PrelineTheme.colors.red.solid
            AlertTheme.Color.Yellow -> PrelineTheme.colors.yellow.solid
            AlertTheme.Color.Light  -> Palette.White
        }

        AlertTheme.Type.Soft  -> when (color) {
            AlertTheme.Color.White  -> PrelineTheme.colors.layer
            AlertTheme.Color.Dark   -> PrelineTheme.colors.surface
            AlertTheme.Color.Gray   -> PrelineTheme.colors.muted
            AlertTheme.Color.Green  -> PrelineTheme.colors.green.soft
            AlertTheme.Color.Blue   -> PrelineTheme.colors.blue.soft
            AlertTheme.Color.Red    -> PrelineTheme.colors.red.soft
            AlertTheme.Color.Yellow -> PrelineTheme.colors.yellow.soft
            AlertTheme.Color.Light  -> Palette.White10
        }

        AlertTheme.Type.White -> PrelineTheme.colors.layer
    }
}

@Composable
@ReadOnlyComposable
private fun getPrimaryForegroundColor(type: AlertTheme.Type, color: AlertTheme.Color): ComposeColor {
    return when (type) {
        AlertTheme.Type.Solid -> when (color) {
            AlertTheme.Color.White  -> PrelineTheme.colors.layerForeground
            AlertTheme.Color.Dark   -> PrelineTheme.colors.secondaryForeground
            AlertTheme.Color.Gray   -> Palette.White
            AlertTheme.Color.Green  -> Palette.White
            AlertTheme.Color.Blue   -> PrelineTheme.colors.primaryForeground
            AlertTheme.Color.Red    -> Palette.White
            AlertTheme.Color.Yellow -> Palette.White
            AlertTheme.Color.Light  -> Palette.White
        }

        AlertTheme.Type.Soft  -> when (color) {
            AlertTheme.Color.White  -> PrelineTheme.colors.layerForeground
            AlertTheme.Color.Dark   -> PrelineTheme.colors.surface
            AlertTheme.Color.Gray   -> PrelineTheme.colors.foreground
            AlertTheme.Color.Green  -> PrelineTheme.colors.green.softForeground
            AlertTheme.Color.Blue   -> PrelineTheme.colors.blue.softForeground
            AlertTheme.Color.Red    -> PrelineTheme.colors.red.softForeground
            AlertTheme.Color.Yellow -> PrelineTheme.colors.yellow.softForeground
            AlertTheme.Color.Light  -> Palette.White
        }

        AlertTheme.Type.White -> PrelineTheme.colors.layerForeground
    }
}

@Composable
@ReadOnlyComposable
private fun getSecondaryForegroundColor(type: AlertTheme.Type, color: AlertTheme.Color): ComposeColor {
    return when (type) {
        AlertTheme.Type.Solid -> when (color) {
            AlertTheme.Color.White  -> PrelineTheme.colors.mutedForeground1
            AlertTheme.Color.Dark   -> PrelineTheme.colors.mutedForeground
            AlertTheme.Color.Gray   -> Palette.White
            AlertTheme.Color.Green  -> Palette.Teal.Teal100
            AlertTheme.Color.Blue   -> Palette.Blue.Blue100
            AlertTheme.Color.Red    -> Palette.Red.Red100
            AlertTheme.Color.Yellow -> Palette.Yellow.Yellow100
            AlertTheme.Color.Light  -> PrelineTheme.colors.mutedForeground
        }

        AlertTheme.Type.Soft  -> when (color) {
            AlertTheme.Color.White  -> PrelineTheme.colors.mutedForeground1
            AlertTheme.Color.Dark   -> PrelineTheme.colors.mutedForeground1
            AlertTheme.Color.Gray   -> PrelineTheme.colors.mutedForeground1
            AlertTheme.Color.Green  -> PrelineTheme.colors.green.softForeground
            AlertTheme.Color.Blue   -> PrelineTheme.colors.blue.softForeground
            AlertTheme.Color.Red    -> PrelineTheme.colors.red.softForeground
            AlertTheme.Color.Yellow -> PrelineTheme.colors.yellow.softForeground
            AlertTheme.Color.Light  -> Palette.White70
        }

        AlertTheme.Type.White -> PrelineTheme.colors.mutedForeground1
    }
}

@Composable
@ReadOnlyComposable
private fun getStrokeColor(type: AlertTheme.Type, color: AlertTheme.Color): ComposeColor {
    return when (type) {
        AlertTheme.Type.Solid -> when (color) {
            AlertTheme.Color.White -> PrelineTheme.colors.layerBorder
            else                   -> Palette.Transparent
        }

        AlertTheme.Type.Soft  -> when (color) {
            AlertTheme.Color.White  -> PrelineTheme.colors.layerBorder
            AlertTheme.Color.Dark   -> PrelineTheme.colors.border
            AlertTheme.Color.Gray   -> PrelineTheme.colors.border
            AlertTheme.Color.Green  -> PrelineTheme.colors.green.softHover
            AlertTheme.Color.Blue   -> PrelineTheme.colors.blue.softHover
            AlertTheme.Color.Red    -> PrelineTheme.colors.red.softHover
            AlertTheme.Color.Yellow -> PrelineTheme.colors.yellow.softHover
            AlertTheme.Color.Light  -> Palette.White10
        }

        AlertTheme.Type.White -> PrelineTheme.colors.layerBorder
    }
}

@Composable
@ReadOnlyComposable
private fun getLeadingIconColor(type: AlertTheme.Type, color: AlertTheme.Color): ComposeColor {
    return when (type) {
        AlertTheme.Type.Solid -> when (color) {
            AlertTheme.Color.White  -> PrelineTheme.colors.primary
            AlertTheme.Color.Dark   -> PrelineTheme.colors.secondaryForeground
            AlertTheme.Color.Gray   -> Palette.White
            AlertTheme.Color.Green  -> Palette.Teal.Teal100
            AlertTheme.Color.Blue   -> Palette.Blue.Blue100
            AlertTheme.Color.Red    -> Palette.Red.Red100
            AlertTheme.Color.Yellow -> Palette.Yellow.Yellow100
            AlertTheme.Color.Light  -> PrelineTheme.colors.mutedForeground
        }

        AlertTheme.Type.Soft  -> when (color) {
            AlertTheme.Color.White  -> PrelineTheme.colors.primary
            AlertTheme.Color.Dark   -> PrelineTheme.colors.mutedForeground1
            AlertTheme.Color.Gray   -> PrelineTheme.colors.mutedForeground1
            AlertTheme.Color.Green  -> PrelineTheme.colors.green.softForeground
            AlertTheme.Color.Blue   -> PrelineTheme.colors.blue.softForeground
            AlertTheme.Color.Red    -> PrelineTheme.colors.red.softForeground
            AlertTheme.Color.Yellow -> PrelineTheme.colors.yellow.softForeground
            AlertTheme.Color.Light  -> Palette.White70
        }

        AlertTheme.Type.White -> PrelineTheme.colors.primary
    }
}

private fun getFeaturedIconColor(type: AlertTheme.Type, color: AlertTheme.Color): StyledIconTheme.Color {
    return when (type) {
        AlertTheme.Type.Solid -> when (color) {
            AlertTheme.Color.White, AlertTheme.Color.Gray -> StyledIconTheme.Color.Gray
            AlertTheme.Color.Dark                         -> StyledIconTheme.Color.Light
            AlertTheme.Color.Green                        -> StyledIconTheme.Color.Green
            AlertTheme.Color.Blue                         -> StyledIconTheme.Color.Blue
            AlertTheme.Color.Red                          -> StyledIconTheme.Color.Red
            AlertTheme.Color.Yellow                       -> StyledIconTheme.Color.Yellow
            AlertTheme.Color.Light                        -> StyledIconTheme.Color.Blue
        }

        AlertTheme.Type.Soft  -> when (color) {
            AlertTheme.Color.White  -> StyledIconTheme.Color.Gray
            AlertTheme.Color.Dark   -> StyledIconTheme.Color.Dark
            AlertTheme.Color.Gray   -> StyledIconTheme.Color.Gray
            AlertTheme.Color.Green  -> StyledIconTheme.Color.Green
            AlertTheme.Color.Blue   -> StyledIconTheme.Color.Blue
            AlertTheme.Color.Red    -> StyledIconTheme.Color.Red
            AlertTheme.Color.Yellow -> StyledIconTheme.Color.Yellow
            AlertTheme.Color.Light  -> StyledIconTheme.Color.Light
        }

        AlertTheme.Type.White -> StyledIconTheme.Color.Gray
    }
}
