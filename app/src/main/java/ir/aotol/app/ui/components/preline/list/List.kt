package ir.aotol.app.ui.components.preline.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.aotol.app.R
import ir.aotol.app.helpers.disabledAlpha
import ir.aotol.app.ui.components.preline.badge.Badge
import ir.aotol.app.ui.components.preline.badge.BadgeTheme
import ir.aotol.app.ui.components.preline.list.data.ListItem
import ir.aotol.app.ui.components.preline.styled_icon.StyledIcon
import ir.aotol.app.ui.components.preline.bold
import ir.aotol.app.ui.components.preline.medium

@Composable
fun List(
    items: List<ListItem>,
    modifier: Modifier = Modifier,
    shape: ListTheme.Shape = ListTheme.Shape.Circular,
    style: ListTheme.Style = ListTheme.Style.Solid,
    color: ListTheme.Color = ListTheme.Color.Dark,
    state: ListTheme.State = ListTheme.State.Default,
    type: ListTheme.Type = ListTheme.Type.None,
    direction: ListTheme.Direction = ListTheme.Direction.Vertical,
) {
    val theme = getTheme(style, color, shape, state)

    when (direction) {
        ListTheme.Direction.Horizontal -> {
            LazyRow(
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(
                    items = items,
                    key = { index, _ -> index }
                ) { index, item ->
                    Box(
                        modifier = Modifier
                            .width(intrinsicSize = IntrinsicSize.Max)
                            .height(intrinsicSize = IntrinsicSize.Min)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            if (index > 0) {
                                if (item.circularSeparator) {
                                    Card(
                                        modifier = Modifier.size(theme.discSize),
                                        colors = CardDefaults.cardColors(theme.separatorColor),
                                        elevation = CardDefaults.cardElevation(0.dp),
                                        shape = CircleShape
                                    ) {}
                                } else if (item.slashSeparator) {
                                    Text(
                                        text = "/",
                                        color = theme.separatorColor,
                                        style = theme.textStyle.bold,
                                    )
                                }
                            }
                            Text(
                                text = item.placeholder,
                                color = theme.textColor,
                                style = theme.textStyle.medium,
                            )
                        }
                        if (item.horizontalDivider) {
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.TopStart),
                                thickness = 1.dp,
                                color = theme.borderColor
                            )
                        }
                        if (item.verticalDivider) {
                            VerticalDivider(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .align(Alignment.TopEnd),
                                thickness = 1.dp,
                                color = theme.borderColor
                            )
                        }
                    }
                }
            }
        }

        ListTheme.Direction.Vertical   -> {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items.forEachIndexed { index, item ->
                    Box(
                        modifier = Modifier
                            .height(intrinsicSize = IntrinsicSize.Min)
                            .disabledAlpha(state == ListTheme.State.Disabled)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            when (type) {
                                ListTheme.Type.Decimal -> {
                                    Text(
                                        text = "${index + 1}.",
                                        color = theme.decimalTextColor,
                                        style = theme.textStyle,
                                    )
                                }

                                ListTheme.Type.Disc    -> {
                                    Card(
                                        modifier = Modifier.size(theme.discSize),
                                        colors = CardDefaults.cardColors(theme.discBackground),
                                        elevation = CardDefaults.cardElevation(0.dp),
                                        shape = CircleShape
                                    ) {}
                                }

                                ListTheme.Type.None    -> {}
                            }
                            item.featuredIcon?.let {
                                StyledIcon(
                                    icon = it,
                                    type = theme.featuredIconType,
                                    color = theme.featuredIconColor,
                                    shape = theme.featuredIconShape,
                                    size = theme.featuredIconSize
                                )
                            }
                            if (item.circularSeparator) {
                                Card(
                                    modifier = Modifier.size(theme.discSize),
                                    colors = CardDefaults.cardColors(theme.separatorColor),
                                    elevation = CardDefaults.cardElevation(0.dp),
                                    shape = CircleShape
                                ) {}
                            } else if (item.slashSeparator) {
                                Text(
                                    text = "/",
                                    color = theme.separatorColor,
                                    style = theme.textStyle.bold,
                                )
                            }
                            item.icon?.let {
                                Icon(
                                    painter = it,
                                    contentDescription = null,
                                    tint = theme.iconColor,
                                    modifier = Modifier.size(theme.iconSize)
                                )
                            }
                            Text(
                                text = item.placeholder,
                                color = theme.textColor,
                                style = theme.textStyle.medium,
                            )
                            item.badge?.let {
                                Badge(
                                    placeholder = it.toString(),
                                    color = BadgeTheme.Color.Blue,
                                    type = BadgeTheme.Type.Solid,
                                    shape = BadgeTheme.Shape.Pilled,
                                    size = BadgeTheme.Size.Small
                                )
                            }
                        }
                        if (item.horizontalDivider) {
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.TopStart),
                                thickness = 1.dp,
                                color = theme.borderColor
                            )
                        }
                        if (item.verticalDivider) {
                            VerticalDivider(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .align(Alignment.TopEnd),
                                thickness = 1.dp,
                                color = theme.borderColor
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Default() {
    List(
        items = getPreviewItems(),
        style = ListTheme.Style.SoftOutlined,
        color = ListTheme.Color.Blue,
        type = ListTheme.Type.Decimal,
        direction = ListTheme.Direction.Vertical,
        state = ListTheme.State.Default
    )
}

@Preview(showBackground = true)
@Composable
private fun Active() {
    List(
        items = getPreviewItems(),
        style = ListTheme.Style.SoftOutlined,
        color = ListTheme.Color.Blue,
        type = ListTheme.Type.Decimal,
        direction = ListTheme.Direction.Vertical,
        state = ListTheme.State.Active
    )
}

@Preview(showBackground = true)
@Composable
private fun Hover() {
    List(
        items = getPreviewItems(),
        style = ListTheme.Style.SoftOutlined,
        color = ListTheme.Color.Blue,
        type = ListTheme.Type.Decimal,
        direction = ListTheme.Direction.Vertical,
        state = ListTheme.State.Hover
    )
}

@Preview(showBackground = true)
@Composable
private fun Disabled() {
    List(
        items = getPreviewItems(),
        style = ListTheme.Style.SoftOutlined,
        color = ListTheme.Color.Blue,
        type = ListTheme.Type.Decimal,
        direction = ListTheme.Direction.Vertical,
        state = ListTheme.State.Disabled
    )
}

@Composable
private fun getPreviewItems(): List<ListItem> = listOf(
    ListItem(placeholder = "Item 1", featuredIcon = painterResource(R.drawable.ic_check), circularSeparator = false, slashSeparator = false, badge = 5),
    ListItem(placeholder = "Item 2", icon = painterResource(R.drawable.ic_bell), circularSeparator = true, badge = 12, verticalDivider = true),
    ListItem(placeholder = "Item 3", slashSeparator = true, horizontalDivider = true)
)
