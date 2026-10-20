package ir.ncis.preline.ui.components.breadcrumb

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.helpers.invisibleClickable
import ir.ncis.preline.ui.components.breadcrumb.data.BreadcrumbItem

@Composable
fun Breadcrumb(
    modifier: Modifier = Modifier,
    items: List<BreadcrumbItem>,
    type: BreadcrumbTheme.Type = BreadcrumbTheme.Type.Base,
    separatorType: BreadcrumbTheme.SeparatorType = BreadcrumbTheme.SeparatorType.Slash
) {
    val theme = getTheme(type, false)
    when (type) {
        BreadcrumbTheme.Type.Base     -> {
            BreadcrumbRow(
                items = items,
                separatorType = separatorType,
                modifier = modifier,
                theme = theme
            )
        }

        BreadcrumbTheme.Type.Bordered -> {
            Column(modifier = modifier.width(IntrinsicSize.Max)) {
                HorizontalDivider(color = theme.borderColor, thickness = 1.dp)
                BreadcrumbRow(
                    items = items,
                    separatorType = separatorType,
                    modifier = Modifier.padding(vertical = theme.verticalPadding, horizontal = theme.horizontalPadding),
                    theme = theme
                )
                HorizontalDivider(color = theme.dividerColor, thickness = 1.dp)
            }
        }
    }
}

@Composable
private fun BreadcrumbRow(
    items: List<BreadcrumbItem>,
    separatorType: BreadcrumbTheme.SeparatorType,
    modifier: Modifier = Modifier,
    theme: BreadcrumbTheme
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, item ->
            BreadcrumbItemView(item = item, isLastItem = index == items.lastIndex, theme = theme)
            if (index != items.lastIndex) {
                BreadcrumbSeparator(type = separatorType, theme = theme)
            }
        }
    }
}

@Composable
private fun BreadcrumbSeparator(type: BreadcrumbTheme.SeparatorType, theme: BreadcrumbTheme) {
    Box(
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        when (type) {
            BreadcrumbTheme.SeparatorType.Slash   -> {
                Icon(
                    painter = painterResource(R.drawable.ic_slash),
                    contentDescription = null,
                    tint = theme.separatorColor,
                    modifier = Modifier.size(theme.separatorSize)
                )
            }

            BreadcrumbTheme.SeparatorType.Chevron -> {
                Icon(
                    painter = painterResource(R.drawable.ic_chevron_forward),
                    contentDescription = null,
                    tint = theme.separatorColor,
                    modifier = Modifier.size(theme.separatorSize)
                )
            }
        }
    }
}

@Composable
private fun BreadcrumbItemView(item: BreadcrumbItem, isLastItem: Boolean, theme: BreadcrumbTheme) {
    if (item.children.isNotEmpty()) {
        BreadcrumbTriggerItem(item = item, theme = theme)
    } else {
        BreadcrumbLinkItem(item = item, isLastItem = isLastItem, theme = theme)
    }
}

@Composable
private fun BreadcrumbLinkItem(item: BreadcrumbItem, isLastItem: Boolean, theme: BreadcrumbTheme) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.invisibleClickable(role = if (item.onClick != null) Role.Button else null) { item.onClick?.invoke() }
    ) {
        item.icon?.let { painter ->
            Icon(
                painter = painter,
                contentDescription = null,
                tint = theme.itemIconColor,
                modifier = Modifier.size(theme.iconSize)
            )
        }
        Text(
            text = item.title,
            color = if (isLastItem) theme.lastItemTextColor else theme.itemTextColor,
            style = if (isLastItem) theme.lastItemStyle else theme.itemStyle
        )
    }
}

@Composable
private fun BreadcrumbTriggerItem(item: BreadcrumbItem, theme: BreadcrumbTheme) {
    val expanded = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.height(theme.triggerSize),
        colors = CardDefaults.cardColors(theme.triggerBackground),
        border = BorderStroke(1.dp, theme.triggerBorder),
        shape = theme.triggerShape,
        onClick = { expanded.value = true }
    ) {
        Row(
            modifier = Modifier
                .height(theme.triggerSize)
                .then(if (item.title.isBlank()) Modifier.width(theme.triggerSize) else Modifier.padding(horizontal = 8.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_more_vertical),
                contentDescription = if (item.title.isBlank()) stringResource(R.string.preline_more) else null,
                tint = theme.triggerIconColor,
                modifier = Modifier.size(theme.iconSize)
            )
            if (item.title.isNotBlank()) {
                Text(
                    text = item.title,
                    color = theme.lastItemTextColor,
                    style = theme.itemStyle
                )
            }
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            item.children.forEach { subItem ->
                DropdownMenuItem(
                    text = { Text(subItem.title) },
                    onClick = {
                        subItem.onClick()
                        expanded.value = false
                    }
                )
            }
        }
    }
}
