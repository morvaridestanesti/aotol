package ir.ncis.preline.ui.components.advanced_select

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.PrelineTheme

data class AdvancedSelectTheme(
    val background: Color,
    val stroke: Color,
    val focusedStroke: Color,
    val text: Color,
    val placeholder: Color,
    val description: Color,
    val group: Color,
    val check: Color,
    val highlight: Color,
    val menuBackground: Color,
    val shape: Shape,
) {
    sealed interface Display {
        object Text : Display
        object Badges : Display
    }

    sealed interface Search {
        object None : Search
        object InField : Search
        object InMenu : Search
    }

    sealed interface Status {
        object Online : Status
        object Away : Status
        object Busy : Status
        object Offline : Status
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(): AdvancedSelectTheme {
    val colors = PrelineTheme.colors
    return AdvancedSelectTheme(
        background = colors.layer,
        stroke = colors.layerBorder,
        focusedStroke = colors.primary,
        text = colors.foreground,
        placeholder = colors.mutedForeground,
        description = colors.mutedForeground1,
        group = colors.primary,
        check = colors.primary,
        highlight = colors.layerHover,
        menuBackground = colors.layer,
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
@ReadOnlyComposable
internal fun getStatusColor(status: AdvancedSelectTheme.Status): Color = with(PrelineTheme.colors) {
    when (status) {
        AdvancedSelectTheme.Status.Online  -> green.solid
        AdvancedSelectTheme.Status.Away    -> yellow.solid
        AdvancedSelectTheme.Status.Busy    -> red.solid
        AdvancedSelectTheme.Status.Offline -> mutedForeground
    }
}
