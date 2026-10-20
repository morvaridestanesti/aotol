package ir.ncis.preline.ui.components.badge

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium

@Composable
fun Badge(
    modifier: Modifier = Modifier,
    avatar: Painter? = null,
    leadingIcon: Painter? = null,
    status: Boolean = false,
    placeholder: String? = null,
    trailingIcon: Painter? = null,
    type: BadgeTheme.Type = BadgeTheme.Type.Soft,
    color: BadgeTheme.Color = BadgeTheme.Color.Green,
    size: BadgeTheme.Size = BadgeTheme.Size.Default,
    shape: BadgeTheme.Shape = BadgeTheme.Shape.Rounded,
    onCloseClick: (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    val theme = getTheme(type, color, size, shape)

    Card(
        modifier = modifier
            .then(onClick?.let { Modifier.clickable(role = Role.Button) { it() } } ?: Modifier),
        colors = CardDefaults.cardColors(theme.background),
        border = BorderStroke(1.dp, theme.stroke),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = theme.shape
    ) {
        Row(
            modifier = Modifier
                .heightIn(min = theme.height)
                .widthIn(min = theme.height)
                .padding(horizontal = theme.containerHorizontalPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
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
                    painter = painterResource(R.drawable.ic_circle),
                    contentDescription = null,
                    modifier = Modifier.size(6.dp),
                    tint = theme.foregroundColor
                )
            }

            placeholder?.let { label ->
                Text(
                    text = label,
                    color = theme.foregroundColor,
                    style = PrelineTheme.typography.xs.medium,
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
                        .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null, role = Role.Button) { it() },
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(0.dp),
                    colors = CardDefaults.cardColors(theme.closeIconBackground),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_clear),
                        contentDescription = stringResource(R.string.preline_remove),
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
