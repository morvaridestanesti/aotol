package ir.ncis.preline.ui.components.strong_password

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import ir.ncis.preline.ui.theme.PrelineTheme

data class StrongPasswordTheme(
    val emptySegment: Color,
    val filledSegment: Color,
    val completeSegment: Color,
    val restSegment: Color,
    val text: Color,
    val unmet: Color,
    val met: Color,
) {
    sealed interface Hints {
        object None : Hints
        object Inline : Hints
        object OnFocus : Hints
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(): StrongPasswordTheme {
    val colors = PrelineTheme.colors
    return StrongPasswordTheme(
        emptySegment = colors.primary.copy(alpha = 0.5f),
        filledSegment = colors.primary,
        completeSegment = colors.green.solid,
        restSegment = colors.surface,
        text = colors.foreground,
        unmet = colors.mutedForeground1,
        met = colors.green.solid
    )
}
