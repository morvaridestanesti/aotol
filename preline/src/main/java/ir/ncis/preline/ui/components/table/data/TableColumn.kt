package ir.ncis.preline.ui.components.table.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import ir.ncis.preline.ui.components.table.TableTheme

data class TableColumn<T>(
    val title: String,
    val weight: Float = 1f,
    val width: Dp? = null,
    val highlight: (T) -> TableTheme.Highlight? = { null },
    val content: @Composable (T) -> Unit,
)
