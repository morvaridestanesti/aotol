package ir.ncis.preline.ui.components.progress

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.semiBold

@Composable
fun ActivityIndicator(
    value: Int,
    modifier: Modifier = Modifier,
    title: String? = null,
    change: String? = null,
    changeUp: Boolean = true,
) {
    val progress = value.coerceIn(0, 100)
    val filledTicks = progress * Ticks / 100
    val filledColor = PrelineTheme.colors.primary
    val emptyColor = PrelineTheme.colors.surface2

    Row(
        modifier = modifier.progressSemantics(progress / 100f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Canvas(modifier = Modifier.size(32.dp)) {
            val stroke = 3.dp.toPx()
            val outer = size.minDimension / 2 - stroke / 2
            repeat(Ticks) { index ->
                rotate(degrees = index * 360f / Ticks) {
                    drawLine(
                        color = if (index < filledTicks) filledColor else emptyColor,
                        start = Offset(center.x, center.y - outer * 0.55f),
                        end = Offset(center.x, center.y - outer),
                        strokeWidth = stroke,
                        cap = StrokeCap.Round
                    )
                }
            }
        }
        Column {
            title?.let { Text(text = it, color = PrelineTheme.colors.mutedForeground1, style = PrelineTheme.typography.xs) }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(text = "$progress%", color = PrelineTheme.colors.foreground, style = PrelineTheme.typography.sm.semiBold)
                change?.let {
                    val changeColor = if (changeUp) PrelineTheme.colors.green.solid else PrelineTheme.colors.red.solid
                    Icon(
                        painter = painterResource(if (changeUp) R.drawable.ic_trending_up else R.drawable.ic_trending_down),
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = changeColor
                    )
                    Text(text = it, color = changeColor, style = PrelineTheme.typography.xs)
                }
            }
        }
    }
}

private const val Ticks = 12
