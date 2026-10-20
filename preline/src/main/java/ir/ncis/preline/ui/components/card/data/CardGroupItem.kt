package ir.ncis.preline.ui.components.card.data

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable

data class CardGroupItem(
    val media: (@Composable () -> Unit)? = null,
    val footer: (@Composable () -> Unit)? = null,
    val content: @Composable ColumnScope.() -> Unit,
)
