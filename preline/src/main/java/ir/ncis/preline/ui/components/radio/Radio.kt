package ir.ncis.preline.ui.components.radio

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ir.ncis.preline.helpers.disabledAlpha
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium

@Composable
fun Radio(
    label: String,
    modifier: Modifier = Modifier,
    secondaryLabel: String? = null,
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    leading: Boolean = true,
    checked: Boolean = true,
    type: RadioTheme.Type = RadioTheme.Type.Solid,
    style: RadioTheme.Style = RadioTheme.Style.Plain,
    state: RadioTheme.State = RadioTheme.State.Default,
    size: RadioTheme.Size = RadioTheme.Size.Default,
    onCheckedChange: ((Boolean) -> Unit)? = null
) {
    val theme = getTheme(checked, type, style, state, size)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .disabledAlpha(state == RadioTheme.State.Disabled)
            .selectable(selected = checked, enabled = state != RadioTheme.State.Disabled, role = Role.RadioButton, interactionSource = remember { MutableInteractionSource() }, indication = null) { onCheckedChange?.invoke(!checked) },
        colors = CardDefaults.cardColors(theme.cardBackground),
        border = BorderStroke(1.dp, theme.cardBorder),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = theme.shape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(theme.rowPadding),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            leadingIcon?.let {
                Icon(
                    painter = it,
                    contentDescription = null,
                    modifier = Modifier.size(theme.iconSize),
                    tint = theme.iconTint
                )
            }

            if (leading) RadioView(checked = checked, type = type, style = style, state = state, size = size, theme = theme)

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = label,
                    color = theme.labelColor,
                    style = PrelineTheme.typography.base.medium,
                )

                secondaryLabel?.let {
                    Text(
                        text = it,
                        color = theme.secondaryLabelColor,
                        style = PrelineTheme.typography.base.medium,
                    )
                }
            }

            if (!leading) RadioView(checked = checked, type = type, style = style, state = state, size = size, theme = theme)

            trailingIcon?.let {
                Icon(
                    painter = it,
                    contentDescription = null,
                    modifier = Modifier.size(theme.iconSize),
                    tint = theme.iconTint
                )
            }
        }
    }
}

@Composable
private fun RadioView(checked: Boolean, type: RadioTheme.Type, style: RadioTheme.Style, state: RadioTheme.State, size: RadioTheme.Size, theme: RadioTheme) {
    Card(
        modifier = Modifier
            .then(if (state == RadioTheme.State.Focus) Modifier.border(2.dp, theme.focusRingColor, CircleShape).padding(2.dp) else Modifier)
            .size(theme.radioSize),
        colors = CardDefaults.cardColors(containerColor = theme.radioBackground),
        border = BorderStroke(1.dp, theme.strokeColor),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = theme.radioShape
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                Box(
                    modifier = Modifier
                        .size(theme.radioIconSize)
                        .background(
                            color = theme.dotColor,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}
