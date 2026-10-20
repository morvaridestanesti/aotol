package ir.ncis.preline.ui.components.spinner

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R

@Composable
fun Spinner(
    modifier: Modifier = Modifier,
    type: SpinnerTheme.Type = SpinnerTheme.Type.Line,
    size: SpinnerTheme.Size = SpinnerTheme.Size.Default,
    label: String? = null,
) {
    val theme = getTheme(size)
    val loading = stringResource(R.string.preline_loading)

    Column(
        modifier = modifier
            .progressSemantics()
            .semantics(mergeDescendants = true) { if (label == null) contentDescription = loading },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        when (type) {
            SpinnerTheme.Type.Dots -> ThreeDots(theme)
            else                   -> Ring(type, theme)
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

@Composable
private fun Ring(type: SpinnerTheme.Type, theme: SpinnerTheme) {
    val rotation by rememberInfiniteTransition(label = "spinner").animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(durationMillis = 1000, easing = LinearEasing)),
        label = "rotation"
    )
    Canvas(
        modifier = Modifier
            .size(theme.spinnerSize)
            .rotate(rotation)
    ) {
        val stroke = theme.strokeWidth.toPx()
        val topLeft = Offset(stroke / 2, stroke / 2)
        val arcSize = Size(size.width - stroke, size.height - stroke)
        if (type == SpinnerTheme.Type.Line) {
            drawArc(theme.trackColor, startAngle = 0f, sweepAngle = 360f, useCenter = false, topLeft = topLeft, size = arcSize, style = Stroke(stroke))
        }
        drawArc(
            color = theme.progressColor,
            startAngle = -90f,
            sweepAngle = if (type == SpinnerTheme.Type.Line) 90f else 270f,
            useCenter = false,
            topLeft = topLeft,
            size = arcSize,
            style = Stroke(stroke, cap = StrokeCap.Round)
        )
    }
}

@Composable
private fun ThreeDots(theme: SpinnerTheme) {
    val transition = rememberInfiniteTransition(label = "dots")
    Row(horizontalArrangement = Arrangement.spacedBy(theme.dotSize / 2)) {
        repeat(3) { index ->
            val alpha by transition.animateFloat(
                initialValue = 0.25f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 500),
                    repeatMode = RepeatMode.Reverse,
                    initialStartOffset = StartOffset(index * 170)
                ),
                label = "dot$index"
            )
            Box(
                modifier = Modifier
                    .size(theme.dotSize)
                    .alpha(alpha)
                    .background(theme.dotColor, CircleShape)
            )
        }
    }
}
