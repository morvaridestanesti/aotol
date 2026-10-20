package ir.ncis.preline.ui.components.chat_bubble.data

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import ir.ncis.preline.ui.components.chat_bubble.ChatBubbleTheme

data class ChatBubbleItem(
    val message: String? = null,
    val avatarPainter: Painter? = null,
    val avatarPlaceholder: String? = null,
    val avatarUrl: String? = null,
    val isReply: Boolean = false,
    val status: ChatBubbleTheme.ItemStatus = ChatBubbleTheme.ItemStatus.None,
    val content: (@Composable ColumnScope.() -> Unit)? = null,
)
