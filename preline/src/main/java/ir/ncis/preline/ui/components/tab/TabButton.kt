package ir.ncis.preline.ui.components.tab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import ir.ncis.preline.helpers.disabledAlpha
import ir.ncis.preline.helpers.invisibleClickable
import ir.ncis.preline.ui.components.badge.Badge
import ir.ncis.preline.ui.components.tab.data.TabItem
import ir.ncis.preline.ui.theme.Palette

@Composable
internal fun TabButton(
    item: TabItem,
    modifier: Modifier = Modifier,
    type: TabTheme.Type = TabTheme.Type.Base,
    contentAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    onClick: () -> Unit
) {
    val theme = getTheme(type, item.isSelected, item.enabled)

    when (type) {
        TabTheme.Type.Base, TabTheme.Type.Bordered -> {
            Column(
                modifier = modifier
                    .width(IntrinsicSize.Max)
                    .invisibleClickable(role = Role.Tab) { if (item.enabled) onClick.invoke() }
                    .semantics { selected = item.isSelected }
            ) {
                ItemRow(
                    item = item,
                    theme = theme,
                    contentAlignment = contentAlignment
                )
                HorizontalDivider(color = theme.dividerColor)
            }
        }

        TabTheme.Type.BrandPills, TabTheme.Type.GrayPills, TabTheme.Type.Segment -> {
            Card(
                modifier = modifier
                    .invisibleClickable(role = Role.Tab) { if (item.enabled) onClick.invoke() }
                    .semantics { selected = item.isSelected },
                colors = CardDefaults.cardColors(if (item.isSelected) theme.cardBackground else Palette.Transparent),
                elevation = CardDefaults.cardElevation(0.dp),
                shape = theme.shape
            ) {
                Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                    ItemRow(item = item, theme = theme, contentAlignment = contentAlignment)
                }
            }
        }
    }
}

@Composable
private fun ItemRow(
    item: TabItem,
    theme: TabTheme,
    contentAlignment: Alignment.Horizontal,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, contentAlignment),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = theme.verticalPadding)
            .disabledAlpha(!item.enabled)
    ) {
        item.leadingIcon?.let {
            Box(modifier = Modifier.size(theme.iconSize)) {
                Icon(
                    painter = it,
                    contentDescription = null,
                    tint = theme.itemColor,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp)
                )
            }
        }

        item.placeholder?.let {
            Text(
                text = it,
                color = theme.itemColor,
                style = theme.textStyle,
            )
        }

        item.badge?.let { count ->
            Badge(
                placeholder = count.toString(),
                type = theme.badgeType,
                color = theme.badgeColor,
                size = theme.badgeSize,
                shape = theme.badgeShape
            )
        }

        item.trailingIcon?.let {
            Box(modifier = Modifier.size(theme.iconSize)) {
                Icon(
                    painter = it,
                    contentDescription = null,
                    tint = theme.itemColor,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp)
                )
            }
        }
    }
}
