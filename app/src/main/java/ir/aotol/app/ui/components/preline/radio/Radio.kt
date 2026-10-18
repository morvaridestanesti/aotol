package ir.aotol.app.ui.components.preline.radio

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.aotol.app.R
import ir.aotol.app.helpers.disabledAlpha
import ir.aotol.app.ui.components.preline.PrelineTheme
import ir.aotol.app.ui.components.preline.medium

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
            .clickable(enabled = state != RadioTheme.State.Disabled, interactionSource = remember { MutableInteractionSource() }, indication = null) { onCheckedChange?.invoke(!checked) },
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

@Preview(showBackground = true)
@Composable
private fun Normal() {
    val checked = remember { mutableStateOf(false) }

    Radio(
        label = "Title",
        secondaryLabel = "Description text",
        checked = checked.value,
        type = RadioTheme.Type.Solid,
        style = RadioTheme.Style.Plain,
        state = RadioTheme.State.Default,
        size = RadioTheme.Size.Default
    ) {
        checked.value = it
    }
}

@Preview(showBackground = true)
@Composable
private fun Focus() {
    val checked = remember { mutableStateOf(false) }

    Radio(
        label = "Focus",
        type = RadioTheme.Type.Solid,
        style = RadioTheme.Style.Plain,
        state = RadioTheme.State.Focus,
        size = RadioTheme.Size.Default,
        checked = checked.value
    ) {
        checked.value = it
    }
}

@Preview(showBackground = true)
@Composable
private fun Disabled() {
    Radio(
        label = "Disabled",
        type = RadioTheme.Type.Solid,
        style = RadioTheme.Style.Plain,
        state = RadioTheme.State.Disabled,
        size = RadioTheme.Size.Default,
        checked = false
    )
}

@Preview(showBackground = true)
@Composable
private fun CheckedDisabled() {
    Radio(
        label = "Checked Disabled",
        type = RadioTheme.Type.Solid,
        style = RadioTheme.Style.Plain,
        state = RadioTheme.State.Disabled,
        size = RadioTheme.Size.Default
    )
}

@Preview(showBackground = true)
@Composable
private fun Success() {
    Radio(
        label = "Success",
        type = RadioTheme.Type.Solid,
        style = RadioTheme.Style.Plain,
        state = RadioTheme.State.Success,
        size = RadioTheme.Size.Default
    )
}

@Preview(showBackground = true)
@Composable
private fun Invalid() {
    Radio(
        label = "Invalid",
        type = RadioTheme.Type.Solid,
        style = RadioTheme.Style.Plain,
        state = RadioTheme.State.Invalid,
        size = RadioTheme.Size.Default
    )
}

@Preview(showBackground = true)
@Composable
private fun WithinInput() {
    Radio(
        label = "Within Input",
        secondaryLabel = "Description text",
        type = RadioTheme.Type.Soft,
        style = RadioTheme.Style.WithinInput,
        state = RadioTheme.State.Default,
        size = RadioTheme.Size.Default,
        leadingIcon = painterResource(
            R.drawable.ic_align_vertical_distribute_start
        ),
        trailingIcon = painterResource(
            R.drawable.ic_table
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun Plain() {
    Radio(
        label = "Plain",
        secondaryLabel = "Description text",
        type = RadioTheme.Type.Soft,
        style = RadioTheme.Style.Plain,
        state = RadioTheme.State.Default,
        size = RadioTheme.Size.Default,
        leadingIcon = painterResource(R.drawable.ic_align_vertical_distribute_start),
        trailingIcon = painterResource(R.drawable.ic_table)
    )
}

@Preview(showBackground = true)
@Composable
private fun Leading() {
    Column {
        Radio(
            label = "Plain",
            secondaryLabel = "Description text",
            leading = true,
            type = RadioTheme.Type.Solid,
            style = RadioTheme.Style.Plain,
            state = RadioTheme.State.Default,
            size = RadioTheme.Size.Default
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Trailing() {
    Column {
        Radio(
            label = "Plain",
            secondaryLabel = "Description text",
            leading = false,
            type = RadioTheme.Type.Solid,
            style = RadioTheme.Style.Plain,
            state = RadioTheme.State.Default,
            size = RadioTheme.Size.Default
        )
    }
}
