package ir.aotol.app.ui.components.preline.breadcrumb.data

import androidx.compose.ui.graphics.painter.Painter

data class BreadcrumbItem(
    val title: String,
    val icon: Painter? = null,
    val children: List<BreadcrumbSubItem> = emptyList(),
    val onClick: (() -> Unit)? = null
)
