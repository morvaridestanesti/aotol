package ir.aotol.app.ui.components.preline.tab

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.aotol.app.R
import ir.aotol.app.helpers.disabledAlpha
import ir.aotol.app.helpers.invisibleClickable
import ir.aotol.app.ui.components.preline.badge.Badge
import ir.aotol.app.ui.components.preline.tab.data.TabItem
import ir.aotol.app.ui.components.preline.Palette

@Composable
internal fun TabButton(
    item: TabItem,
    modifier: Modifier = Modifier,
    type: TabTheme.Type = TabTheme.Type.Base,
    onClick: () -> Unit
) {
    val theme = getTheme(type, item.isSelected, item.enabled)

    when (type) {
        TabTheme.Type.Base, TabTheme.Type.Bordered -> {
            Column(
                modifier = modifier
                    .width(IntrinsicSize.Max)
                    .invisibleClickable { if (item.enabled) onClick.invoke() }
            ) {
                ItemRow(
                    item = item,
                    theme = theme
                )
                HorizontalDivider(color = theme.dividerColor)
            }
        }

        TabTheme.Type.BrandPills, TabTheme.Type.GrayPills, TabTheme.Type.Segment -> {
            // Every chip keeps the same padding, so the label does not move when it becomes selected.
            Card(
                modifier = modifier.invisibleClickable { if (item.enabled) onClick.invoke() },
                colors = CardDefaults.cardColors(if (item.isSelected) theme.cardBackground else Palette.Transparent),
                elevation = CardDefaults.cardElevation(0.dp),
                shape = theme.shape
            ) {
                Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                    ItemRow(item = item, theme = theme)
                }
            }
        }
    }
}

@Composable
private fun ItemRow(
    item: TabItem,
    theme: TabTheme,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
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

@Preview(showBackground = true)
@Composable
private fun TabItemPreview() {
    TabButton(
        item = TabItem(
            index = 1,
            placeholder = "Projects",
            leadingIcon = painterResource(R.drawable.ic_home),
            trailingIcon = painterResource(R.drawable.ic_chevron_down),
            badge = 3,
            isSelected = false,
            enabled = true
        ),
        type = TabTheme.Type.Bordered
    ) { }
}
