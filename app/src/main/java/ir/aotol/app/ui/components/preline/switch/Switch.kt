package ir.aotol.app.ui.components.preline.switch

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.aotol.app.R
import ir.aotol.app.helpers.disabledAlpha
import ir.aotol.app.ui.components.preline.PrelineTheme

@Composable
fun Switch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    type: SwitchTheme.Type = SwitchTheme.Type.Solid,
    state: SwitchTheme.State = SwitchTheme.State.Default,
    size: SwitchTheme.Size = SwitchTheme.Size.Small,
    shape: SwitchTheme.Shape = SwitchTheme.Shape.Pilled,
    offLabel: String? = null,
    onLabel: String? = null,
    offIcon: Painter? = null,
    onIcon: Painter? = null,
) {
    val theme = getTheme(checked, type, state, size, shape)
    val disabled = state == SwitchTheme.State.Disabled
    // -1 is the start of the track, 1 the end; animating it slides the thumb.
    val thumbBias by animateFloatAsState(if (checked) 1f else -1f, label = "thumbBias")

    Row(
        modifier = modifier.disabledAlpha(disabled),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        offLabel?.let { Text(text = it, color = theme.labelColor, style = PrelineTheme.typography.sm) }

        Box(
            modifier = Modifier
                .then(if (state == SwitchTheme.State.Focus) Modifier.border(2.dp, theme.focusRingColor, theme.trackShape).padding(2.dp) else Modifier)
                .size(width = theme.trackWidth, height = theme.trackHeight)
                .clip(theme.trackShape)
                .background(theme.trackColor)
                .toggleable(
                    value = checked,
                    enabled = !disabled,
                    role = Role.Switch,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onValueChange = onCheckedChange
                )
        ) {
            // The icon of the side the thumb is not on stays visible in the track.
            (if (checked) offIcon else onIcon)?.let {
                Icon(
                    painter = it,
                    contentDescription = null,
                    tint = theme.trackIconColor,
                    modifier = Modifier
                        .align(if (checked) Alignment.CenterStart else Alignment.CenterEnd)
                        .padding(horizontal = 2.dp + (theme.thumbSize - theme.iconSize) / 2)
                        .size(theme.iconSize)
                )
            }
            Box(
                modifier = Modifier
                    .align(BiasAlignment(thumbBias, 0f))
                    .padding(2.dp)
                    .size(theme.thumbSize)
                    .background(theme.thumbColor, theme.thumbShape),
                contentAlignment = Alignment.Center
            ) {
                (if (checked) onIcon else offIcon)?.let {
                    Icon(painter = it, contentDescription = null, tint = theme.thumbIconColor, modifier = Modifier.size(theme.iconSize))
                }
            }
        }

        onLabel?.let { Text(text = it, color = theme.labelColor, style = PrelineTheme.typography.sm) }
    }
}

@Preview(showBackground = true)
@Composable
private fun SolidPreview() {
    var checked by remember { mutableStateOf(true) }
    Switch(
        checked = checked,
        onCheckedChange = { checked = it },
        offLabel = "Off",
        onLabel = "On",
        offIcon = painterResource(R.drawable.ic_clear),
        onIcon = painterResource(R.drawable.ic_check),
    )
}

@Preview(showBackground = true)
@Composable
private fun StatesPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        listOf(SwitchTheme.State.Default, SwitchTheme.State.Focus, SwitchTheme.State.Disabled, SwitchTheme.State.Invalid, SwitchTheme.State.Success).forEach { state ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(SwitchTheme.Type.Solid, SwitchTheme.Type.Soft).forEach { type ->
                    Switch(checked = false, onCheckedChange = {}, type = type, state = state, offIcon = painterResource(R.drawable.ic_clear), onIcon = painterResource(R.drawable.ic_check))
                    Switch(checked = true, onCheckedChange = {}, type = type, state = state, offIcon = painterResource(R.drawable.ic_clear), onIcon = painterResource(R.drawable.ic_check))
                }
            }
        }
    }
}
