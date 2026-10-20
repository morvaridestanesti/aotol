package ir.ncis.preline.ui.components.accordion

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.ui.components.accordion.data.AccordionItem
import ir.ncis.preline.ui.components.badge.Badge
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme

@Composable
fun Accordion(
    items: List<AccordionItem>,
    modifier: Modifier = Modifier,
    style: AccordionTheme.Style = AccordionTheme.Style.Normal,
    toggle: AccordionTheme.Toggle = AccordionTheme.Toggle.StartPlus,
    type: AccordionTheme.Type = AccordionTheme.Type.Basic
) {
    var expanded by remember(items.map { it.title }) {
        mutableStateOf(items.indices.filter { items[it].isExpanded }.toSet())
    }
    val theme = getTheme(style, false)

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(Palette.Transparent),
        border = BorderStroke(1.dp, theme.borderColor),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = theme.shape
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            items.forEachIndexed { index, item ->
                val isExpanded = index in expanded
                val itemTheme = getTheme(style, isExpanded)
                val expandedState = stringResource(if (isExpanded) R.string.preline_expanded else R.string.preline_collapsed)
                if (index > 0 && style.hasDividers) {
                    HorizontalDivider(thickness = 1.dp, color = PrelineTheme.colors.layerBorder)
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .semantics { stateDescription = expandedState }
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            role = Role.Button
                        ) {
                            expanded = when (type) {
                                AccordionTheme.Type.Basic      -> if (isExpanded) emptySet() else setOf(index)
                                AccordionTheme.Type.AlwaysOpen -> if (isExpanded) expanded - index else expanded + index
                            }
                        }
                ) {
                    if (isExpanded && style == AccordionTheme.Style.BorderedOpen) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(Palette.Transparent),
                            border = BorderStroke(1.dp, PrelineTheme.colors.layerBorder),
                            elevation = CardDefaults.cardElevation(0.dp),
                            shape = itemTheme.shape
                        ) { ItemView(item, isExpanded, toggle, itemTheme) }
                    } else {
                        ItemView(item, isExpanded, toggle, itemTheme)
                    }
                }
            }
        }
    }
}

private val AccordionTheme.Style.hasDividers: Boolean
    get() = this == AccordionTheme.Style.Bordered || this == AccordionTheme.Style.Divider

@Composable
private fun ItemView(item: AccordionItem, isExpanded: Boolean, toggle: AccordionTheme.Toggle, theme: AccordionTheme) {
    val startToggle = toggle == AccordionTheme.Toggle.StartPlus || toggle == AccordionTheme.Toggle.StartArrow
    val endToggle = toggle == AccordionTheme.Toggle.EndArrow || toggle == AccordionTheme.Toggle.EndPlus
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(durationMillis = 250))
            .padding(horizontal = theme.horizontalPadding)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (startToggle) {
                ToggleIcon(toggle, isExpanded, theme, Modifier.padding(end = theme.gap))
            }

            item.icon?.let {
                Icon(
                    painter = it,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = theme.gap)
                        .size(theme.iconSize),
                    tint = theme.titleColor
                )
            }

            Text(
                text = item.title,
                modifier = Modifier.weight(1f, fill = endToggle),
                color = theme.titleColor,
                style = theme.titleStyle,
            )

            item.badge?.let {
                Badge(
                    placeholder = it.title,
                    type = it.type,
                    color = it.color,
                    size = it.size,
                    shape = it.shape,
                    modifier = Modifier.padding(start = theme.gap)
                )
            }

            if (endToggle) {
                ToggleIcon(toggle, isExpanded, theme, Modifier.padding(start = theme.gap))
            }
        }

        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(animationSpec = tween(250)) + fadeIn(animationSpec = tween(150)),
            exit = shrinkVertically(animationSpec = tween(250)) + fadeOut(animationSpec = tween(100))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = if (startToggle) theme.iconSize + theme.gap else 0.dp, bottom = 12.dp)
            ) { item.content() }
        }
    }
}

@Composable
private fun ToggleIcon(toggle: AccordionTheme.Toggle, isExpanded: Boolean, theme: AccordionTheme, modifier: Modifier) {
    val icon = when (toggle) {
        AccordionTheme.Toggle.StartPlus, AccordionTheme.Toggle.EndPlus -> if (isExpanded) R.drawable.ic_minus else R.drawable.ic_plus
        else                                                         -> if (isExpanded) R.drawable.ic_chevron_up else R.drawable.ic_chevron_down
    }
    Icon(
        painter = painterResource(icon),
        contentDescription = null,
        modifier = modifier.size(theme.iconSize),
        tint = theme.titleColor
    )
}
