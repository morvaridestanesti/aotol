package ir.ncis.preline.ui.components.tree_view.data

import androidx.compose.ui.graphics.painter.Painter

data class TreeNode(
    val id: String,
    val label: String,
    val icon: Painter? = null,
    val children: List<TreeNode> = emptyList(),
    val enabled: Boolean = true,
)
