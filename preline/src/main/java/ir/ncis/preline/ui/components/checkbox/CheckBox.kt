package ir.ncis.preline.ui.components.checkbox

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.helpers.disabledAlpha
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium

@Composable
fun Checkbox(
    label: String,
    modifier: Modifier = Modifier,
    secondaryLabel: String? = null,
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    leading: Boolean = true,
    checked: Boolean = true,
    type: CheckboxTheme.Type = CheckboxTheme.Type.Solid,
    style: CheckboxTheme.Style = CheckboxTheme.Style.Plain,
    state: CheckboxTheme.State = CheckboxTheme.State.Default,
    size: CheckboxTheme.Size = CheckboxTheme.Size.Default,
    onCheckedChange: ((Boolean) -> Unit)? = null
) {
    val theme = getTheme(checked, type, style, state, size)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .disabledAlpha(state == CheckboxTheme.State.Disabled)
            .toggleable(value = checked, enabled = state != CheckboxTheme.State.Disabled, role = Role.Checkbox, interactionSource = remember { MutableInteractionSource() }, indication = null) { onCheckedChange?.invoke(it) },
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

            if (leading) CheckboxView(checked = checked, type = type, style = style, state = state, size = size, theme = theme)

            Column(modifier = Modifier.weight(1f)) {
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

            if (!leading) CheckboxView(checked = checked, type = type, style = style, state = state, size = size, theme = theme)

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
private fun CheckboxView(checked: Boolean, type: CheckboxTheme.Type, style: CheckboxTheme.Style, state: CheckboxTheme.State, size: CheckboxTheme.Size, theme: CheckboxTheme) {
    Card(
        modifier = Modifier
            .then(if (state == CheckboxTheme.State.Focus) Modifier.border(2.dp, theme.focusRingColor, RoundedCornerShape(6.dp)).padding(2.dp) else Modifier)
            .size(theme.checkboxSize),
        colors = CardDefaults.cardColors(containerColor = theme.checkboxBackground),
        border = BorderStroke(1.dp, theme.checkboxStroke),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = theme.checkboxShape
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (state == CheckboxTheme.State.Indeterminate) {
                Icon(
                    painter = painterResource(R.drawable.ic_minus),
                    contentDescription = null,
                    modifier = Modifier.size(theme.checkboxIconSize),
                    tint = theme.checkboxForeground
                )
            } else {
                if (checked) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null,
                        modifier = Modifier.size(theme.checkboxIconSize),
                        tint = theme.checkboxForeground
                    )
                }
            }
        }
    }
}
