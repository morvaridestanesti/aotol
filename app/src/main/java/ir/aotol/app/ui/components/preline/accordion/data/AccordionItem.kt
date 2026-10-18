package ir.aotol.app.ui.components.preline.accordion.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

data class AccordionItem(
    val title: String,
    val badge: AccordionBadge? = null,
    val icon: Painter? = null,
    val isExpanded: Boolean = false,
    val content: @Composable () -> Unit
)
