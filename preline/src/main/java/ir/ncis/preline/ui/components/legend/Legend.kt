package ir.ncis.preline.ui.components.legend

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.PrelineTheme

@Composable
fun Legend(
    modifier: Modifier = Modifier,
    label: String? = null,
    type: LegendTheme.Type = LegendTheme.Type.Circled,
    color: LegendTheme.Color = LegendTheme.Color.Blue,
) {
    val theme = getTheme(color)

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Indicator(type, theme.indicatorColor)
        label?.let { Text(text = it, color = theme.labelColor, style = PrelineTheme.typography.sm) }
    }
}

@Composable
private fun Indicator(type: LegendTheme.Type, color: Color) {
    when (type) {
        LegendTheme.Type.Circled -> Box(Modifier.size(8.dp).background(color, CircleShape))
        LegendTheme.Type.Solid   -> Box(Modifier.size(width = 12.dp, height = 5.dp).background(color, RoundedCornerShape(2.dp)))
        LegendTheme.Type.Striped -> Canvas(Modifier.size(width = 12.dp, height = 6.dp)) {
            val stroke = 1.5.dp.toPx()
            val step = size.width / 3
            repeat(3) { i ->
                val x = step * i + stroke / 2
                drawLine(color, Offset(x, size.height), Offset(x + step - stroke, 0f), stroke, StrokeCap.Round)
            }
        }
    }
}
