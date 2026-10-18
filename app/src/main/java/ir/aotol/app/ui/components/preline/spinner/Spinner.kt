package ir.aotol.app.ui.components.preline.spinner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.aotol.app.ui.components.preline.Palette

@Composable
fun Spinner(
    modifier: Modifier = Modifier,
    max: Int = 100,
    progress: Int = 30,
    size: SpinnerTheme.Size = SpinnerTheme.Size.Large,
    label: String? = null,
    circularLine: Boolean = false
) {
    val theme = getTheme(size)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Box(modifier = Modifier.size(theme.spinnerSize)) {
            if (circularLine) {
                CircularProgressIndicator(
                    progress = { 0f },
                    modifier = Modifier.fillMaxSize(),
                    strokeWidth = theme.strokeWidth,
                    trackColor = theme.trackColor,
                    strokeCap = StrokeCap.Round,
                    gapSize = 0.dp
                )
            }
            CircularProgressIndicator(
                progress = { progress.toFloat() / max },
                modifier = Modifier.fillMaxSize(),
                color = theme.progressColor,
                trackColor = Palette.Transparent,
                strokeWidth = theme.strokeWidth,
                strokeCap = StrokeCap.Round,
                gapSize = 0.dp
            )
        }
        label?.let {
            Text(
                text = it,
                color = theme.labelColor,
                style = theme.textStyle,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF68E7F9)
@Composable
private fun Preview() {
    Spinner(
        progress = 50,
        size = SpinnerTheme.Size.Large,
        label = "Loading",
        circularLine = true
    )
}
