package ir.ncis.preline.ui.components.list.data

import androidx.compose.ui.graphics.painter.Painter

data class ListItem(
    val placeholder: String,
    val featuredIcon: Painter? = null,
    val icon: Painter? = null,
    val badge: Int? = null,
    val verticalDivider: Boolean = false,
    val horizontalDivider: Boolean = false,
    val circularSeparator: Boolean = false,
    val slashSeparator: Boolean = false,
)
