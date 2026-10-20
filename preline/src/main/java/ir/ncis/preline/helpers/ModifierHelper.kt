package ir.ncis.preline.helpers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.semantics.Role

const val DisabledAlpha = 0.5f

fun Modifier.disabledAlpha(disabled: Boolean): Modifier = if (disabled) alpha(DisabledAlpha) else this

@Composable
fun Modifier.invisibleClickable(focusable: Boolean = true, role: Role? = null, onClick: () -> Unit): Modifier = focusProperties { canFocus = focusable }.clickable(
    interactionSource = remember { MutableInteractionSource() },
    indication = null,
    role = role
) { onClick() }
