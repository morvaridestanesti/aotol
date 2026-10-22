package ir.ncis.preline.ui.components.sidebar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.ui.components.avatar.Avatar
import ir.ncis.preline.ui.components.avatar.AvatarTheme
import ir.ncis.preline.ui.components.badge.Badge
import ir.ncis.preline.ui.components.badge.BadgeTheme
import ir.ncis.preline.ui.components.sidebar.data.SidebarAccount
import ir.ncis.preline.ui.components.sidebar.data.SidebarItem
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium

@Composable
fun Sidebar(
    items: List<SidebarItem>,
    selected: String?,
    onSelect: (SidebarItem) -> Unit,
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    account: SidebarAccount? = null,
    type: SidebarTheme.Type = SidebarTheme.Type.Plain,
    minified: Boolean = false,
    width: Dp = 260.dp,
) {
    val theme = getTheme(type)
    var expanded by remember { mutableStateOf(items.filter { it.contains(selected) }.map { it.id }.toSet()) }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(if (minified) 64.dp else width)
            .animateContentSize()
            .background(theme.background)
            .drawBehind {
                val x = if (layoutDirection == LayoutDirection.Rtl) 0f else size.width
                drawLine(theme.stroke, Offset(x, 0f), Offset(x, size.height), 1.dp.toPx())
            }
            .padding(vertical = 12.dp)
    ) {
        header?.let { Box(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp)) { it() } }
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items.forEach { item ->
                ItemTree(item, selected, expanded, { id -> expanded = if (id in expanded) expanded - id else expanded + id }, onSelect, minified, theme)
            }
        }
        account?.let {
            HorizontalDivider(thickness = 1.dp, color = theme.guide, modifier = Modifier.padding(bottom = 8.dp))
            AccountButton(it, minified, onSelect, theme)
        }
    }
}

@Composable
private fun ItemTree(
    item: SidebarItem,
    selected: String?,
    expanded: Set<String>,
    onToggle: (String) -> Unit,
    onSelect: (SidebarItem) -> Unit,
    minified: Boolean,
    theme: SidebarTheme,
) {
    val isOpen = item.id in expanded
    val hasChildren = item.children.isNotEmpty() && !minified
    val stateDescription = stringResource(if (isOpen) R.string.preline_expanded else R.string.preline_collapsed)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (item.id == selected) theme.selectedBackground else theme.background.copy(alpha = 0f))
            .semantics {
                this.selected = item.id == selected
                if (hasChildren) this.stateDescription = stateDescription
                if (minified) contentDescription = item.label
            }
            .clickable(role = Role.Button) { if (hasChildren) onToggle(item.id) else onSelect(item) }
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (minified) Arrangement.Center else Arrangement.spacedBy(10.dp)
    ) {
        item.icon?.let { Icon(painter = it, contentDescription = null, tint = theme.icon, modifier = Modifier.size(16.dp)) }
        if (!minified) {
            Text(
                text = item.label,
                color = if (item.id == selected) theme.selectedText else theme.text,
                style = PrelineTheme.typography.sm.medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            item.badge?.let { Badge(placeholder = it, type = BadgeTheme.Type.Solid, color = BadgeTheme.Color.Blue, size = BadgeTheme.Size.Small, shape = BadgeTheme.Shape.Pilled) }
            Spacer(Modifier.weight(1f))
            if (hasChildren) {
                Icon(
                    painter = painterResource(if (isOpen) R.drawable.ic_chevron_up else R.drawable.ic_chevron_down),
                    contentDescription = null,
                    tint = theme.icon,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
    AnimatedVisibility(visible = hasChildren && isOpen) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .drawBehind {
                    val x = if (layoutDirection == LayoutDirection.Rtl) size.width else 0f
                    drawLine(theme.guide, Offset(x, 0f), Offset(x, size.height), 1.dp.toPx())
                }
                .padding(start = 8.dp, top = 2.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            item.children.forEach { ItemTree(it, selected, expanded, onToggle, onSelect, false, theme) }
        }
    }
}

@Composable
private fun AccountButton(account: SidebarAccount, minified: Boolean, onSelect: (SidebarItem) -> Unit, theme: SidebarTheme) {
    var open by remember { mutableStateOf(false) }

    Box(Modifier.padding(horizontal = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .clickable(role = Role.Button, enabled = account.menu.isNotEmpty()) { open = true }
                .semantics { if (minified) contentDescription = account.name }
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (minified) Arrangement.Center else Arrangement.spacedBy(10.dp)
        ) {
            Avatar(imagePainter = account.avatar, placeholder = account.name, size = AvatarTheme.Size.XS, shape = AvatarTheme.Shape.Circular)
            if (!minified) {
                Text(text = account.name, color = theme.text, style = PrelineTheme.typography.sm.medium, maxLines = 1, modifier = Modifier.weight(1f))
                if (account.menu.isNotEmpty()) {
                    Icon(painter = painterResource(R.drawable.ic_selector), contentDescription = null, tint = theme.icon, modifier = Modifier.size(14.dp))
                }
            }
        }
        DropdownMenu(
            expanded = open,
            onDismissRequest = { open = false },
            shape = RoundedCornerShape(8.dp),
            containerColor = PrelineTheme.colors.layer,
            shadowElevation = 4.dp,
            border = BorderStroke(1.dp, PrelineTheme.colors.layerBorder)
        ) {
            account.menu.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.label, color = PrelineTheme.colors.foreground, style = PrelineTheme.typography.sm) },
                    leadingIcon = item.icon?.let { { Icon(painter = it, contentDescription = null, tint = PrelineTheme.colors.foreground, modifier = Modifier.size(16.dp)) } },
                    onClick = {
                        open = false
                        onSelect(item)
                    }
                )
            }
        }
    }
}

private fun SidebarItem.contains(id: String?): Boolean = this.id == id || children.any { it.contains(id) }
