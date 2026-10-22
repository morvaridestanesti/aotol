package ir.ncis.preline.ui.components.advanced_select.data

import androidx.compose.ui.graphics.painter.Painter
import ir.ncis.preline.ui.components.advanced_select.AdvancedSelectTheme

data class SelectOption(
    val value: String,
    val label: String,
    val group: String? = null,
    val icon: Painter? = null,
    val description: String? = null,
    val status: AdvancedSelectTheme.Status? = null,
    val enabled: Boolean = true,
)
