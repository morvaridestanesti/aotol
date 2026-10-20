package ir.ncis.preline.ui.components.tab.data

import androidx.compose.ui.graphics.painter.Painter

data class TabItem(
    val index: Int,
    val placeholder: String? = null,
    val leadingIcon: Painter? = null,
    val trailingIcon: Painter? = null,
    val badge: Int? = null,
    val isSelected: Boolean = false,
    val enabled: Boolean = true,
)
