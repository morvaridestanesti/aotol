package ir.aotol.app.ui.components.preline.accordion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.aotol.app.R
import ir.aotol.app.ui.components.preline.Colors
import ir.aotol.app.ui.components.preline.accordion.AccordionTheme.Style
import ir.aotol.app.ui.components.preline.accordion.AccordionTheme.Toggle
import ir.aotol.app.ui.components.preline.accordion.AccordionTheme.Type
import ir.aotol.app.ui.components.preline.accordion.data.AccordionBadge
import ir.aotol.app.ui.components.preline.accordion.data.AccordionItem
import ir.aotol.app.ui.components.preline.badge.Badge

@Composable
fun Accordion(
    items: List<AccordionItem>,
    modifier: Modifier = Modifier,
    style: Style = Style.Normal,
    toggle: Toggle = Toggle.StartPlus,
    type: Type = Type.Basic
) {
    val itemsState = remember { mutableStateOf(items) }
    val theme = getTheme(style, false)

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(Colors.Transparent),
        border = BorderStroke(1.dp, theme.borderColor),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = theme.shape
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            itemsState.value.forEachIndexed { index, item ->
                val itemTheme = getTheme(style, item.isExpanded)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            itemsState.value = itemsState.value.mapIndexed { stateIndex, stateItem ->
                                when (type) {
                                    Type.Basic      -> stateItem.copy(isExpanded = stateIndex == index && !stateItem.isExpanded)
                                    Type.AlwaysOpen -> if (stateIndex == index) stateItem.copy(isExpanded = !stateItem.isExpanded) else stateItem
                                }
                            }
                        }
                ) {
                    if (item.isExpanded && style == Style.BorderedOpen) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(Colors.Transparent),
                            border = BorderStroke(1.dp, itemTheme.borderColor),
                            elevation = CardDefaults.cardElevation(0.dp),
                            shape = itemTheme.shape
                        ) { ItemView(item, toggle, itemTheme) }
                    } else {
                        ItemView(item, toggle, itemTheme)
                    }
                }

                if (style == Style.Bordered && index != items.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = Colors.Theme.Layer.LayerBorder
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemView(item: AccordionItem, toggle: Toggle, theme: AccordionTheme) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(durationMillis = 250))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (toggle) {
                Toggle.StartPlus  -> Icon(
                    painter = painterResource(if (item.isExpanded) R.drawable.ic_minus else R.drawable.ic_plus),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(theme.iconSize),
                    tint = theme.titleColor
                )

                Toggle.StartArrow -> Icon(
                    painter = painterResource(if (item.isExpanded) R.drawable.ic_chevron_up else R.drawable.ic_chevron_down),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(theme.iconSize),
                    tint = theme.titleColor
                )

                else              -> {}
            }

            item.icon?.let {
                Icon(
                    painter = it,
                    contentDescription = null,
                    modifier = Modifier.size(theme.iconSize),
                    tint = theme.titleColor
                )
                Spacer(Modifier.width(8.dp))
            }

            Text(
                text = item.title,
                color = theme.titleColor,
                fontSize = theme.titleFontSize,
                fontWeight = FontWeight.SemiBold
            )

            item.badge?.let {
                Badge(
                    placeholder = it.title,
                    type = it.type,
                    color = it.color,
                    size = it.size,
                    shape = it.shape,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }

            if (toggle == Toggle.EndArrow) {
                Spacer(Modifier.weight(1f))
                Icon(
                    painter = painterResource(if (item.isExpanded) R.drawable.ic_chevron_up else R.drawable.ic_chevron_down),
                    contentDescription = null,
                    modifier = Modifier.size(theme.iconSize),
                    tint = theme.titleColor
                )
            }
        }

        AnimatedVisibility(
            visible = item.isExpanded,
            enter = expandVertically(animationSpec = tween(250)) + fadeIn(animationSpec = tween(150)),
            exit = shrinkVertically(animationSpec = tween(250)) + fadeOut(animationSpec = tween(100))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            ) { item.content() }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    val items = listOf(
        AccordionItem("Accordion #1", badge = AccordionBadge("5")) { Text("Content 1") },
        AccordionItem("Accordion #2", isExpanded = true) { Text("Content 2") },
        AccordionItem("Accordion #3") { Text("Content 3") },
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Accordion(
                items = items,
                modifier = Modifier.fillMaxWidth(),
                style = Style.BorderedOpen,
                toggle = Toggle.StartArrow,
                type = Type.Basic
            )
        }
    }
}
