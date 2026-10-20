package ir.ncis.preline.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ir.ncis.preline.helpers.disabledAlpha
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium

@Composable
fun Button(
    modifier: Modifier = Modifier,
    style: ButtonTheme.Style = ButtonTheme.Style.Solid,
    color: ButtonTheme.Color = ButtonTheme.Color.Dark,
    state: ButtonTheme.State = ButtonTheme.State.Default,
    size: ButtonTheme.Size = ButtonTheme.Size.Default,
    shape: ButtonTheme.Shape = ButtonTheme.Shape.Rounded,
    loading: Boolean = false,
    leadingIcon: Painter? = null,
    leadingAvatar: Painter? = null,
    leadingDivider: Boolean = false,
    placeholder: String? = null,
    badge: Int? = null,
    trailingIcon: Painter? = null,
    trailingAvatar: Painter? = null,
    trailingDivider: Boolean = false,
    onClick: () -> Unit,
) {
    val theme = getTheme(style, color, state, size, shape)
    val clickable = state != ButtonTheme.State.Disabled && !loading

    Card(
        modifier = modifier
            .disabledAlpha(state == ButtonTheme.State.Disabled)
            .clickable(enabled = clickable, role = Role.Button, interactionSource = remember { MutableInteractionSource() }, indication = null) { onClick() },
        colors = CardDefaults.cardColors(theme.background),
        border = BorderStroke(1.dp, theme.stroke),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = theme.shape
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(IntrinsicSize.Min)
                .padding(vertical = theme.verticalPadding, horizontal = theme.horizontalPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(14.dp),
                    color = theme.foreground,
                    strokeWidth = 2.dp
                )
            }
            leadingIcon?.let { icon ->
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = theme.foreground
                )
            }
            leadingAvatar?.let { avatar ->
                Image(
                    painter = avatar,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
            if (leadingDivider) {
                VerticalDivider(
                    modifier = Modifier.fillMaxHeight(),
                    thickness = 1.dp,
                    color = theme.foreground.copy(alpha = 0.2f)
                )
            }
            placeholder?.let { label ->
                Text(
                    text = label,
                    color = theme.foreground,
                    style = PrelineTheme.typography.sm.medium,
                )
            }
            if (trailingDivider) {
                VerticalDivider(
                    modifier = Modifier.fillMaxHeight(),
                    thickness = 1.dp,
                    color = theme.foreground.copy(alpha = 0.2f)
                )
            }
            trailingAvatar?.let { avatar ->
                Image(
                    painter = avatar,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
            trailingIcon?.let { icon ->
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = theme.foreground
                )
            }
            badge?.let { count ->
                Card(
                    colors = CardDefaults.cardColors(theme.foreground),
                    elevation = CardDefaults.cardElevation(0.dp),
                    shape = CircleShape
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = count.toString(),
                            textAlign = TextAlign.Center,
                            color = if (theme.background.alpha < 1f) PrelineTheme.colors.layer else theme.background,
                            style = PrelineTheme.typography.xs,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                    }
                }
            }
        }
    }
}
