package ir.ncis.preline.ui.components.list_group.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import ir.ncis.preline.ui.components.list_group.ListGroupTheme

data class ListGroupItem(
    val placeholder: String,
    val icon: Painter? = null,
    val badge: (@Composable () -> Unit)? = null,
    val state: ListGroupTheme.State = ListGroupTheme.State.Default,
    val onClick: (() -> Unit)? = null,
)
