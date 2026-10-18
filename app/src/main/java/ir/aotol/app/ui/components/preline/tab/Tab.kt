package ir.aotol.app.ui.components.preline.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.aotol.app.R
import ir.aotol.app.ui.components.preline.tab.data.TabItem
import ir.aotol.app.ui.components.preline.PrelineTheme

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
    // Segment chips sit on a gray track.
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
        Column(modifier = modifier.then(track), verticalArrangement = Arrangement.spacedBy(if (chips) 4.dp else 0.dp)) {
            displayItems.forEach {
                TabButton(
                    item = it,
                    type = type,
                ) { onIndexChanged(it.index) }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
private fun Horizontal() {
    val currentTabIndex = remember { mutableIntStateOf(0) }
    Tab(
        items = listOf(
            TabItem(
                index = 0,
                placeholder = "Projects",
                leadingIcon = painterResource(R.drawable.ic_home),
                badge = 3,
                isSelected = currentTabIndex.intValue == 0
            ),
            TabItem(
                index = 1,
                placeholder = "Tasks",
                leadingIcon = painterResource(R.drawable.ic_home),
                isSelected = currentTabIndex.intValue == 1
            ),
            TabItem(
                index = 2,
                placeholder = "Settings",
                leadingIcon = painterResource(R.drawable.ic_home),
                trailingIcon = painterResource(R.drawable.ic_chevron_down),
                isSelected = currentTabIndex.intValue == 2,
                badge = 2,
                enabled = false,
            ),
        ),
        selectedIndex = currentTabIndex.intValue,
        onIndexChanged = { currentTabIndex.intValue = it },
        type = TabTheme.Type.Bordered,
        direction = TabTheme.Direction.Horizontal,
        fill = TabTheme.Fill.Filled
    )
}

@Preview(showBackground = true)
@Composable
private fun Vertical() {
    val currentTabIndex = remember { mutableIntStateOf(0) }
    Tab(
        items = listOf(
            TabItem(
                index = 0,
                placeholder = "Projects",
                leadingIcon = painterResource(R.drawable.ic_home),
                badge = 3,
                isSelected = currentTabIndex.intValue == 0
            ),
            TabItem(
                index = 1,
                placeholder = "Tasks",
                leadingIcon = painterResource(R.drawable.ic_home),
                isSelected = currentTabIndex.intValue == 1
            ),
            TabItem(
                index = 2,
                placeholder = "Settings",
                leadingIcon = painterResource(R.drawable.ic_home),
                trailingIcon = painterResource(R.drawable.ic_chevron_down),
                isSelected = currentTabIndex.intValue == 2,
                badge = 2,
                enabled = false,
            ),
        ),
        modifier = Modifier.width(160.dp),
        selectedIndex = currentTabIndex.intValue,
        onIndexChanged = { currentTabIndex.intValue = it },
        type = TabTheme.Type.Bordered,
        direction = TabTheme.Direction.Vertical,
        fill = TabTheme.Fill.Filled
    )
}