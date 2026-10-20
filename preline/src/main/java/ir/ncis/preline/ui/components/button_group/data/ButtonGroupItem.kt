package ir.ncis.preline.ui.components.button_group.data

import androidx.compose.ui.graphics.painter.Painter
import ir.ncis.preline.ui.components.button_group.ButtonGroupTheme

data class ButtonGroupItem(
    val placeholder: String? = null,
    val leadingIcon: Painter? = null,
    val trailingIcon: Painter? = null,
    val contentDescription: String? = null,
    val state: ButtonGroupTheme.State = ButtonGroupTheme.State.Default,
    val onClick: () -> Unit,
)
