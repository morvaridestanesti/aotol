package ir.ncis.preline.ui.components.blockquote

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.components.avatar.AvatarTheme
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.semiBold

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
    val avatarSize: AvatarTheme.Size,
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
    quoteColor = PrelineTheme.colors.surface2,
    textColor = PrelineTheme.colors.foreground,
    authorColor = PrelineTheme.colors.foreground,
    sourceTitleColor = PrelineTheme.colors.mutedForeground1,
    textAlign = getTextAlign(type),
    placeholderStyle = getPlaceholderStyle(size),
    authorStyle = getAuthorStyle(size),
    sourceTitleStyle = PrelineTheme.typography.xs,
    leftBorderThickness = 2.dp,
    leftBorderSpacing = 12.dp,
    quoteSize = getQuoteSize(size),
    quoteTopPadding = getQuoteSize(size) / 2,
    quoteStartPadding = getQuoteSize(size) / 3,
    contentSpacerHeight = if (size == BlockquoteTheme.Size.Large) 16.dp else 12.dp,
    avatarSize = if (size == BlockquoteTheme.Size.Large) AvatarTheme.Size.Small else AvatarTheme.Size.XS,
)

private fun getTextAlign(type: BlockquoteTheme.Type): TextAlign = if (type == BlockquoteTheme.Type.Centered) TextAlign.Center else TextAlign.Start

@Composable
@ReadOnlyComposable
private fun getPlaceholderStyle(size: BlockquoteTheme.Size): TextStyle = when (size) {
    BlockquoteTheme.Size.Large   -> PrelineTheme.typography.xl
    BlockquoteTheme.Size.Default -> PrelineTheme.typography.sm
    BlockquoteTheme.Size.Small   -> PrelineTheme.typography.xs
}

@Composable
@ReadOnlyComposable
private fun getAuthorStyle(size: BlockquoteTheme.Size): TextStyle = when (size) {
    BlockquoteTheme.Size.Large                               -> PrelineTheme.typography.sm.semiBold
    BlockquoteTheme.Size.Default, BlockquoteTheme.Size.Small -> PrelineTheme.typography.xs.semiBold
}

private fun getQuoteSize(size: BlockquoteTheme.Size): Dp = when (size) {
    BlockquoteTheme.Size.Large   -> 48.dp
    BlockquoteTheme.Size.Default -> 36.dp
    BlockquoteTheme.Size.Small   -> 28.dp
}
