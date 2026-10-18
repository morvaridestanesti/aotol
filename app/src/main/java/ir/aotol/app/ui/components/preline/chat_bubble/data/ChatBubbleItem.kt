package ir.aotol.app.ui.components.preline.chat_bubble.data

import androidx.compose.ui.graphics.painter.Painter
import ir.aotol.app.ui.components.preline.chat_bubble.ChatBubbleTheme

data class ChatBubbleItem(
    val message: String,
    val avatarPainter: Painter? = null,
    val avatarPlaceholder: String? = null,
    val avatarUrl: String? = null,
    val isReply: Boolean = false,
    val status: ChatBubbleTheme.ItemStatus = ChatBubbleTheme.ItemStatus.None,
)
