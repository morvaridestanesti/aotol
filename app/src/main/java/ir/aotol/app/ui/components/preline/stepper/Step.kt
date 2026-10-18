package ir.aotol.app.ui.components.preline.stepper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.aotol.app.R
import ir.aotol.app.ui.components.preline.stepper.data.StepperItem

@Composable
fun Step(
    item: StepperItem,
    modifier: Modifier = Modifier
) {
    val theme = getTheme(item.type)

    if (item.direction == StepperTheme.ItemDirection.Horizontal) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (item.position != StepperTheme.ItemPosition.Start) {
                HorizontalDivider(Modifier.weight(1f), 1.dp, if (item.type == StepperTheme.ItemType.Success) theme.dividerSuccessColor else theme.dividerActiveColor)
            } else if (item.alignment == StepperTheme.ItemAlignment.Center) {
                Spacer(Modifier.weight(1f))
            }
            StepCard(item, theme)
            item.title?.let {
                Text(
                    text = it,
                    color = theme.titleColor,
                    style = theme.titleStyle,
                )
            }
            if (item.position != StepperTheme.ItemPosition.End) HorizontalDivider(
                Modifier.weight(1f), 1.dp, getDividerColor(item.type)
            ) else if (item.alignment == StepperTheme.ItemAlignment.Center) {
                Spacer(Modifier.weight(1f))
            }
        }
    } else {
        Column(
            modifier = modifier,
            horizontalAlignment = when (item.alignment) {
                StepperTheme.ItemAlignment.Center -> Alignment.CenterHorizontally
                StepperTheme.ItemAlignment.End    -> Alignment.End
                StepperTheme.ItemAlignment.Start  -> Alignment.Start
            },
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (item.position != StepperTheme.ItemPosition.Start) {
                    HorizontalDivider(Modifier.weight(1f), 1.dp, if (item.type == StepperTheme.ItemType.Success) theme.dividerSuccessColor else theme.dividerActiveColor)
                } else if (item.alignment == StepperTheme.ItemAlignment.Center) {
                    Spacer(Modifier.weight(1f))
                }
                StepCard(item, theme)
                if (item.position != StepperTheme.ItemPosition.End) HorizontalDivider(
                    Modifier.weight(1f), 1.dp, getDividerColor(item.type)
                ) else if (item.alignment == StepperTheme.ItemAlignment.Center) {
                    Spacer(Modifier.weight(1f))
                }
            }
            if (listOf(item.title, item.description).any { it != null }) {
                Column(
                    horizontalAlignment = when (item.alignment) {
                        StepperTheme.ItemAlignment.Center -> Alignment.CenterHorizontally
                        StepperTheme.ItemAlignment.End    -> Alignment.End
                        StepperTheme.ItemAlignment.Start  -> Alignment.Start
                    },
                ) {
                    item.title?.let {
                        Text(
                            text = it,
                            color = theme.titleColor,
                            style = theme.titleStyle,
                        )
                    }
                    item.description?.let {
                        Text(
                            text = it,
                            textAlign = when (item.alignment) {
                                StepperTheme.ItemAlignment.Center -> TextAlign.Center
                                StepperTheme.ItemAlignment.End    -> TextAlign.End
                                StepperTheme.ItemAlignment.Start  -> TextAlign.Start
                            },
                            color = theme.descriptionColor,
                            style = theme.descriptionStyle,
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun StepCard(item: StepperItem, theme: StepperTheme) {
    Card(
        modifier = Modifier.size(theme.cardSize),
        colors = CardDefaults.cardColors(getCardBackgroundColor(item.type)),
        elevation = CardDefaults.cardElevation(1.dp),
        shape = theme.shape
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (item.type) {
                StepperTheme.ItemType.ActiveCheck, StepperTheme.ItemType.Invalid, StepperTheme.ItemType.Success -> Icon(
                    painter = painterResource(if (item.type == StepperTheme.ItemType.Invalid) R.drawable.ic_clear else R.drawable.ic_check),
                    contentDescription = null,
                    modifier = Modifier.size(theme.iconSize),
                    tint = getCardContentColor(item.type)
                )

                StepperTheme.ItemType.ActiveNumber, StepperTheme.ItemType.Default                  -> Text(
                    text = item.step.toString(),
                    color = getCardContentColor(item.type),
                    style = theme.stepStyle,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() = Step(
    StepperItem(
        step = 1,
        title = "Step",
        description = "This is a description text.",
        alignment = StepperTheme.ItemAlignment.Center,
        direction = StepperTheme.ItemDirection.Vertical,
        type = StepperTheme.ItemType.Default,
        position = StepperTheme.ItemPosition.Middle
    )
)
