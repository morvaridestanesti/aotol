package ir.ncis.preline.ui.components.pin_input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ir.ncis.preline.helpers.toLatinDigit
import ir.ncis.preline.ui.theme.PrelineTheme

@Composable
fun PinInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    length: Int = 4,
    shape: PinInputTheme.Shape = PinInputTheme.Shape.Rounded,
    size: PinInputTheme.Size = PinInputTheme.Size.Default,
    aspectRatio: Float = 1f,
    placeholder: String? = null,
    mask: Boolean = false,
    allowed: Regex = Regex("[0-9]"),
    keyboardType: KeyboardType = KeyboardType.NumberPassword,
    invalid: Boolean = false,
    onComplete: ((String) -> Unit)? = null,
) {
    val theme = getTheme(shape, size)
    val interactionSource = remember { MutableInteractionSource() }
    val focused by interactionSource.collectIsFocusedAsState()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        BasicTextField(
            value = value,
            onValueChange = { input ->
                val pin = input.map { it.toLatinDigit() }.filter { allowed.matches(it.toString()) }.joinToString("").take(length)
                if (pin != value) {
                    onValueChange(pin)
                    if (pin.length == length) onComplete?.invoke(pin)
                }
            },
            modifier = modifier,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            interactionSource = interactionSource,
            decorationBox = { innerTextField ->
                Row(horizontalArrangement = Arrangement.spacedBy(theme.spacing)) {
                    repeat(length) { index ->
                        val stroke = when {
                            invalid                                                  -> theme.invalidStroke
                            focused && index == value.length.coerceAtMost(length - 1) -> theme.focusedStroke
                            else                                                     -> theme.stroke
                        }
                        Cell(value.getOrNull(index)?.let { if (mask) '•' else it }, placeholder, stroke, shape, aspectRatio, theme)
                    }
                }
                Box(Modifier.size(0.dp)) { innerTextField() }
            }
        )
    }
}

@Composable
private fun Cell(char: Char?, placeholder: String?, stroke: Color, shape: PinInputTheme.Shape, aspectRatio: Float, theme: PinInputTheme) {
    val border = if (shape == PinInputTheme.Shape.Underline) {
        Modifier.drawBehind { drawLine(stroke, Offset(0f, size.height), Offset(size.width, size.height), 2.dp.toPx()) }
    } else {
        Modifier.border(1.dp, stroke, theme.shape)
    }

    Box(
        modifier = Modifier
            .height(theme.height)
            .width(theme.height * aspectRatio)
            .clip(theme.shape)
            .background(theme.background)
            .then(border),
        contentAlignment = Alignment.Center
    ) {
        when {
            char != null        -> Text(text = char.toString(), color = theme.text, style = theme.textStyle)
            placeholder != null -> Text(text = placeholder, color = theme.placeholder, style = PrelineTheme.typography.xs)
        }
    }
}
