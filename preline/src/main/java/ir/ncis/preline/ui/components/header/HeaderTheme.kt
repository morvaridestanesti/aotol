package ir.ncis.preline.ui.components.header

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme

data class HeaderTheme(
    val background: Color,
    val stroke: Color,
    val bottomBorder: Color,
    val link: Color,
    val activeLink: Color,
    val divider: Color,
    val shape: Shape,
) {
    sealed interface Type {
        object Plain : Type
        object Gray : Type
        object Inverse : Type
    }

    sealed interface Border {
        object None : Border
        object Bottom : Border
        object Rounded : Border
    }

    sealed interface Alignment {
        object Start : Alignment
        object Center : Alignment
        object End : Alignment
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(type: HeaderTheme.Type, border: HeaderTheme.Border): HeaderTheme {
    val colors = PrelineTheme.colors
    val inverse = type == HeaderTheme.Type.Inverse
    return HeaderTheme(
        background = when (type) {
            HeaderTheme.Type.Plain   -> if (border == HeaderTheme.Border.Rounded) colors.layer else Palette.Transparent
            HeaderTheme.Type.Gray    -> colors.muted
            HeaderTheme.Type.Inverse -> Palette.Blue.Blue950
        },
        stroke = if (border == HeaderTheme.Border.Rounded) colors.layerBorder else Palette.Transparent,
        bottomBorder = if (border == HeaderTheme.Border.Bottom) colors.layerBorder else Palette.Transparent,
        link = if (inverse) Palette.White.copy(alpha = 0.8f) else colors.foreground,
        activeLink = if (inverse) Palette.White else colors.primary,
        divider = if (inverse) Palette.White.copy(alpha = 0.2f) else colors.layerBorder,
        shape = if (border == HeaderTheme.Border.Rounded) RoundedCornerShape(12.dp) else RectangleShape
    )
}
