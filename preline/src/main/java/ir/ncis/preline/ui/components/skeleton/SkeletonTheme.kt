package ir.ncis.preline.ui.components.skeleton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import ir.ncis.preline.ui.theme.PrelineTheme

data class SkeletonTheme(
    val base: Color,
    val highlight: Color,
)

@Composable
@ReadOnlyComposable
internal fun getTheme(): SkeletonTheme = with(PrelineTheme.colors) {
    if (isDark) SkeletonTheme(base = surface, highlight = surface1) else SkeletonTheme(base = surface1, highlight = surface)
}
