package ir.aotol.app.ui.components.preline.badge

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.aotol.app.R
import ir.aotol.app.helperes.invisibleClickable
import ir.aotol.app.ui.components.preline.badge.BadgeTheme.Color
import ir.aotol.app.ui.components.preline.badge.BadgeTheme.Shape
import ir.aotol.app.ui.components.preline.badge.BadgeTheme.Size
import ir.aotol.app.ui.components.preline.badge.BadgeTheme.Type

@Composable
fun Badge(
    modifier: Modifier = Modifier,
    avatar: Painter? = null,
    leadingIcon: Painter? = null,
    status: Boolean = false,
    placeholder: String? = null,
    trailingIcon: Painter? = null,
    type: Type = Type.Soft,
    color: Color = Color.Green,
    size: Size = Size.Default,
    shape: Shape = Shape.Rounded,
    onCloseClick: (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    val theme = getTheme(type, color, size, shape)

    Card(
        modifier = modifier.then(onClick?.let { Modifier.invisibleClickable { it() } } ?: Modifier),
        colors = CardDefaults.cardColors(theme.background),
        border = BorderStroke(1.dp, theme.stroke),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = theme.shape
    ) {
        Row(
            modifier = Modifier.padding(
                vertical = theme.containerVerticalPadding,
                horizontal = theme.containerHorizontalPadding
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            avatar?.let { image ->
                Card(
                    modifier = Modifier.size(theme.avatarSize),
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(0.dp),
                    colors = CardDefaults.cardColors(theme.background)
                ) {
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            leadingIcon?.let { icon ->
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(theme.leadingIconSize),
                    tint = theme.foregroundColor
                )
            }

            if (status) {
                Icon(
                    painter = painterResource(R.drawable.aotol),
                    contentDescription = null,
                    modifier = Modifier.size(8.dp),
                    tint = theme.foregroundColor
                )
            }

            placeholder?.let { label ->
                Text(
                    text = label,
                    color = theme.foregroundColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            trailingIcon?.let { icon ->
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(theme.trailingIconSize),
                    tint = theme.foregroundColor
                )
            }

            onCloseClick?.let {
                Card(
                    modifier = Modifier
                        .size(theme.closeIconSize)
                        .invisibleClickable { it() },
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(0.dp),
                    colors = CardDefaults.cardColors(theme.closeIconBackground),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.aotol),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(2.dp),
                        tint = theme.closeIconForegroundColor
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BadgePlaceholder() {
    Badge(
        placeholder = "Badge"
    )
}

@Preview
@Composable
private fun BadgeAvatarPlaceholder() {
    Badge(
        placeholder = "Badge",
        avatar = painterResource(R.drawable.aotol)
    )
}

@Preview
@Composable
private fun BadgePlaceholderLeadingIcon() {
    Badge(
        placeholder = "Badge",
        leadingIcon = painterResource(R.drawable.aotol)
    )
}

@Preview
@Composable
private fun BadgePlaceholderTrailingIcon() {
    Badge(
        placeholder = "Badge",
        trailingIcon = painterResource(R.drawable.aotol)
    )
}

@Preview
@Composable
private fun BadgePlaceholderStatus() {
    Badge(
        placeholder = "Badge",
        status = true
    )
}

@Preview
@Composable
private fun BadgePlaceholderCloseIcon() {
    Badge(
        placeholder = "Badge",
        onCloseClick = {}
    )
}
