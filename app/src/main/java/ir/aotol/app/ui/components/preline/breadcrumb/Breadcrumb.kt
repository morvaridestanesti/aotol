package ir.aotol.app.ui.components.preline.breadcrumb

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.aotol.app.R
import ir.aotol.app.helpers.invisibleClickable
import ir.aotol.app.ui.components.preline.breadcrumb.data.BreadcrumbItem
import ir.aotol.app.ui.components.preline.breadcrumb.data.BreadcrumbSubItem

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
            Box(modifier = modifier) {
                HorizontalDivider(color = theme.borderColor, thickness = 1.dp)
                BreadcrumbRow(
                    items = items,
                    separatorType = separatorType,
                    modifier = Modifier.padding(vertical = theme.verticalPadding),
                    theme = theme
                )
                HorizontalDivider(color = theme.dividerColor)
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
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(
            items = items,
            key = { index, item -> "${index}_${item.title}" }
        ) { index, item ->
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
        modifier = Modifier.invisibleClickable { item.onClick?.invoke() }
    ) {
        item.icon?.let { painter ->
            Icon(
                painter = painter,
                contentDescription = null,
                tint = theme.itemIconColor,
                modifier = Modifier.size(theme.iconSize)
            )
            Spacer(Modifier.width(6.dp))
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
        modifier = Modifier.size(theme.triggerSize),
        colors = CardDefaults.cardColors(theme.triggerBackground),
        border = BorderStroke(1.dp, theme.triggerBorder),
        shape = theme.triggerShape,
        onClick = { expanded.value = true }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_more_vertical),
            contentDescription = "",
            tint = theme.triggerIconColor,
            modifier = Modifier
                .fillMaxSize()
                .padding(theme.triggerPadding)
        )
        if (item.title.isNotBlank()) {
            Spacer(Modifier.width(10.dp))
            Text(
                text = item.title,
                color = theme.itemTextColor,
                style = theme.itemStyle
            )
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

private fun previewLinkItem(title: String, icon: Painter? = null) = BreadcrumbItem(
    title = title,
    icon = icon,
    onClick = {}
)

@Preview(showBackground = true)
@Composable
private fun BreadcrumbPreview_Base() {
    Breadcrumb(
        items = listOf(
            previewLinkItem("Home"),
            previewLinkItem("App Center", painterResource(R.drawable.ic_home)),
            previewLinkItem("Application")
        ),
        type = BreadcrumbTheme.Type.Base,
        separatorType = BreadcrumbTheme.SeparatorType.Slash
    )
}

@Preview(showBackground = true)
@Composable
private fun BreadcrumbPreview_BorderedChevron() {
    Breadcrumb(
        items = listOf(
            previewLinkItem("Home"),
            previewLinkItem("App Center"),
            previewLinkItem("Application")
        ),
        type = BreadcrumbTheme.Type.Bordered,
        separatorType = BreadcrumbTheme.SeparatorType.Chevron
    )
}

@Preview(showBackground = true)
@Composable
private fun BreadcrumbPreview_IconsDesktop() {
    Breadcrumb(
        items = listOf(
            previewLinkItem("Home"),
            previewLinkItem("App Center"),
            previewLinkItem("Application")
        ),
        type = BreadcrumbTheme.Type.Base,
        separatorType = BreadcrumbTheme.SeparatorType.Chevron
    )
}

@Preview(showBackground = true)
@Composable
private fun BreadcrumbPreview_TriggerWithLinks() {
    Breadcrumb(
        items = listOf(
            previewLinkItem("Home", painterResource(R.drawable.ic_home)),
            BreadcrumbItem(
                title = "",
                children = listOf(
                    BreadcrumbSubItem(title = "Projects", onClick = {}),
                    BreadcrumbSubItem(title = "App Center", onClick = {}),
                    BreadcrumbSubItem(title = "Settings", onClick = {})
                )
            ),
            previewLinkItem("Application")
        ),
        type = BreadcrumbTheme.Type.Base,
        separatorType = BreadcrumbTheme.SeparatorType.Slash
    )
}
