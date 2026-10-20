package ir.ncis.preline.ui.components.progress

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium

@Composable
fun Progress(
    modifier: Modifier = Modifier,
    title: String? = null,
    value: Int = 30,
    type: ProgressTheme.Type = ProgressTheme.Type.Base,
    steps: Int = 4,
) {
    val progress = value.coerceIn(0, 100)
    val theme = getTheme()

    when (type) {
        ProgressTheme.Type.Base                -> {
            ProgressBar(
                modifier = modifier,
                value = progress,
                theme = theme
            )
        }

        ProgressTheme.Type.TitleLabel          -> {
            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    title?.let {
                        Text(
                            text = it,
                            color = theme.titleColor,
                            style = theme.titleStyle,
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "$progress%",
                        color = theme.valueColor,
                        style = theme.valueStyle,
                    )
                }
                ProgressBar(
                    value = progress,
                    theme = theme
                )
            }
        }

        ProgressTheme.Type.TrailingLabel       -> {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProgressBar(
                    modifier = Modifier.weight(1f),
                    value = progress,
                    theme = theme
                )
                Text(
                    text = "$progress%",
                    modifier = Modifier.padding(start = 12.dp),
                    color = theme.valueColor,
                    style = theme.valueStyle,
                )
            }
        }

        ProgressTheme.Type.TopFloatingLabel    -> {
            FloatingLabelProgress(
                modifier = modifier,
                value = progress,
                labelAbove = true,
                theme = theme
            )
        }

        ProgressTheme.Type.BottomFloatingLabel -> {
            FloatingLabelProgress(
                modifier = modifier,
                value = progress,
                labelAbove = false,
                theme = theme
            )
        }

        ProgressTheme.Type.WithinProgressBar   -> {
            ProgressBar(
                modifier = modifier,
                value = progress,
                showValue = true,
                theme = theme
            )
        }

        ProgressTheme.Type.Steps               -> {
            StepsProgress(
                modifier = modifier,
                value = progress,
                steps = steps.coerceAtLeast(1),
                theme = theme
            )
        }
    }
}

@Composable
private fun StepsProgress(
    modifier: Modifier = Modifier,
    value: Int,
    steps: Int,
    theme: ProgressTheme
) {
    val filledSteps = value * steps / 100
    Row(
        modifier = modifier
            .fillMaxWidth()
            .progressSemantics(value / 100f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(steps) { index ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(theme.stepHeight)
                    .background(if (index < filledSteps) theme.progressColor else theme.emptyStepColor)
            )
        }
        if (value == 100) {
            Icon(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(16.dp)
                    .background(theme.progressColor, CircleShape)
                    .padding(2.dp),
                tint = theme.withinValueColor
            )
        } else {
            Text(
                text = "$value%",
                modifier = Modifier.padding(start = 4.dp),
                color = theme.valueColor,
                style = theme.valueStyle,
            )
        }
    }
}

@Composable
private fun ProgressBar(
    modifier: Modifier = Modifier,
    value: Int,
    showValue: Boolean = false,
    theme: ProgressTheme
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(if (showValue) theme.withValueBarHeight else theme.barHeight)
            .progressSemantics(value / 100f),
        colors = CardDefaults.cardColors(theme.trackColor),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = theme.shape
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(value / 100f)
                .fillMaxHeight(),
            colors = CardDefaults.cardColors(theme.progressColor),
            elevation = CardDefaults.cardElevation(0.dp),
            shape = theme.shape
        ) {
            if (showValue) {
                Text(
                    text = "$value%",
                    textAlign = TextAlign.Center,
                    color = theme.withinValueColor,
                    style = PrelineTheme.typography.xs,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun FloatingLabelProgress(
    modifier: Modifier = Modifier,
    value: Int,
    labelAbove: Boolean,
    theme: ProgressTheme
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        if (labelAbove) {
            FloatingLabelRow(value = value, theme = theme)
            ProgressBar(
                modifier = Modifier.padding(top = 6.dp),
                value = value,
                theme = theme
            )
        } else {
            ProgressBar(
                modifier = Modifier.padding(bottom = 6.dp),
                value = value,
                theme = theme
            )
            FloatingLabelRow(value = value, theme = theme)
        }
    }
}

@Composable
private fun FloatingLabelRow(value: Int, theme: ProgressTheme) {
    val fraction = value.coerceIn(0, 100) / 100f
    Row(modifier = Modifier.fillMaxWidth()) {
        if (fraction > 0f) Spacer(Modifier.weight(fraction))
        FloatingLabel(value = value, theme = theme)
        if (fraction < 1f) Spacer(Modifier.weight(1f - fraction))
    }
}

@Composable
private fun FloatingLabel(
    value: Int,
    modifier: Modifier = Modifier,
    theme: ProgressTheme
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(theme.floatingBackground),
        border = BorderStroke(1.dp, theme.floatingBorder),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = theme.floatingShape
    ) {
        Text(
            text = "$value%",
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            color = theme.floatingTextColor,
            style = PrelineTheme.typography.xs.medium,
        )
    }
}
