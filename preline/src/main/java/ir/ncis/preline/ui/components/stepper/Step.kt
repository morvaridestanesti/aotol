package ir.ncis.preline.ui.components.stepper

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.ui.components.spinner.Spinner
import ir.ncis.preline.ui.components.spinner.SpinnerTheme
import ir.ncis.preline.ui.components.stepper.data.StepperItem

@Composable
fun Step(
    item: StepperItem,
    modifier: Modifier = Modifier,
    previousType: StepperTheme.ItemType? = null,
) {
    val theme = getTheme(item.type)
    val textAlignment = when (item.alignment) {
        StepperTheme.ItemAlignment.Center -> Alignment.CenterHorizontally
        StepperTheme.ItemAlignment.End    -> Alignment.End
        StepperTheme.ItemAlignment.Start  -> Alignment.Start
    }

    if (item.direction == StepperTheme.ItemDirection.Horizontal) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LeadingLine(item, previousType)
            StepCard(item, theme)
            item.title?.let {
                Text(
                    text = it,
                    color = theme.titleColor,
                    style = theme.titleStyle,
                )
            }
            TrailingLine(item)
        }
    } else {
        Column(
            modifier = modifier,
            horizontalAlignment = textAlignment,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LeadingLine(item, previousType)
                StepCard(item, theme)
                TrailingLine(item)
            }
            if (item.title != null || item.description != null) {
                Column(horizontalAlignment = textAlignment) {
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
private fun RowScope.LeadingLine(item: StepperItem, previousType: StepperTheme.ItemType?) {
    when {
        item.alignment == StepperTheme.ItemAlignment.Start -> {}
        item.position == StepperTheme.ItemPosition.Start   -> if (item.alignment == StepperTheme.ItemAlignment.Center) Spacer(Modifier.weight(1f))
        else                                                -> HorizontalDivider(Modifier.weight(1f), 1.dp, getDividerColor(previousType ?: StepperTheme.ItemType.Default))
    }
}

@Composable
private fun RowScope.TrailingLine(item: StepperItem) {
    when {
        item.alignment == StepperTheme.ItemAlignment.End -> {}
        item.position == StepperTheme.ItemPosition.End   -> Spacer(Modifier.weight(1f))
        else                                              -> HorizontalDivider(Modifier.weight(1f), 1.dp, getDividerColor(item.type))
    }
}

@Composable
internal fun StepCard(item: StepperItem, theme: StepperTheme) {
    Card(
        modifier = Modifier.size(theme.cardSize),
        colors = CardDefaults.cardColors(getCardBackgroundColor(item.type)),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = theme.shape
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when {
                item.loading       -> Spinner(type = SpinnerTheme.Type.Circular, size = SpinnerTheme.Size.XS)
                item.avatar != null -> Image(
                    painter = item.avatar,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                else               -> StepMarkerContent(item, theme)
            }
        }
    }
}

@Composable
private fun StepMarkerContent(item: StepperItem, theme: StepperTheme) {
    when (item.type) {
        StepperTheme.ItemType.ActiveCheck, StepperTheme.ItemType.Invalid, StepperTheme.ItemType.Success -> Icon(
            painter = painterResource(if (item.type == StepperTheme.ItemType.Invalid) R.drawable.ic_clear else R.drawable.ic_check),
            contentDescription = null,
            modifier = Modifier.size(theme.iconSize),
            tint = getCardContentColor(item.type)
        )

        StepperTheme.ItemType.ActiveNumber, StepperTheme.ItemType.Default                  -> item.icon?.let {
            Icon(
                painter = it,
                contentDescription = null,
                modifier = Modifier.size(theme.iconSize),
                tint = getCardContentColor(item.type)
            )
        } ?: Text(
            text = item.step.toString(),
            color = getCardContentColor(item.type),
            style = theme.stepStyle,
        )
    }
}
