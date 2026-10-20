package ir.ncis.preline.ui.components.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.components.card.data.CardGroupItem

@Composable
fun CardGroup(
    items: List<CardGroupItem>,
    modifier: Modifier = Modifier,
    direction: CardTheme.Direction = CardTheme.Direction.Vertical,
    style: CardTheme.Style = CardTheme.Style.Bordered,
) {
    val theme = getTheme(style)
    CardContainer(modifier = modifier, theme = theme) {
        when (direction) {
            CardTheme.Direction.Vertical   -> items.forEachIndexed { index, item ->
                if (index > 0) HorizontalDivider(thickness = 1.dp, color = theme.divider)
                item.media?.invoke()
                CardBody(theme, item.content)
                item.footer?.let { CardFooter(theme, it) }
            }

            CardTheme.Direction.Horizontal -> Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                items.forEachIndexed { index, item ->
                    if (index > 0) VerticalDivider(thickness = 1.dp, color = theme.divider)
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        item.media?.invoke()
                        CardBody(theme, item.content)
                        Spacer(Modifier.weight(1f))
                        item.footer?.let { CardFooter(theme, it) }
                    }
                }
            }
        }
    }
}
