package ir.aotol.app.ui.components.preline.button_icon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.aotol.app.R
import ir.aotol.app.helpers.disabledAlpha
import ir.aotol.app.ui.components.preline.PrelineTheme
import ir.aotol.app.ui.components.preline.medium
import ir.aotol.app.ui.components.preline.semiBold

@Composable
fun ButtonIcon(
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    avatar: Painter? = null,
    placeholder: String? = null,
    badge: String? = null,
    type: ButtonIconTheme.Type = ButtonIconTheme.Type.Solid,
    color: ButtonIconTheme.Color = ButtonIconTheme.Color.Dark,
    state: ButtonIconTheme.State = ButtonIconTheme.State.Default,
    size: ButtonIconTheme.Size = ButtonIconTheme.Size.Default,
    shape: ButtonIconTheme.Shape = ButtonIconTheme.Shape.Rounded,
    loading: Boolean = false,
    onClick: () -> Unit,
) {
    val theme = getTheme(type, color, state, size, shape)
    // A loading button keeps its look but ignores taps until the work is done.
    val clickable = state != ButtonIconTheme.State.Disabled && !loading

    Box(modifier = modifier.disabledAlpha(state == ButtonIconTheme.State.Disabled)) {
        Box(modifier = Modifier.then(badge?.let { Modifier.padding(top = 10.dp) } ?: Modifier)) {
            Card(
                modifier = Modifier
                    .padding(end = badge?.let { 10.dp } ?: 0.dp)
                    .size(theme.height)
                    .align(Alignment.BottomEnd)
                    .clickable(enabled = clickable, role = Role.Button) { onClick() },
                colors = CardDefaults.cardColors(theme.background),
                border = BorderStroke(if (state == ButtonIconTheme.State.Focus) 2.dp else 1.dp, theme.stroke),
                elevation = CardDefaults.cardElevation(0.dp),
                shape = theme.shape
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(theme.padding),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(theme.iconSize),
                            color = theme.foreground,
                            strokeWidth = 2.dp
                        )
                    } else placeholder?.let { label ->
                        Text(
                            text = label,
                            color = theme.foreground,
                            style = PrelineTheme.typography.sm.semiBold,
                        )
                    }
                    if (!loading) avatar?.let { av ->
                        Image(
                            painter = av,
                            contentDescription = null,
                            modifier = Modifier.size(theme.iconSize)
                        )
                    }
                    if (!loading) icon?.let { ic ->
                        Icon(
                            painter = ic,
                            contentDescription = null,
                            modifier = Modifier.size(theme.iconSize),
                            tint = theme.foreground
                        )
                    }
                }
            }
            badge?.let { bd ->
                Card(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(y = (-10).dp),
                    colors = CardDefaults.cardColors(theme.badgeBackground),
                    border = BorderStroke(1.dp, theme.badgeBorder),
                    elevation = CardDefaults.cardElevation(2.dp),
                    shape = theme.badgeShape
                ) {
                    Text(
                        text = bd,
                        color = theme.badgeForeground,
                        style = PrelineTheme.typography.xs.medium,
                        modifier = Modifier.padding(horizontal = 4.dp),
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ButtonIcon(
        icon = painterResource(R.drawable.ic_home),
        placeholder = "Button",
        type = ButtonIconTheme.Type.Solid,
        color = ButtonIconTheme.Color.Blue,
        state = ButtonIconTheme.State.Default,
        size = ButtonIconTheme.Size.Default,
        badge = "5"
    ) {}
}
