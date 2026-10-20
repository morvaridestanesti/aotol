package ir.ncis.preline.ui.components.chat_bubble

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.components.chat_bubble.data.ChatBubbleItem

@Composable
fun ChatBubble(
    items: List<ChatBubbleItem>,
    modifier: Modifier = Modifier,
    type: ChatBubbleTheme.Type = ChatBubbleTheme.Type.Mirrored,
    sentLabel: String = "Sent",
    notSentLabel: String = "Not Sent",
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items.forEach { item ->
            if (type == ChatBubbleTheme.Type.OneSided) {
                ChatItem(item = item, sentLabel = sentLabel, notSentLabel = notSentLabel, alignToEnd = false)
            } else {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = if (item.isReply) Alignment.TopEnd else Alignment.TopStart
                ) { ChatItem(item = item, sentLabel = sentLabel, notSentLabel = notSentLabel) }
            }
        }
    }
}
