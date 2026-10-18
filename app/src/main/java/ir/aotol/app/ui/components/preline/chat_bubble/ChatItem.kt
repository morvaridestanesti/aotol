package ir.aotol.app.ui.components.preline.chat_bubble

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.aotol.app.R
import ir.aotol.app.ui.components.preline.avatar.Avatar
import ir.aotol.app.ui.components.preline.avatar.AvatarTheme
import ir.aotol.app.ui.components.preline.chat_bubble.data.ChatBubbleItem

@Composable
fun ChatItem(item: ChatBubbleItem) {
    val theme = getTheme()
    Row(
        modifier = Modifier.fillMaxWidth(0.75f),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val hasAvatar = listOf(item.avatarPlaceholder, item.avatarUrl, item.avatarPainter).any { it != null }
        if (hasAvatar && !item.isReply) {
            Avatar(
                placeholder = item.avatarPlaceholder,
                imageUrl = item.avatarUrl,
                imagePainter = item.avatarPainter,
                shape = AvatarTheme.Shape.Circular
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = if (item.isReply) Alignment.End else Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(if (item.isReply) theme.replyBackground else theme.regularBackground),
                border = BorderStroke(1.dp, if (item.isReply) theme.replyBorder else theme.regularBorder),
                elevation = CardDefaults.cardElevation(0.dp),
                shape = theme.shape
            ) {
                Text(
                    text = item.message,
                    color = if (item.isReply) theme.replyTextColor else theme.regularTextColor,
                    style = theme.messageStyle,
                    modifier = Modifier.padding(20.dp)
                )
            }
            when (item.status) {
                ChatBubbleTheme.ItemStatus.None    -> {}
                ChatBubbleTheme.ItemStatus.NotSent -> Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.ic_info),
                        contentDescription = null,
                        modifier = Modifier.size(theme.statusIconSize),
                        tint = theme.notSentTextColor
                    )
                    Text(
                        text = "Not Sent",
                        color = theme.notSentTextColor,
                        style = theme.statusStyle,
                    )
                }

                ChatBubbleTheme.ItemStatus.Sent    -> Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check_double),
                        contentDescription = null,
                        modifier = Modifier.size(theme.statusIconSize),
                        tint = theme.sentTextColor
                    )
                    Text(
                        text = "Sent",
                        color = theme.sentTextColor,
                        style = theme.statusStyle,
                    )
                }
            }
        }
        if (hasAvatar && item.isReply) {
            Avatar(
                placeholder = item.avatarPlaceholder,
                imageUrl = item.avatarUrl,
                imagePainter = item.avatarPainter,
                shape = AvatarTheme.Shape.Circular
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 600)
@Composable
private fun Preview() = ChatItem(
    ChatBubbleItem(
        message = "Preline UI is an open-source set of prebuilt UI components based on the utility-first Tailwind CSS framework.",
        avatarPlaceholder = "John Smith",
        avatarPainter = null,
        avatarUrl = null,
        isReply = true,
        status = ChatBubbleTheme.ItemStatus.None,
    )
)
