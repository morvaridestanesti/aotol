package ir.aotol.app.ui.components.preline.chat_bubble

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ir.aotol.app.ui.components.preline.chat_bubble.data.ChatBubbleItem

@Composable
fun ChatBubble(
    items: List<ChatBubbleItem>,
    modifier: Modifier = Modifier,
    type: ChatBubbleTheme.Type = ChatBubbleTheme.Type.Mirrored
) {
    Column(modifier = modifier) {
        items.forEach { item ->
            if (type == ChatBubbleTheme.Type.OneSided) {
                ChatItem(item = item)
            } else {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = if (item.isReply) Alignment.TopEnd else Alignment.TopStart
                ) { ChatItem(item = item) }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 600)
@Composable
private fun Preview() {
    val item = ChatBubbleItem(
        message = "Preline UI is an open-source set of prebuilt UI components based on the utility-first Tailwind CSS framework.",
        avatarPlaceholder = "John Smith",
    )
    ChatBubble(
        items = listOf(
            item.copy(status = ChatBubbleTheme.ItemStatus.Sent),
            item.copy(avatarPlaceholder = "Admin", isReply = true, status = ChatBubbleTheme.ItemStatus.Sent),
            item,
            item.copy(avatarPlaceholder = "Admin", isReply = true, status = ChatBubbleTheme.ItemStatus.NotSent),
        ),
        modifier = Modifier.fillMaxWidth(),
        type = ChatBubbleTheme.Type.Mirrored
    )
}