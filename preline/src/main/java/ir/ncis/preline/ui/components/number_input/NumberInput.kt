package ir.ncis.preline.ui.components.number_input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.helpers.disabledAlpha
import ir.ncis.preline.helpers.toLatinDigit
import ir.ncis.preline.ui.theme.PrelineTheme

@Composable
fun NumberInput(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    type: NumberInputTheme.Type = NumberInputTheme.Type.Gray,
    label: String? = null,
    min: Int = 0,
    max: Int = Int.MAX_VALUE,
    step: Int = 1,
    invalid: Boolean = false,
    feedbackMessage: String? = null,
) {
    val theme = getTheme(type, invalid)
    val decrease = Step(R.drawable.ic_minus, stringResource(R.string.preline_decrease), value > min) { onValueChange((value - step).coerceIn(min, max)) }
    val increase = Step(R.drawable.ic_plus, stringResource(R.string.preline_increase), value < max) { onValueChange((value + step).coerceIn(min, max)) }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .clip(theme.shape)
                .background(theme.background)
                .border(1.dp, theme.stroke, theme.shape),
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (type) {
                NumberInputTheme.Type.Mini, NumberInputTheme.Type.Plain -> Row(
                    modifier = Modifier.padding(if (type == NumberInputTheme.Type.Mini) 8.dp else 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    StepButton(decrease, theme)
                    ValueField(value, onValueChange, min, max, theme, Modifier.width(40.dp), TextAlign.Center)
                    StepButton(increase, theme)
                }

                NumberInputTheme.Type.HorizontalButtons -> {
                    ValueColumn(value, onValueChange, min, max, label, theme)
                    ErrorIcon(invalid, theme)
                    VerticalDivider(thickness = 1.dp, color = theme.buttonStroke)
                    SegmentButton(decrease, theme, Modifier.fillMaxHeight().width(48.dp))
                    VerticalDivider(thickness = 1.dp, color = theme.buttonStroke)
                    SegmentButton(increase, theme, Modifier.fillMaxHeight().width(48.dp))
                }

                NumberInputTheme.Type.VerticalButtons -> {
                    ValueColumn(value, onValueChange, min, max, label, theme)
                    ErrorIcon(invalid, theme)
                    VerticalDivider(thickness = 1.dp, color = theme.buttonStroke)
                    Column(Modifier.width(40.dp).fillMaxHeight()) {
                        SegmentButton(decrease, theme, Modifier.fillMaxWidth().weight(1f))
                        HorizontalDivider(thickness = 1.dp, color = theme.buttonStroke)
                        SegmentButton(increase, theme, Modifier.fillMaxWidth().weight(1f))
                    }
                }

                else -> {
                    ValueColumn(value, onValueChange, min, max, label, theme)
                    ErrorIcon(invalid, theme)
                    Row(Modifier.padding(horizontal = 12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        StepButton(decrease, theme)
                        StepButton(increase, theme)
                    }
                }
            }
        }
        feedbackMessage?.let {
            Text(text = it, color = theme.feedback, style = PrelineTheme.typography.xs, modifier = Modifier.padding(top = 6.dp))
        }
    }
}

private class Step(val icon: Int, val description: String, val enabled: Boolean, val onClick: () -> Unit)

@Composable
private fun RowScope.ValueColumn(value: Int, onValueChange: (Int) -> Unit, min: Int, max: Int, label: String?, theme: NumberInputTheme) {
    Column(
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = 12.dp, vertical = if (label == null) 12.dp else 8.dp)
    ) {
        label?.let { Text(text = it, color = theme.label, style = PrelineTheme.typography.sm) }
        ValueField(value, onValueChange, min, max, theme, Modifier.fillMaxWidth(), TextAlign.Start)
    }
}

@Composable
private fun ValueField(value: Int, onValueChange: (Int) -> Unit, min: Int, max: Int, theme: NumberInputTheme, modifier: Modifier, textAlign: TextAlign) {
    var text by remember(value) { mutableStateOf(value.toString()) }

    BasicTextField(
        value = text,
        onValueChange = { input ->
            val digits = input.map { it.toLatinDigit() }
                .filterIndexed { index, c -> c.isDigit() || (c == '-' && index == 0 && min < 0) }
                .joinToString("")
            text = digits
            digits.toIntOrNull()?.let { onValueChange(it.coerceIn(min, max)) }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = PrelineTheme.typography.sm.copy(color = theme.text, textAlign = textAlign),
        cursorBrush = SolidColor(PrelineTheme.colors.primary),
        modifier = modifier
    )
}

@Composable
private fun ErrorIcon(invalid: Boolean, theme: NumberInputTheme) {
    if (invalid) Icon(painter = painterResource(R.drawable.ic_info), contentDescription = null, tint = theme.feedback, modifier = Modifier.size(16.dp))
}

@Composable
private fun StepButton(step: Step, theme: NumberInputTheme) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .disabledAlpha(!step.enabled)
            .clip(theme.buttonShape)
            .background(theme.buttonBackground)
            .border(1.dp, theme.buttonStroke, theme.buttonShape)
            .clickable(enabled = step.enabled, role = Role.Button, onClick = step.onClick)
            .semantics { contentDescription = step.description },
        contentAlignment = Alignment.Center
    ) {
        Icon(painter = painterResource(step.icon), contentDescription = null, tint = theme.text, modifier = Modifier.size(12.dp))
    }
}

@Composable
private fun SegmentButton(step: Step, theme: NumberInputTheme, modifier: Modifier) {
    Box(
        modifier = modifier
            .disabledAlpha(!step.enabled)
            .clickable(enabled = step.enabled, role = Role.Button, onClick = step.onClick)
            .semantics { contentDescription = step.description },
        contentAlignment = Alignment.Center
    ) {
        Icon(painter = painterResource(step.icon), contentDescription = null, tint = theme.text, modifier = Modifier.size(14.dp))
    }
}
