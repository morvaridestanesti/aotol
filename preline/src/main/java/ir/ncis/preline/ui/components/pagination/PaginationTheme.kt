package ir.ncis.preline.ui.components.pagination

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme

data class PaginationTheme(
    val container: Item,
    val currentPage: Item,
    val navGroup: Item,
) {
    data class Item(
        val background: Color,
        val border: Color,
        val shape: Shape,
    )

    sealed interface Type {
        object BaseNone : Type
        object BasePilled : Type

        object BorderedRounded : Type
        object BorderedPilled : Type

        object BorderedPrevNextButtonRounded : Type
        object BorderedPrevNextButtonPilled : Type

        object PagerRounded : Type
        object PagerPilled : Type

        object PaginationPagerRounded : Type
        object PaginationPagerPilled : Type

        object PagerBorderedRounded : Type
        object PagerBorderedPilled : Type
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(type: PaginationTheme.Type): PaginationTheme = when (type) {
    PaginationTheme.Type.BaseNone                             -> PaginationTheme(
        container = PaginationTheme.Item(Palette.Transparent, Palette.Transparent, RectangleShape),
        currentPage = PaginationTheme.Item(PrelineTheme.colors.surfaceActive, Palette.Transparent, RoundedCornerShape(8.dp)),
        navGroup = PaginationTheme.Item(Palette.Transparent, Palette.Transparent, RectangleShape)
    )

    PaginationTheme.Type.BasePilled                           -> PaginationTheme(
        container = PaginationTheme.Item(Palette.Transparent, Palette.Transparent, RectangleShape),
        currentPage = PaginationTheme.Item(PrelineTheme.colors.surfaceActive, Palette.Transparent, CircleShape),
        navGroup = PaginationTheme.Item(Palette.Transparent, Palette.Transparent, RectangleShape)
    )

    PaginationTheme.Type.BorderedPilled, PaginationTheme.Type.BorderedRounded -> PaginationTheme(
        container = PaginationTheme.Item(
            Palette.Transparent,
            PrelineTheme.colors.layerBorder,
            if (type == PaginationTheme.Type.BorderedPilled) CircleShape else RoundedCornerShape(8.dp)
        ),
        currentPage = PaginationTheme.Item(PrelineTheme.colors.surfaceActive, Palette.Transparent, RectangleShape),
        navGroup = PaginationTheme.Item(Palette.Transparent, Palette.Transparent, RectangleShape)
    )

    PaginationTheme.Type.BorderedPrevNextButtonPilled         -> PaginationTheme(
        container = PaginationTheme.Item(Palette.Transparent, Palette.Transparent, RectangleShape),
        currentPage = PaginationTheme.Item(PrelineTheme.colors.surfaceActive, Palette.Transparent, CircleShape),
        navGroup = PaginationTheme.Item(PrelineTheme.colors.layer, PrelineTheme.colors.layerBorder, CircleShape)
    )

    PaginationTheme.Type.BorderedPrevNextButtonRounded        -> PaginationTheme(
        container = PaginationTheme.Item(Palette.Transparent, Palette.Transparent, RectangleShape),
        currentPage = PaginationTheme.Item(PrelineTheme.colors.surfaceActive, Palette.Transparent, RoundedCornerShape(8.dp)),
        navGroup = PaginationTheme.Item(PrelineTheme.colors.layer, PrelineTheme.colors.layerBorder, RoundedCornerShape(8.dp))
    )

    PaginationTheme.Type.PagerPilled                          -> PaginationTheme(
        container = PaginationTheme.Item(Palette.Transparent, Palette.Transparent, RectangleShape),
        currentPage = PaginationTheme.Item(PrelineTheme.colors.layer, PrelineTheme.colors.layerBorder, CircleShape),
        navGroup = PaginationTheme.Item(Palette.Transparent, Palette.Transparent, RectangleShape)
    )

    PaginationTheme.Type.PagerRounded                         -> PaginationTheme(
        container = PaginationTheme.Item(Palette.Transparent, Palette.Transparent, RectangleShape),
        currentPage = PaginationTheme.Item(PrelineTheme.colors.layer, PrelineTheme.colors.layerBorder, RectangleShape),
        navGroup = PaginationTheme.Item(Palette.Transparent, Palette.Transparent, RectangleShape)
    )

    PaginationTheme.Type.PaginationPagerPilled                -> PaginationTheme(
        container = PaginationTheme.Item(Palette.Transparent, Palette.Transparent, RectangleShape),
        currentPage = PaginationTheme.Item(PrelineTheme.colors.layer, PrelineTheme.colors.layerBorder, CircleShape),
        navGroup = PaginationTheme.Item(Palette.Transparent, Palette.Transparent, RectangleShape)
    )

    PaginationTheme.Type.PaginationPagerRounded               -> PaginationTheme(
        container = PaginationTheme.Item(Palette.Transparent, Palette.Transparent, RectangleShape),
        currentPage = PaginationTheme.Item(PrelineTheme.colors.layer, PrelineTheme.colors.layerBorder, RectangleShape),
        navGroup = PaginationTheme.Item(Palette.Transparent, Palette.Transparent, RectangleShape)
    )

    PaginationTheme.Type.PagerBorderedPilled                  -> PaginationTheme(
        container = PaginationTheme.Item(Palette.Transparent, Palette.Transparent, RectangleShape),
        currentPage = PaginationTheme.Item(PrelineTheme.colors.layer, PrelineTheme.colors.layerBorder, CircleShape),
        navGroup = PaginationTheme.Item(PrelineTheme.colors.layer, PrelineTheme.colors.layerBorder, CircleShape)
    )

    PaginationTheme.Type.PagerBorderedRounded                 -> PaginationTheme(
        container = PaginationTheme.Item(Palette.Transparent, Palette.Transparent, RectangleShape),
        currentPage = PaginationTheme.Item(PrelineTheme.colors.layer, PrelineTheme.colors.layerBorder, RoundedCornerShape(8.dp)),
        navGroup = PaginationTheme.Item(PrelineTheme.colors.layer, PrelineTheme.colors.layerBorder, RoundedCornerShape(8.dp))
    )
}
