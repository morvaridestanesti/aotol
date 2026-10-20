package ir.ncis.preline.ui.components.accordion.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

data class AccordionItem(
    val title: String,
    val content: @Composable () -> Unit,
    val badge: AccordionBadge? = null,
    val icon: Painter? = null,
    val isExpanded: Boolean = false
)
