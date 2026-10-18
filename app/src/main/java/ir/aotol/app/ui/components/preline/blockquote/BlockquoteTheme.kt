package ir.aotol.app.ui.components.preline.blockquote

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.aotol.app.ui.components.preline.PrelineTheme
import ir.aotol.app.ui.components.preline.bold
import ir.aotol.app.ui.components.preline.medium

data class BlockquoteTheme(
    val leftBorderColor: Color,
    val quoteColor: Color,
    val textColor: Color,
    val authorColor: Color,
    val sourceTitleColor: Color,
    val textAlign: TextAlign,
    val placeholderStyle: TextStyle,
    val authorStyle: TextStyle,
    val sourceTitleStyle: TextStyle,
    val leftBorderThickness: Dp,
    val leftBorderSpacing: Dp,
    val quoteSize: Dp,
    val quoteTopPadding: Dp,
    val quoteStartPadding: Dp,
    val contentSpacerHeight: Dp,
) {
    sealed interface Size {
        object Large : Size
        object Default : Size
        object Small : Size
    }

    sealed interface Type {
        object Avatar : Type
        object Base : Type
        object Centered : Type
        object LeftBorder : Type
        object Quote : Type
        object SourceTitle : Type
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(type: BlockquoteTheme.Type, size: BlockquoteTheme.Size): BlockquoteTheme = BlockquoteTheme(
    leftBorderColor = PrelineTheme.colors.border,
    quoteColor = PrelineTheme.colors.mutedForeground2,
    textColor = PrelineTheme.colors.foreground,
    authorColor = PrelineTheme.colors.foreground,
    sourceTitleColor = PrelineTheme.colors.mutedForeground1,
    textAlign = getTextAlign(type),
    placeholderStyle = getPlaceholderStyle(size),
    authorStyle = getAuthorStyle(size),
    sourceTitleStyle = PrelineTheme.typography.sm.medium,
    leftBorderThickness = 4.dp,
    leftBorderSpacing = 28.dp,
    quoteSize = 64.dp,
    quoteTopPadding = 32.dp,
    quoteStartPadding = 24.dp,
    contentSpacerHeight = 20.dp
)

private fun getTextAlign(type: BlockquoteTheme.Type): TextAlign = if (type == BlockquoteTheme.Type.Centered) TextAlign.Center else TextAlign.Start

@Composable
@ReadOnlyComposable
private fun getPlaceholderStyle(size: BlockquoteTheme.Size): TextStyle = when (size) {
    BlockquoteTheme.Size.Large   -> PrelineTheme.typography.xl3.bold
    BlockquoteTheme.Size.Default -> PrelineTheme.typography.xl.bold
    BlockquoteTheme.Size.Small   -> PrelineTheme.typography.base.bold
}

@Composable
@ReadOnlyComposable
private fun getAuthorStyle(size: BlockquoteTheme.Size): TextStyle = when (size) {
    BlockquoteTheme.Size.Large                               -> PrelineTheme.typography.lg.bold
    BlockquoteTheme.Size.Default, BlockquoteTheme.Size.Small -> PrelineTheme.typography.base.bold
}
