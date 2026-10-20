package ir.ncis.preline.ui.components.alert

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.helpers.invisibleClickable
import ir.ncis.preline.ui.components.alert.data.AlertButton
import ir.ncis.preline.ui.components.styled_icon.StyledIcon
import ir.ncis.preline.ui.components.styled_icon.StyledIconTheme
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.semiBold

@Composable
fun Alert(
    modifier: Modifier = Modifier,
    featuredIcon: Painter? = null,
    avatar: Painter? = null,
    leadingIcon: Painter? = null,
    title: String? = null,
    subtitle: String? = null,
    messageContent: String? = null,
    listItems: List<String> = emptyList(),
    button1: AlertButton? = null,
    button2: AlertButton? = null,
    trailingLink: AlertButton? = null,
    type: AlertTheme.Type = AlertTheme.Type.Solid,
    color: AlertTheme.Color = AlertTheme.Color.Dark,
    accentBorder: AlertTheme.AccentBorder? = null,
    onClose: (() -> Unit)? = null
) {
    val theme = getTheme(type, color)
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(theme.container),
        border = BorderStroke(1.dp, theme.stroke),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        if (accentBorder == AlertTheme.AccentBorder.Top) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(theme.accent)
            )
        }
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            if (accentBorder == AlertTheme.AccentBorder.Start) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .fillMaxHeight()
                        .background(theme.accent)
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                AlertContent(featuredIcon, avatar, leadingIcon, title, subtitle, messageContent, listItems, button1, button2, trailingLink, onClose, theme)
                onClose?.let {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 8.dp, end = 8.dp)
                            .size(24.dp)
                            .invisibleClickable(role = Role.Button) { it() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_clear),
                            contentDescription = stringResource(R.string.preline_close),
                            modifier = Modifier.size(16.dp),
                            tint = theme.secondaryForeground
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AlertContent(
    featuredIcon: Painter?,
    avatar: Painter?,
    leadingIcon: Painter?,
    title: String?,
    subtitle: String?,
    messageContent: String?,
    listItems: List<String>,
    button1: AlertButton?,
    button2: AlertButton?,
    trailingLink: AlertButton?,
    onClose: (() -> Unit)?,
    theme: AlertTheme,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(end = if (onClose != null) 20.dp else 0.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        featuredIcon?.let {
            StyledIcon(
                icon = it,
                type = StyledIconTheme.Type.SoftOutlined,
                color = theme.featuredIcon,
                size = StyledIconTheme.Size.Small,
                shape = StyledIconTheme.Shape.Circular
            )
        }
        avatar?.let {
            Card(
                modifier = Modifier.size(32.dp),
                colors = CardDefaults.cardColors(Palette.White),
                elevation = CardDefaults.cardElevation(0.dp),
                shape = CircleShape
            ) {
                Image(
                    painter = it,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
        leadingIcon?.let {
            Icon(
                painter = it,
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 2.dp)
                    .size(16.dp),
                tint = theme.leadingIcon
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (title != null || subtitle != null || trailingLink != null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    title?.let {
                        Text(
                            text = it,
                            modifier = Modifier.weight(1f, fill = trailingLink != null),
                            color = theme.primaryForeground,
                            style = PrelineTheme.typography.sm.semiBold,
                        )
                    }
                    subtitle?.let {
                        Text(
                            text = it,
                            color = theme.secondaryForeground,
                            style = PrelineTheme.typography.xs,
                        )
                    }
                    trailingLink?.let { AlertAction(it, theme.leadingIcon, showChevron = false) }
                }
            }
            messageContent?.let {
                Text(
                    text = it,
                    color = theme.secondaryForeground,
                    style = PrelineTheme.typography.sm,
                )
            }
            listItems.forEach {
                Row(modifier = Modifier.padding(start = 4.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = "•", color = theme.secondaryForeground, style = PrelineTheme.typography.sm)
                    Text(text = it, color = theme.secondaryForeground, style = PrelineTheme.typography.sm)
                }
            }
            if (button1 != null || button2 != null) {
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    button1?.let { AlertAction(it, theme.leadingIcon, showChevron = true) }
                    button2?.let { AlertAction(it, theme.primaryForeground, showChevron = false) }
                }
            }
        }
    }
}

@Composable
private fun AlertAction(button: AlertButton, color: Color, showChevron: Boolean) {
    Row(
        modifier = Modifier.clickable(role = Role.Button) { button.onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = button.title, color = color, style = PrelineTheme.typography.sm.semiBold)
        if (showChevron) {
            Icon(
                painter = painterResource(R.drawable.ic_chevron_forward),
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = color
            )
        }
    }
}
