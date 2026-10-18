package ir.aotol.app.ui.components.preline.button_icon

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

data class ButtonIconTheme(
    val background: ComposeColor,
    val foreground: ComposeColor,
    val stroke: ComposeColor,
    val height: Dp,
    val padding: Dp,
    val iconSize: Dp,
    val shape: ComposeShape,
    val badgeBackground: ComposeColor,
    val badgeBorder: ComposeColor,
    val badgeForeground: ComposeColor,
    val badgeShape: ComposeShape,
) {
    sealed interface Color {
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
        object Circled : Shape
    }

    sealed interface Size {
        object Default : Size
        object Large : Size
        object Small : Size
    }

    sealed interface State {
        object Default : State
        object Disabled : State
        object Focus : State
        object Hover : State
    }

    sealed interface Type {
        object Ghost : Type
        object Link : Type
        object Outlined : Type
        object Soft : Type
        object Solid : Type
        object White : Type
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(type: ButtonIconTheme.Type, color: ButtonIconTheme.Color, state: ButtonIconTheme.State, size: ButtonIconTheme.Size, shape: ButtonIconTheme.Shape): ButtonIconTheme = ButtonIconTheme(
    background = getBackgroundColor(type, color, state),
    foreground = getForegroundColor(type, color, state),
    stroke = getStrokeColor(type, color, state),
    height = getHeight(size),
    padding = getContainerPadding(size),
    iconSize = getIconSize(size),
    shape = if (shape == ButtonIconTheme.Shape.Circled) CircleShape else RoundedCornerShape(8.dp),
    badgeBackground = PrelineTheme.colors.layer,
    badgeBorder = PrelineTheme.colors.layerBorder,
    badgeForeground = PrelineTheme.colors.layerForeground,
    badgeShape = CircleShape
)

@Composable
@ReadOnlyComposable
private fun getBackgroundColor(type: ButtonIconTheme.Type, color: ButtonIconTheme.Color, state: ButtonIconTheme.State): ComposeColor {
    return when (type) {
        ButtonIconTheme.Type.Ghost    -> when (color) {
            ButtonIconTheme.Color.Dark   -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> Palette.Transparent
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.mutedFocus
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.mutedHover
            }

            ButtonIconTheme.Color.Gray   -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> Palette.Transparent
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.mutedFocus
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.mutedHover
            }

            ButtonIconTheme.Color.Green  -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> Palette.Transparent
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.green.soft
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.green.soft
            }

            ButtonIconTheme.Color.Blue   -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> Palette.Transparent
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.blue.soft
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.blue.soft
            }

            ButtonIconTheme.Color.Red    -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> Palette.Transparent
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.red.soft
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.red.soft
            }

            ButtonIconTheme.Color.Yellow -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> Palette.Transparent
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.yellow.soft
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.yellow.soft
            }

            ButtonIconTheme.Color.Light  -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> Palette.Transparent
                ButtonIconTheme.State.Focus                                   -> Palette.White10
                ButtonIconTheme.State.Hover                                   -> Palette.White10
            }
        }

        ButtonIconTheme.Type.Link     -> Palette.Transparent
        ButtonIconTheme.Type.Outlined -> Palette.Transparent
        ButtonIconTheme.Type.Soft     -> when (color) {
            ButtonIconTheme.Color.Dark   -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> PrelineTheme.colors.surface
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.surfaceFocus
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.surfaceHover
            }

            ButtonIconTheme.Color.Gray   -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> PrelineTheme.colors.surface
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.surfaceFocus
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.surfaceHover
            }

            ButtonIconTheme.Color.Green  -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> PrelineTheme.colors.green.soft
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.green.softHover
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.green.softHover
            }

            ButtonIconTheme.Color.Blue   -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> PrelineTheme.colors.blue.soft
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.blue.softHover
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.blue.softHover
            }

            ButtonIconTheme.Color.Red    -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> PrelineTheme.colors.red.soft
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.red.softHover
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.red.softHover
            }

            ButtonIconTheme.Color.Yellow -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> PrelineTheme.colors.yellow.soft
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.yellow.softHover
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.yellow.softHover
            }

            ButtonIconTheme.Color.Light  -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> Palette.White10
                ButtonIconTheme.State.Focus                                   -> Palette.White20
                ButtonIconTheme.State.Hover                                   -> Palette.White20
            }
        }

        ButtonIconTheme.Type.Solid    -> when (color) {
            ButtonIconTheme.Color.Dark   -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> PrelineTheme.colors.secondary
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.secondaryFocus
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.secondaryHover
            }

            ButtonIconTheme.Color.Gray   -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> PrelineTheme.colors.surface4
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.surface5
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.surface5
            }

            ButtonIconTheme.Color.Green  -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> PrelineTheme.colors.green.solid
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.green.solidHover
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.green.solidHover
            }

            ButtonIconTheme.Color.Blue   -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> PrelineTheme.colors.primary
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.primaryFocus
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.primaryHover
            }

            ButtonIconTheme.Color.Red    -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> PrelineTheme.colors.red.solid
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.red.solidHover
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.red.solidHover
            }

            ButtonIconTheme.Color.Yellow -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> PrelineTheme.colors.yellow.solid
                ButtonIconTheme.State.Focus                                   -> PrelineTheme.colors.yellow.solidHover
                ButtonIconTheme.State.Hover                                   -> PrelineTheme.colors.yellow.solidHover
            }

            ButtonIconTheme.Color.Light  -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> Palette.White
                ButtonIconTheme.State.Focus                                   -> Palette.White90
                ButtonIconTheme.State.Hover                                   -> Palette.White90
            }
        }

        ButtonIconTheme.Type.White    -> when (state) {
            ButtonIconTheme.State.Hover -> PrelineTheme.colors.layerHover
            ButtonIconTheme.State.Focus -> PrelineTheme.colors.layerFocus
            else                        -> PrelineTheme.colors.layer
        }
    }
}

@Composable
@ReadOnlyComposable
private fun getForegroundColor(type: ButtonIconTheme.Type, color: ButtonIconTheme.Color, state: ButtonIconTheme.State): ComposeColor {
    return when (type) {
        ButtonIconTheme.Type.Ghost    -> when (color) {
            ButtonIconTheme.Color.Dark   -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> PrelineTheme.colors.foreground
                else                                                          -> PrelineTheme.colors.foreground
            }

            ButtonIconTheme.Color.Gray   -> when (state) {
                ButtonIconTheme.State.Default, ButtonIconTheme.State.Disabled -> PrelineTheme.colors.mutedForeground1
                else                                                          -> PrelineTheme.colors.mutedForeground1
            }

            ButtonIconTheme.Color.Green  -> PrelineTheme.colors.green.solid
            ButtonIconTheme.Color.Blue   -> PrelineTheme.colors.primary
            ButtonIconTheme.Color.Red    -> PrelineTheme.colors.red.solid
            ButtonIconTheme.Color.Yellow -> PrelineTheme.colors.yellow.solid
            ButtonIconTheme.Color.Light  -> when (state) {
                else           -> Palette.White
            }
        }

        ButtonIconTheme.Type.Link     -> when (color) {
            ButtonIconTheme.Color.Dark   -> PrelineTheme.colors.foreground
            ButtonIconTheme.Color.Gray   -> PrelineTheme.colors.mutedForeground1
            ButtonIconTheme.Color.Green  -> PrelineTheme.colors.green.solid
            ButtonIconTheme.Color.Blue   -> PrelineTheme.colors.primary
            ButtonIconTheme.Color.Red    -> PrelineTheme.colors.red.solid
            ButtonIconTheme.Color.Yellow -> PrelineTheme.colors.yellow.solid
            ButtonIconTheme.Color.Light  -> Palette.White
        }

        ButtonIconTheme.Type.Outlined -> when (color) {
            ButtonIconTheme.Color.Dark   -> PrelineTheme.colors.foreground
            ButtonIconTheme.Color.Gray   -> PrelineTheme.colors.mutedForeground1
            ButtonIconTheme.Color.Green  -> PrelineTheme.colors.green.solid
            ButtonIconTheme.Color.Blue   -> PrelineTheme.colors.primary
            ButtonIconTheme.Color.Red    -> PrelineTheme.colors.red.solid
            ButtonIconTheme.Color.Yellow -> PrelineTheme.colors.yellow.solid
            ButtonIconTheme.Color.Light  -> Palette.White
        }

        ButtonIconTheme.Type.Soft     -> when (color) {
            ButtonIconTheme.Color.Dark   -> PrelineTheme.colors.surfaceForeground
            ButtonIconTheme.Color.Gray   -> PrelineTheme.colors.mutedForeground1
            ButtonIconTheme.Color.Green  -> PrelineTheme.colors.green.softForeground
            ButtonIconTheme.Color.Blue   -> PrelineTheme.colors.blue.softForeground
            ButtonIconTheme.Color.Red    -> PrelineTheme.colors.red.softForeground
            ButtonIconTheme.Color.Yellow -> PrelineTheme.colors.yellow.softForeground
            ButtonIconTheme.Color.Light  -> Palette.White
        }

        ButtonIconTheme.Type.Solid    -> when (color) {
            ButtonIconTheme.Color.Dark   -> PrelineTheme.colors.secondaryForeground
            ButtonIconTheme.Color.Gray   -> Palette.White
            ButtonIconTheme.Color.Green  -> Palette.White
            ButtonIconTheme.Color.Blue   -> PrelineTheme.colors.primaryForeground
            ButtonIconTheme.Color.Red    -> Palette.White
            ButtonIconTheme.Color.Yellow -> Palette.White
            ButtonIconTheme.Color.Light  -> Palette.Gray.Gray800
        }

        ButtonIconTheme.Type.White    -> when (color) {
            ButtonIconTheme.Color.Dark   -> PrelineTheme.colors.layerForeground
            ButtonIconTheme.Color.Gray   -> PrelineTheme.colors.mutedForeground1
            ButtonIconTheme.Color.Green  -> PrelineTheme.colors.green.solid
            ButtonIconTheme.Color.Blue   -> PrelineTheme.colors.primary
            ButtonIconTheme.Color.Red    -> PrelineTheme.colors.red.solid
            ButtonIconTheme.Color.Yellow -> PrelineTheme.colors.yellow.solid
            ButtonIconTheme.Color.Light  -> Palette.Gray.Gray800
        }
    }
}

@Composable
@ReadOnlyComposable
private fun getStrokeColor(type: ButtonIconTheme.Type, color: ButtonIconTheme.Color, state: ButtonIconTheme.State): ComposeColor {
    return when (type) {
        ButtonIconTheme.Type.Outlined -> when (color) {
            ButtonIconTheme.Color.Dark   -> PrelineTheme.colors.borderLine8
            ButtonIconTheme.Color.Gray   -> PrelineTheme.colors.borderLine5
            ButtonIconTheme.Color.Green  -> PrelineTheme.colors.green.solid
            ButtonIconTheme.Color.Blue   -> PrelineTheme.colors.primary
            ButtonIconTheme.Color.Red    -> PrelineTheme.colors.red.solid
            ButtonIconTheme.Color.Yellow -> PrelineTheme.colors.yellow.solid
            ButtonIconTheme.Color.Light  -> Palette.White
        }

        ButtonIconTheme.Type.White    -> PrelineTheme.colors.layerBorder
        else                          -> Palette.Transparent
    }
}

private fun getHeight(size: ButtonIconTheme.Size): Dp = when (size) {
    ButtonIconTheme.Size.Large   -> 60.dp
    ButtonIconTheme.Size.Default -> 44.dp
    ButtonIconTheme.Size.Small   -> 36.dp
}

private fun getContainerPadding(size: ButtonIconTheme.Size): Dp = when (size) {
    ButtonIconTheme.Size.Large   -> 16.dp
    ButtonIconTheme.Size.Default -> 12.dp
    ButtonIconTheme.Size.Small   -> 10.dp
}

private fun getIconSize(size: ButtonIconTheme.Size): Dp = when (size) {
    ButtonIconTheme.Size.Large   -> 24.dp
    ButtonIconTheme.Size.Default -> 20.dp
    ButtonIconTheme.Size.Small   -> 16.dp
}
