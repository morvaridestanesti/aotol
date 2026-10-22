package ir.ncis.preline.ui.components.header.data

import androidx.compose.ui.graphics.painter.Painter

data class HeaderItem(
    val label: String,
    val icon: Painter? = null,
    val children: List<HeaderItem> = emptyList(),
    val onClick: () -> Unit = {},
)
