package ir.ncis.preline.ui.components.chat_bubble

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
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.ui.components.avatar.Avatar
import ir.ncis.preline.ui.components.avatar.AvatarTheme
import ir.ncis.preline.ui.components.chat_bubble.data.ChatBubbleItem

@Composable
fun ChatItem(item: ChatBubbleItem, sentLabel: String = "Sent", notSentLabel: String = "Not Sent", alignToEnd: Boolean = item.isReply) {
    val theme = getTheme()
    Row(
        modifier = Modifier.fillMaxWidth(0.75f),
        horizontalArrangement = Arrangement.spacedBy(12.dp, if (alignToEnd) Alignment.End else Alignment.Start)
    ) {
        val hasAvatar = listOf(item.avatarPlaceholder, item.avatarUrl, item.avatarPainter).any { it != null }
        if (hasAvatar && !item.isReply) {
            Avatar(
                placeholder = item.avatarPlaceholder,
                imageUrl = item.avatarUrl,
                imagePainter = item.avatarPainter,
                size = AvatarTheme.Size.Small,
                shape = AvatarTheme.Shape.Circular
            )
        }
        Column(
            modifier = Modifier.weight(1f, fill = false),
            horizontalAlignment = if (item.isReply) Alignment.End else Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            val textColor = if (item.isReply) theme.replyTextColor else theme.regularTextColor
            Card(
                colors = CardDefaults.cardColors(if (item.isReply) theme.replyBackground else theme.regularBackground),
                border = BorderStroke(1.dp, if (item.isReply) theme.replyBorder else theme.regularBorder),
                elevation = CardDefaults.cardElevation(0.dp),
                shape = theme.shape
            ) {
                CompositionLocalProvider(
                    LocalContentColor provides textColor,
                    LocalTextStyle provides theme.messageStyle
                ) {
                    Column(
                        modifier = Modifier.padding(theme.padding),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item.message?.let {
                            Text(
                                text = it,
                                color = textColor,
                                style = if (item.isReply) theme.replyMessageStyle else theme.messageStyle,
                            )
                        }
                        item.content?.invoke(this)
                    }
                }
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
                        text = notSentLabel,
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
                        text = sentLabel,
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
                type = AvatarTheme.Type.Solid,
                color = AvatarTheme.Color.Gray,
                size = AvatarTheme.Size.Small,
                shape = AvatarTheme.Shape.Circular
            )
        }
    }
}
