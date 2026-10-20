package ir.ncis.preline.ui.components.tree_view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.ui.components.checkbox.CompactCheckbox
import ir.ncis.preline.ui.components.tree_view.data.TreeNode
import ir.ncis.preline.ui.theme.PrelineTheme

@Composable
fun TreeView(
    nodes: List<TreeNode>,
    expanded: Set<String>,
    onExpandedChange: (Set<String>) -> Unit,
    modifier: Modifier = Modifier,
    selected: String? = null,
    onSelect: ((TreeNode) -> Unit)? = null,
    checked: Set<String>? = null,
    onCheckedChange: (Set<String>) -> Unit = {},
) {
    Column(modifier = modifier) {
        Nodes(nodes, expanded, onExpandedChange, selected, onSelect, checked, onCheckedChange, getTheme())
    }
}

@Composable
private fun Nodes(
    nodes: List<TreeNode>,
    expanded: Set<String>,
    onExpandedChange: (Set<String>) -> Unit,
    selected: String?,
    onSelect: ((TreeNode) -> Unit)?,
    checked: Set<String>?,
    onCheckedChange: (Set<String>) -> Unit,
    theme: TreeViewTheme,
) {
    nodes.forEach { node ->
        val isExpanded = node.id in expanded
        val isSelected = node.id == selected && node.enabled
        Column(
            modifier = if (isSelected) Modifier.clip(RoundedCornerShape(6.dp)).background(theme.selectedBackground) else Modifier
        ) {
            NodeRow(
                node = node,
                isExpanded = isExpanded,
                checked = checked?.contains(node.id),
                theme = theme,
                onToggle = { onExpandedChange(if (isExpanded) expanded - node.id else expanded + node.id) },
                onSelect = onSelect,
                onCheckedChange = { isChecked ->
                    val ids = node.ids()
                    onCheckedChange(if (isChecked) checked.orEmpty() + ids else checked.orEmpty() - ids)
                }
            )
            if (isExpanded && node.children.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .drawBehind {
                            val x = 8.dp.toPx().let { if (layoutDirection == LayoutDirection.Rtl) size.width - it else it }
                            drawLine(theme.guide, Offset(x, 0f), Offset(x, size.height), 1.dp.toPx())
                        }
                        .padding(start = 24.dp)
                ) { Nodes(node.children, expanded, onExpandedChange, selected, onSelect, checked, onCheckedChange, theme) }
            }
        }
    }
}

@Composable
private fun NodeRow(
    node: TreeNode,
    isExpanded: Boolean,
    checked: Boolean?,
    theme: TreeViewTheme,
    onToggle: () -> Unit,
    onSelect: ((TreeNode) -> Unit)?,
    onCheckedChange: (Boolean) -> Unit,
) {
    val labelColor = if (node.enabled) theme.label else theme.disabledLabel
    val stateDescription = stringResource(if (isExpanded) R.string.preline_expanded else R.string.preline_collapsed)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .then(
                when {
                    !node.enabled              -> Modifier
                    node.children.isNotEmpty() -> Modifier
                        .semantics { this.stateDescription = stateDescription }
                        .clickable(role = Role.Button) { onToggle(); onSelect?.invoke(node) }

                    onSelect != null           -> Modifier.clickable(role = Role.Button) { onSelect(node) }
                    else                       -> Modifier
                }
            )
            .padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(Modifier.size(16.dp), contentAlignment = Alignment.Center) {
            if (node.children.isNotEmpty()) {
                Icon(
                    painter = painterResource(if (isExpanded) R.drawable.ic_minus else R.drawable.ic_plus),
                    contentDescription = null,
                    tint = labelColor,
                    modifier = Modifier.size(12.dp)
                )
            }
        }
        node.icon?.let { Icon(painter = it, contentDescription = null, tint = labelColor, modifier = Modifier.size(16.dp)) }
        checked?.let { CompactCheckbox(state = ToggleableState(it), onClick = { onCheckedChange(!it) }, enabled = node.enabled) }
        Text(text = node.label, color = labelColor, style = PrelineTheme.typography.base)
    }
}

private fun TreeNode.ids(): Set<String> = if (enabled) setOf(id) + children.flatMap { it.ids() } else emptySet()
