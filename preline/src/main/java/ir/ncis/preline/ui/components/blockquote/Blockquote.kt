package ir.ncis.preline.ui.components.blockquote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.ui.components.avatar.Avatar
import ir.ncis.preline.ui.components.avatar.AvatarTheme

@Composable
fun Blockquote(
    placeholder: String,
    authorTitle: String,
    modifier: Modifier = Modifier,
    type: BlockquoteTheme.Type = BlockquoteTheme.Type.Avatar,
    size: BlockquoteTheme.Size = BlockquoteTheme.Size.Large,
    sourceTitle: String? = null,
    sourceAvatar: Painter? = null,
) {
    val theme = getTheme(type, size)
    Box(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(intrinsicSize = IntrinsicSize.Min)
        ) {
            if (type == BlockquoteTheme.Type.LeftBorder) {
                VerticalDivider(
                    modifier = Modifier.fillMaxHeight(),
                    thickness = theme.leftBorderThickness,
                    color = theme.leftBorderColor
                )
                Spacer(Modifier.width(theme.leftBorderSpacing))
            }
            if (type == BlockquoteTheme.Type.Quote) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_quote),
                        contentDescription = null,
                        modifier = Modifier.size(theme.quoteSize),
                        tint = theme.quoteColor
                    )
                    Box(modifier = Modifier.padding(start = theme.quoteStartPadding, top = theme.quoteTopPadding)) {
                        Contents(placeholder, type, size, sourceAvatar, authorTitle, sourceTitle, theme)
                    }
                }
            } else {
                Contents(placeholder, type, size, sourceAvatar, authorTitle, sourceTitle, theme)
            }
        }
    }
}

@Composable
private fun Contents(placeholder: String, type: BlockquoteTheme.Type, size: BlockquoteTheme.Size, sourceAvatar: Painter?, authorTitle: String, sourceTitle: String?, theme: BlockquoteTheme) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = placeholder,
            textAlign = theme.textAlign,
            color = theme.textColor,
            style = theme.placeholderStyle,
        )
        Spacer(Modifier.height(theme.contentSpacerHeight))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp, if (type == BlockquoteTheme.Type.Centered) Alignment.CenterHorizontally else Alignment.Start)
        ) {
            sourceAvatar?.let {
                Avatar(
                    imagePainter = it,
                    size = theme.avatarSize,
                    shape = AvatarTheme.Shape.Circular
                )
            }
            Column {
                Text(
                    text = authorTitle,
                    color = theme.authorColor,
                    style = theme.authorStyle,
                )
                sourceTitle?.let {
                    Text(
                        text = it,
                        color = theme.sourceTitleColor,
                        style = theme.sourceTitleStyle,
                    )
                }
            }
        }
    }
}
