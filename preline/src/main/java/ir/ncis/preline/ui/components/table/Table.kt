package ir.ncis.preline.ui.components.table

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.components.checkbox.CompactCheckbox
import ir.ncis.preline.ui.components.table.data.TableColumn
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium

@Composable
fun <T> Table(
    rows: List<T>,
    columns: List<TableColumn<T>>,
    modifier: Modifier = Modifier,
    border: TableTheme.Border = TableTheme.Border.None,
    showHeader: Boolean = true,
    headerBackground: Boolean = false,
    headerDividers: Boolean = false,
    rowDividers: Boolean = true,
    striped: Boolean = false,
    shadow: Boolean = false,
    selected: Set<Int>? = null,
    onSelectionChange: (Set<Int>) -> Unit = {},
    toolbar: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null,
) {
    val theme = getTheme(border, headerBackground)
    val scrollable = columns.all { it.width != null }

    Column(
        modifier = modifier
            .then(if (shadow) Modifier.shadow(1.dp, theme.shape) else Modifier)
            .clip(theme.shape)
            .background(theme.background)
            .border(1.dp, theme.stroke, theme.shape)
    ) {
        toolbar?.let {
            Box(Modifier.padding(12.dp)) { it() }
            HorizontalDivider(thickness = 1.dp, color = theme.divider)
        }
        Column(modifier = if (scrollable) Modifier.horizontalScroll(rememberScrollState()).width(IntrinsicSize.Max) else Modifier) {
            if (showHeader) {
                TableRow(Modifier.background(theme.headerBackground)) {
                    selected?.let {
                        val state = when {
                            it.isEmpty()         -> ToggleableState.Off
                            it.size == rows.size -> ToggleableState.On
                            else                 -> ToggleableState.Indeterminate
                        }
                        SelectionCell { CompactCheckbox(state, onClick = { onSelectionChange(if (state == ToggleableState.On) emptySet() else rows.indices.toSet()) }) }
                    }
                    columns.forEachIndexed { index, column ->
                        Cell(column, Palette.Transparent, if (headerDividers && index > 0) theme.divider else null) {
                            Text(text = column.title.uppercase(), color = theme.headerForeground, style = PrelineTheme.typography.xs.medium, maxLines = 1)
                        }
                    }
                }
                HorizontalDivider(thickness = 1.dp, color = if (rowDividers || border != TableTheme.Border.None) theme.divider else Palette.Transparent)
            }
            rows.forEachIndexed { rowIndex, row ->
                if (rowIndex > 0 && rowDividers) HorizontalDivider(thickness = 1.dp, color = theme.divider)
                TableRow(Modifier.background(if (striped && rowIndex % 2 == 1) theme.stripe else Palette.Transparent)) {
                    selected?.let {
                        val isSelected = rowIndex in it
                        SelectionCell {
                            CompactCheckbox(ToggleableState(isSelected), onClick = { onSelectionChange(if (isSelected) it - rowIndex else it + rowIndex) })
                        }
                    }
                    columns.forEach { column ->
                        Cell(column, column.highlight(row)?.let { getHighlightColor(it) } ?: Palette.Transparent) { column.content(row) }
                    }
                }
            }
        }
        footer?.let {
            HorizontalDivider(thickness = 1.dp, color = theme.divider)
            Box(Modifier.padding(12.dp)) { it() }
        }
    }
}

@Composable
fun TableText(text: String) {
    Text(text = text, color = PrelineTheme.colors.foreground, style = PrelineTheme.typography.sm)
}

@Composable
private fun TableRow(modifier: Modifier, content: @Composable RowScope.() -> Unit) {
    Row(modifier = modifier.height(IntrinsicSize.Min), content = content)
}

@Composable
private fun RowScope.SelectionCell(content: @Composable () -> Unit) {
    Box(Modifier.fillMaxHeight().padding(start = 16.dp), contentAlignment = Alignment.Center) { content() }
}

@Composable
private fun <T> RowScope.Cell(column: TableColumn<T>, background: Color, startDivider: Color? = null, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .then(column.width?.let { Modifier.width(it) } ?: Modifier.weight(column.weight))
            .fillMaxHeight()
            .background(background)
            .drawBehind {
                startDivider?.let {
                    val x = if (layoutDirection == LayoutDirection.Rtl) size.width else 0f
                    drawLine(it, Offset(x, 0f), Offset(x, size.height), 1.dp.toPx())
                }
            }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) { content() }
}
