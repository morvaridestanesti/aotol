package ir.ncis.preline.ui.components.sidebar.data

import androidx.compose.ui.graphics.painter.Painter

data class SidebarItem(
    val id: String,
    val label: String,
    val icon: Painter? = null,
    val badge: String? = null,
    val children: List<SidebarItem> = emptyList(),
)
