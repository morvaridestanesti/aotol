package ir.ncis.preline.ui.components.list_group

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ir.ncis.preline.helpers.disabledAlpha
import ir.ncis.preline.ui.components.list_group.data.ListGroupItem
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium

@Composable
fun ListGroup(
    items: List<ListGroupItem>,
    modifier: Modifier = Modifier,
    type: ListGroupTheme.Type = ListGroupTheme.Type.Bordered,
    direction: ListGroupTheme.Direction = ListGroupTheme.Direction.Vertical,
    badgePosition: ListGroupTheme.BadgePosition = ListGroupTheme.BadgePosition.AfterText,
) {
    val theme = getTheme(type)
    val container = modifier
        .clip(theme.shape)
        .background(theme.background)
        .border(1.dp, theme.stroke, theme.shape)

    when (direction) {
        ListGroupTheme.Direction.Vertical   -> Column(modifier = container) {
            items.forEachIndexed { index, item ->
                if (index > 0) HorizontalDivider(thickness = 1.dp, color = theme.divider)
                ItemView(item, index, theme, badgePosition, Modifier.fillMaxWidth())
            }
        }

        ListGroupTheme.Direction.Horizontal -> Row(modifier = container.width(IntrinsicSize.Max).height(IntrinsicSize.Min)) {
            items.forEachIndexed { index, item ->
                if (index > 0) VerticalDivider(thickness = 1.dp, color = theme.divider)
                ItemView(item, index, theme, badgePosition, Modifier.weight(1f).fillMaxHeight())
            }
        }
    }
}

@Composable
private fun ItemView(item: ListGroupItem, index: Int, theme: ListGroupTheme, badgePosition: ListGroupTheme.BadgePosition, modifier: Modifier) {
    val disabled = item.state == ListGroupTheme.State.Disabled
    val color = getTextColor(item.state, theme)
    val background = when {
        item.state == ListGroupTheme.State.Hover -> theme.hoverBackground
        index % 2 == 1                           -> theme.stripe
        else                                     -> Palette.Transparent
    }

    Row(
        modifier = modifier
            .background(background)
            .then(item.onClick?.let { Modifier.clickable(enabled = !disabled, role = Role.Button, onClick = it) } ?: Modifier)
            .padding(horizontal = theme.horizontalPadding, vertical = theme.verticalPadding)
            .disabledAlpha(disabled),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item.icon?.let { Icon(painter = it, contentDescription = null, tint = color, modifier = Modifier.size(16.dp)) }
        Text(text = item.placeholder, color = color, style = PrelineTheme.typography.sm.medium)
        item.badge?.let { badge ->
            if (badgePosition == ListGroupTheme.BadgePosition.End) Spacer(Modifier.weight(1f))
            badge()
        }
    }
}
