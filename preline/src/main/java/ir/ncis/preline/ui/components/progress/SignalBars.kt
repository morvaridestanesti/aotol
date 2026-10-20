package ir.ncis.preline.ui.components.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.theme.PrelineTheme

@Composable
fun SignalBars(
    signal: ProgressTheme.Signal,
    modifier: Modifier = Modifier,
    label: String? = null,
) {
    val color = getSignalColor(signal)
    val filledBars = getSignalBars(signal)

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp), verticalAlignment = Alignment.Bottom) {
            repeat(4) { index ->
                Box(
                    modifier = Modifier
                        .size(width = 4.dp, height = (6 + index * 4).dp)
                        .background(
                            if (index < filledBars) color else PrelineTheme.colors.surface2,
                            RoundedCornerShape(1.dp)
                        )
                )
            }
        }
        label?.let { Text(text = it, color = color, style = PrelineTheme.typography.xs) }
    }
}
