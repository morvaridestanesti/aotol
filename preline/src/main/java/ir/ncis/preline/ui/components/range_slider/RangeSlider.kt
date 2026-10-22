package ir.ncis.preline.ui.components.range_slider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.setProgress
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ir.ncis.preline.helpers.disabledAlpha
import ir.ncis.preline.ui.theme.PrelineTheme
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun RangeSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..100f,
    step: Float = 1f,
    tooltip: Boolean = false,
    labels: Int = 0,
    minorTicks: Int = 0,
    enabled: Boolean = true,
    valueLabel: (Float) -> String = { it.roundToInt().toString() },
) = SliderLayout(listOf(value), { onValueChange(it[0]) }, modifier, valueRange, step, tooltip, labels, minorTicks, enabled, valueLabel)

@Composable
fun RangeSlider(
    values: ClosedFloatingPointRange<Float>,
    onValuesChange: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..100f,
    step: Float = 1f,
    tooltip: Boolean = false,
    labels: Int = 0,
    minorTicks: Int = 0,
    enabled: Boolean = true,
    valueLabel: (Float) -> String = { it.roundToInt().toString() },
) = SliderLayout(listOf(values.start, values.endInclusive), { onValuesChange(it[0]..it[1]) }, modifier, valueRange, step, tooltip, labels, minorTicks, enabled, valueLabel)

@Composable
private fun SliderLayout(
    values: List<Float>,
    onChange: (List<Float>) -> Unit,
    modifier: Modifier,
    valueRange: ClosedFloatingPointRange<Float>,
    step: Float,
    tooltip: Boolean,
    labels: Int,
    minorTicks: Int,
    enabled: Boolean,
    valueLabel: (Float) -> String,
) {
    val theme = getTheme()
    val rtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val currentValues by rememberUpdatedState(values)
    val currentOnChange by rememberUpdatedState(onChange)
    val span = valueRange.endInclusive - valueRange.start

    BoxWithConstraints(modifier = modifier.fillMaxWidth().disabledAlpha(!enabled)) {
        val inset = with(LocalDensity.current) { 8.dp.toPx() }
        val width = constraints.maxWidth
        val trackWidth = width - 2 * inset

        fun xOf(value: Float): Float {
            val fraction = ((value - valueRange.start) / span).coerceIn(0f, 1f)
            return inset + trackWidth * if (rtl) 1 - fraction else fraction
        }

        fun valueAt(x: Float): Float {
            val fraction = ((x - inset) / trackWidth).coerceIn(0f, 1f).let { if (rtl) 1 - it else it }
            val raw = valueRange.start + fraction * span
            val snapped = if (step > 0f) valueRange.start + ((raw - valueRange.start) / step).roundToInt() * step else raw
            return snapped.coerceIn(valueRange)
        }

        fun update(index: Int, value: Float) {
            val updated = currentValues.toMutableList()
            updated[index] = when {
                updated.size < 2 -> value
                index == 0       -> value.coerceAtMost(updated[1])
                else             -> value.coerceAtLeast(updated[0])
            }
            currentOnChange(updated)
        }

        Column {
            if (tooltip) {
                Box(Modifier.fillMaxWidth().padding(bottom = 6.dp)) {
                    values.forEach { value ->
                        Text(
                            text = valueLabel(value),
                            color = theme.tooltipText,
                            style = PrelineTheme.typography.xs,
                            modifier = Modifier
                                .centeredAt(xOf(value))
                                .background(theme.tooltipBackground, RoundedCornerShape(6.dp))
                                .border(1.dp, theme.tooltipBorder, RoundedCornerShape(6.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .pointerInput(enabled, valueRange, step, rtl, width) {
                        if (!enabled) return@pointerInput
                        awaitEachGesture {
                            val down = awaitFirstDown()
                            val values = currentValues
                            val index = if (values.size == 2 && values[0] == values[1]) {
                                if ((down.position.x > xOf(values[0])) != rtl) 1 else 0
                            } else {
                                values.indices.minBy { abs(xOf(values[it]) - down.position.x) }
                            }
                            update(index, valueAt(down.position.x))
                            drag(down.id) { change ->
                                update(index, valueAt(change.position.x))
                                change.consume()
                            }
                        }
                    }
            ) {
                Canvas(Modifier.matchParentSize()) {
                    val y = size.height / 2
                    val stroke = 4.dp.toPx()
                    drawLine(theme.track, Offset(inset, y), Offset(size.width - inset, y), stroke, StrokeCap.Round)
                    val from = if (values.size == 1) xOf(valueRange.start) else xOf(values[0])
                    drawLine(theme.activeTrack, Offset(from, y), Offset(xOf(values.last()), y), stroke, StrokeCap.Round)
                }
                values.forEachIndexed { index, value ->
                    Box(
                        Modifier
                            .centeredAt(xOf(value))
                            .size(16.dp)
                            .background(theme.thumb, CircleShape)
                            .border(3.dp, theme.thumbBorder, CircleShape)
                            .semantics {
                                progressBarRangeInfo = ProgressBarRangeInfo(value, valueRange)
                                setProgress { target -> update(index, target.coerceIn(valueRange)); true }
                            }
                    )
                }
            }
            if (labels > 1 || minorTicks > 0) Pips(labels.coerceAtLeast(2), minorTicks, labels > 1, valueRange, ::xOf, valueLabel, theme)
        }
    }
}

@Composable
private fun Pips(
    labels: Int,
    minorTicks: Int,
    showLabels: Boolean,
    valueRange: ClosedFloatingPointRange<Float>,
    xOf: (Float) -> Float,
    valueLabel: (Float) -> String,
    theme: RangeSliderTheme,
) {
    val span = valueRange.endInclusive - valueRange.start
    val majors = (0 until labels).map { valueRange.start + span * it / (labels - 1) }

    Column(Modifier.fillMaxWidth().padding(top = 4.dp)) {
        Canvas(Modifier.fillMaxWidth().height(8.dp)) {
            val stroke = 1.dp.toPx()
            majors.forEachIndexed { index, major ->
                drawLine(theme.tick, Offset(xOf(major), 0f), Offset(xOf(major), size.height), stroke)
                if (index < majors.lastIndex) {
                    val gap = (majors[index + 1] - major) / (minorTicks + 1)
                    repeat(minorTicks) { i ->
                        val x = xOf(major + gap * (i + 1))
                        drawLine(theme.tick, Offset(x, 0f), Offset(x, size.height / 2), stroke)
                    }
                }
            }
        }
        if (showLabels) {
            Box(Modifier.fillMaxWidth().padding(top = 4.dp)) {
                majors.forEach { major ->
                    Text(text = valueLabel(major), color = theme.label, style = PrelineTheme.typography.xs, modifier = Modifier.centeredAt(xOf(major)))
                }
            }
        }
    }
}

private fun Modifier.centeredAt(x: Float) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints.copy(minWidth = 0))
    layout(constraints.maxWidth, placeable.height) {
        placeable.place((x - placeable.width / 2f).roundToInt().coerceIn(0, (constraints.maxWidth - placeable.width).coerceAtLeast(0)), 0)
    }
}
