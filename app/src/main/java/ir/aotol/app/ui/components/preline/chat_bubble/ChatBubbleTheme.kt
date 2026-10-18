package ir.aotol.app.ui.components.preline.chat_bubble

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.aotol.app.ui.components.preline.Palette
import ir.aotol.app.ui.components.preline.PrelineTheme
import ir.aotol.app.ui.components.preline.medium

data class ChatBubbleTheme(
    val replyBackground: Color,
    val regularBackground: Color,
    val replyBorder: Color,
    val regularBorder: Color,
    val replyTextColor: Color,
    val regularTextColor: Color,
    val notSentTextColor: Color,
    val sentTextColor: Color,
    val shape: Shape,
    val messageStyle: TextStyle,
    val statusStyle: TextStyle,
    val statusIconSize: Dp,
) {
    sealed interface ItemStatus {
        object None : ItemStatus
        object NotSent : ItemStatus
        object Sent : ItemStatus
    }

    sealed interface Type {
        object Mirrored : Type
        object OneSided : Type
    }
}

@Composable
@ReadOnlyComposable
internal fun getTheme(): ChatBubbleTheme = ChatBubbleTheme(
    replyBackground = PrelineTheme.colors.primary,
    regularBackground = Palette.Transparent,
    replyBorder = Palette.Transparent,
    regularBorder = PrelineTheme.colors.layerBorder,
    replyTextColor = PrelineTheme.colors.primaryForeground,
    regularTextColor = PrelineTheme.colors.layerForeground,
    notSentTextColor = PrelineTheme.colors.destructive,
    sentTextColor = PrelineTheme.colors.mutedForeground1,
    shape = RoundedCornerShape(16.dp),
    messageStyle = PrelineTheme.typography.base.medium,
    statusStyle = PrelineTheme.typography.xs.medium,
    statusIconSize = 12.dp
)
