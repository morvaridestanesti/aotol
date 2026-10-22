package ir.ncis.preline.ui.components.sidebar.data

import androidx.compose.ui.graphics.painter.Painter

data class SidebarAccount(
    val name: String,
    val avatar: Painter? = null,
    val menu: List<SidebarItem> = emptyList(),
)
