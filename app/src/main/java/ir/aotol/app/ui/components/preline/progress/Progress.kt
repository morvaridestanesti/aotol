package ir.aotol.app.ui.components.preline.progress

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.aotol.app.ui.components.preline.PrelineTheme
import ir.aotol.app.ui.components.preline.bold
import ir.aotol.app.ui.components.preline.medium

@Composable
fun Progress(
    modifier: Modifier = Modifier,
    title: String? = null,
    value: Int = 30,
    type: ProgressTheme.Type = ProgressTheme.Type.Base,
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
                        style = theme.valueStyle.medium,
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
                    color = theme.titleColor,
                    style = theme.valueStyle.bold,
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
            .height(if (showValue) theme.withValueBarHeight else theme.barHeight),
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
                    color = theme.valueColor,
                    style = PrelineTheme.typography.sm.medium,
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

// A zero weight is not allowed, so the spacer on a side that has no room is left out.
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
            style = PrelineTheme.typography.sm.medium,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF777777)
@Composable
private fun ProgressBase() {
    Progress(
        value = 30,
        type = ProgressTheme.Type.Base
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF777777)
@Composable
private fun ProgressTitleLabel() {
    Progress(
        title = "Progress Title",
        value = 50,
        type = ProgressTheme.Type.TitleLabel
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF777777)
@Composable
private fun ProgressTrailingLabel() {
    Progress(
        value = 70,
        type = ProgressTheme.Type.TrailingLabel
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF777777)
@Composable
private fun ProgressTopFloatingLabel() {
    Progress(
        value = 60,
        type = ProgressTheme.Type.TopFloatingLabel
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF777777)
@Composable
private fun ProgressBottomFloatingLabel() {
    Progress(
        value = 40,
        type = ProgressTheme.Type.BottomFloatingLabel
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF777777)
@Composable
private fun ProgressWithinProgressBar() {
    Progress(
        value = 80,
        type = ProgressTheme.Type.WithinProgressBar
    )
}
