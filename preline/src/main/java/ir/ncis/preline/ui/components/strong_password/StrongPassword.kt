package ir.ncis.preline.ui.components.strong_password

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.ui.components.card.Card
import ir.ncis.preline.ui.components.input.Input
import ir.ncis.preline.ui.components.strong_password.data.PasswordRule
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.semiBold

@Composable
fun StrongPassword(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    rules: List<PasswordRule> = defaultPasswordRules(),
    levels: List<String> = defaultPasswordLevels(),
    hints: StrongPasswordTheme.Hints = StrongPasswordTheme.Hints.None,
) {
    val theme = getTheme()
    val met = rules.count { it.isMet(value) }
    var focused by remember { mutableStateOf(false) }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Input(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focused = it.hasFocus }
        )
        when (hints) {
            StrongPasswordTheme.Hints.None    -> StrengthBar(value.isEmpty(), met, rules.size, theme)
            StrongPasswordTheme.Hints.Inline  -> {
                StrengthBar(value.isEmpty(), met, rules.size, theme)
                Hints(value, met, rules, levels, theme)
            }

            StrongPasswordTheme.Hints.OnFocus -> AnimatedVisibility(visible = focused) {
                Card {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        StrengthBar(value.isEmpty(), met, rules.size, theme)
                        Hints(value, met, rules, levels, theme)
                    }
                }
            }
        }
    }
}

@Composable
fun defaultPasswordRules(minLength: Int = 6): List<PasswordRule> = listOf(
    PasswordRule(stringResource(R.string.preline_password_min_length, minLength)) { it.length >= minLength },
    PasswordRule(stringResource(R.string.preline_password_lowercase)) { password -> password.any { it.isLowerCase() } },
    PasswordRule(stringResource(R.string.preline_password_uppercase)) { password -> password.any { it.isUpperCase() } },
    PasswordRule(stringResource(R.string.preline_password_numbers)) { password -> password.any { it.isDigit() } },
    PasswordRule(stringResource(R.string.preline_password_special)) { password -> password.any { !it.isLetterOrDigit() && !it.isWhitespace() } },
)

@Composable
fun defaultPasswordLevels(): List<String> = listOf(
    stringResource(R.string.preline_password_empty),
    stringResource(R.string.preline_password_weak),
    stringResource(R.string.preline_password_medium),
    stringResource(R.string.preline_password_strong),
    stringResource(R.string.preline_password_very_strong),
    stringResource(R.string.preline_password_super_strong),
)

@Composable
private fun StrengthBar(empty: Boolean, met: Int, total: Int, theme: StrongPasswordTheme) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        repeat(total) { index ->
            val color = when {
                empty        -> theme.emptySegment
                met == total -> theme.completeSegment
                index < met  -> theme.filledSegment
                else         -> theme.restSegment
            }
            Box(
                Modifier
                    .weight(1f)
                    .height(8.dp)
                    .background(color, CircleShape)
            )
        }
    }
}

@Composable
private fun Hints(value: String, met: Int, rules: List<PasswordRule>, levels: List<String>, theme: StrongPasswordTheme) {
    val level = if (value.isEmpty()) levels.firstOrNull() else levels.getOrNull(met.coerceAtLeast(1).coerceAtMost(levels.lastIndex))

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        level?.let {
            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.preline_password_level) + " ")
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) { append(it) }
                },
                color = theme.text,
                style = PrelineTheme.typography.sm
            )
        }
        Text(text = stringResource(R.string.preline_password_must_contain), color = theme.text, style = PrelineTheme.typography.sm.semiBold)
        rules.forEach { rule ->
            val ok = rule.isMet(value)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(
                    painter = painterResource(if (ok) R.drawable.ic_check else R.drawable.ic_clear),
                    contentDescription = null,
                    tint = if (ok) theme.met else theme.unmet,
                    modifier = Modifier.size(14.dp)
                )
                Text(text = rule.label, color = if (ok) theme.met else theme.unmet, style = PrelineTheme.typography.sm)
            }
        }
    }
}
