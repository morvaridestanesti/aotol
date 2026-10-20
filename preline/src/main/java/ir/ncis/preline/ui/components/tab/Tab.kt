package ir.ncis.preline.ui.components.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.components.tab.data.TabItem
import ir.ncis.preline.ui.theme.PrelineTheme

@Composable
fun Tab(
    items: List<TabItem>,
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    onIndexChanged: (Int) -> Unit,
    type: TabTheme.Type = TabTheme.Type.Base,
    direction: TabTheme.Direction = TabTheme.Direction.Horizontal,
    fill: TabTheme.Fill = TabTheme.Fill.None
) {
    val displayItems = items.mapIndexed { index, item -> item.copy(index = index, isSelected = index == selectedIndex) }
    val chips = type in listOf(TabTheme.Type.BrandPills, TabTheme.Type.GrayPills, TabTheme.Type.Segment)
    val track = if (type == TabTheme.Type.Segment) {
        Modifier.background(PrelineTheme.colors.surface, RoundedCornerShape(10.dp)).padding(4.dp)
    } else {
        Modifier
    }
    if (direction == TabTheme.Direction.Horizontal) {
        Row(
            modifier = (if (fill == TabTheme.Fill.Filled) modifier.fillMaxWidth() else modifier.horizontalScroll(rememberScrollState())).then(track),
            horizontalArrangement = when {
                fill == TabTheme.Fill.Filled -> Arrangement.Start
                chips                        -> Arrangement.spacedBy(4.dp)
                else                         -> Arrangement.spacedBy(20.dp)
            },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            displayItems.forEach {
                TabButton(
                    item = it,
                    modifier = if (fill == TabTheme.Fill.Filled) Modifier.weight(1f) else Modifier,
                    type = type,
                ) { onIndexChanged(it.index) }
            }
        }
    } else {
        Column(
            modifier = modifier
                .width(IntrinsicSize.Max)
                .then(track),
            verticalArrangement = Arrangement.spacedBy(if (chips) 4.dp else 0.dp)
        ) {
            displayItems.forEach {
                TabButton(
                    item = it,
                    modifier = Modifier.fillMaxWidth(),
                    type = type,
                    contentAlignment = Alignment.Start,
                ) { onIndexChanged(it.index) }
            }
        }
    }
}
