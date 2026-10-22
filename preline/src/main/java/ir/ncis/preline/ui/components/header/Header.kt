package ir.ncis.preline.ui.components.header

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
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
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.ui.components.header.data.HeaderItem
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium

@Composable
fun Header(
    logo: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    items: List<HeaderItem> = emptyList(),
    selectedIndex: Int? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
    type: HeaderTheme.Type = HeaderTheme.Type.Plain,
    border: HeaderTheme.Border = HeaderTheme.Border.None,
    alignment: HeaderTheme.Alignment = HeaderTheme.Alignment.End,
    actionsDivider: Boolean = false,
    collapseBelow: Dp = 600.dp,
    windowInsets: WindowInsets = WindowInsets.statusBars,
) {
    val theme = getTheme(type, border)
    var expanded by remember { mutableStateOf(false) }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .clip(theme.shape)
            .background(theme.background)
            .border(1.dp, theme.stroke, theme.shape)
            .drawBehind { drawLine(theme.bottomBorder, Offset(0f, size.height), Offset(size.width, size.height), 1.dp.toPx()) }
            .windowInsetsPadding(windowInsets)
    ) {
        val collapsed = items.isNotEmpty() && maxWidth < collapseBelow

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 56.dp)
                    .height(IntrinsicSize.Min)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                logo()
                if (collapsed || alignment != HeaderTheme.Alignment.Start) Spacer(Modifier.weight(1f))
                if (!collapsed) {
                    items.forEachIndexed { index, item -> Link(item, index == selectedIndex, theme) }
                    if (alignment != HeaderTheme.Alignment.End) Spacer(Modifier.weight(1f))
                }
                actions?.let {
                    if (actionsDivider) VerticalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 1.dp, color = theme.divider)
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp), content = it)
                }
                if (collapsed) MenuButton(expanded, theme) { expanded = !expanded }
            }
            AnimatedVisibility(visible = collapsed && expanded) {
                Column(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items.forEachIndexed { index, item -> Link(item, index == selectedIndex, theme, Modifier.fillMaxWidth()) }
                }
            }
        }
    }
}

@Composable
private fun Link(item: HeaderItem, isSelected: Boolean, theme: HeaderTheme, modifier: Modifier = Modifier) {
    var open by remember { mutableStateOf(false) }
    val color = if (isSelected) theme.activeLink else theme.link

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .semantics { selected = isSelected }
                .clickable(role = Role.Button) { if (item.children.isEmpty()) item.onClick() else open = true }
                .drawBehind {
                    if (isSelected) drawLine(theme.activeLink, Offset(0f, size.height - 1.dp.toPx()), Offset(size.width, size.height - 1.dp.toPx()), 2.dp.toPx())
                }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            item.icon?.let { Icon(painter = it, contentDescription = null, tint = color, modifier = Modifier.size(16.dp)) }
            Text(text = item.label, color = color, style = PrelineTheme.typography.sm.medium, maxLines = 1)
            if (item.children.isNotEmpty()) {
                Icon(painter = painterResource(R.drawable.ic_chevron_down), contentDescription = null, tint = color, modifier = Modifier.size(14.dp))
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
            item.children.forEach { child ->
                DropdownMenuItem(
                    text = { Text(text = child.label, color = PrelineTheme.colors.foreground, style = PrelineTheme.typography.sm) },
                    leadingIcon = child.icon?.let { { Icon(painter = it, contentDescription = null, tint = PrelineTheme.colors.foreground, modifier = Modifier.size(16.dp)) } },
                    onClick = {
                        open = false
                        child.onClick()
                    }
                )
            }
        }
    }
}

@Composable
private fun MenuButton(expanded: Boolean, theme: HeaderTheme, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, theme.divider, RoundedCornerShape(8.dp))
            .clickable(role = Role.Button, onClick = onClick)
            .semantics { selected = expanded },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(if (expanded) R.drawable.ic_clear else R.drawable.ic_menu),
            contentDescription = stringResource(if (expanded) R.string.preline_close else R.string.preline_menu),
            tint = theme.link,
            modifier = Modifier.size(16.dp)
        )
    }
}
