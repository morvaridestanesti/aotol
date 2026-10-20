package ir.ncis.preline.ui.components.button_group

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ir.ncis.preline.helpers.disabledAlpha
import ir.ncis.preline.ui.components.button_group.data.ButtonGroupItem
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium

@Composable
fun ButtonGroup(
    items: List<ButtonGroupItem>,
    modifier: Modifier = Modifier,
    type: ButtonGroupTheme.Type = ButtonGroupTheme.Type.White,
    size: ButtonGroupTheme.Size = ButtonGroupTheme.Size.Default,
    shape: ButtonGroupTheme.Shape = ButtonGroupTheme.Shape.Rounded,
    direction: ButtonGroupTheme.Direction = ButtonGroupTheme.Direction.Horizontal,
) {
    val theme = getTheme(type, size, shape)
    val container = modifier
        .clip(theme.shape)
        .background(theme.background)
        .border(1.dp, theme.stroke, theme.shape)

    when (direction) {
        ButtonGroupTheme.Direction.Horizontal -> Row(modifier = container.height(IntrinsicSize.Min)) {
            items.forEachIndexed { index, item ->
                if (index > 0) VerticalDivider(thickness = 1.dp, color = theme.stroke)
                ItemView(item, theme, Modifier.fillMaxHeight())
            }
        }

        ButtonGroupTheme.Direction.Vertical   -> Column(modifier = container.width(IntrinsicSize.Max)) {
            items.forEachIndexed { index, item ->
                if (index > 0) HorizontalDivider(thickness = 1.dp, color = theme.stroke)
                ItemView(item, theme, Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
private fun ItemView(item: ButtonGroupItem, theme: ButtonGroupTheme, modifier: Modifier) {
    val disabled = item.state == ButtonGroupTheme.State.Disabled
    val background = when (item.state) {
        ButtonGroupTheme.State.Hover -> theme.hoverBackground
        ButtonGroupTheme.State.Focus -> theme.focusBackground
        else                         -> Palette.Transparent
    }

    Row(
        modifier = modifier
            .background(background)
            .disabledAlpha(disabled)
            .clickable(enabled = !disabled, role = Role.Button, onClick = item.onClick)
            .padding(horizontal = theme.horizontalPadding, vertical = theme.verticalPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        item.leadingIcon?.let { ItemIcon(it, item.contentDescription.takeIf { item.placeholder == null }, theme) }
        item.placeholder?.let { Text(text = it, color = theme.foreground, style = PrelineTheme.typography.sm.medium) }
        item.trailingIcon?.let { ItemIcon(it, null, theme) }
    }
}

@Composable
private fun ItemIcon(painter: Painter, contentDescription: String?, theme: ButtonGroupTheme) {
    Icon(painter = painter, contentDescription = contentDescription, tint = theme.foreground, modifier = Modifier.size(16.dp))
}
