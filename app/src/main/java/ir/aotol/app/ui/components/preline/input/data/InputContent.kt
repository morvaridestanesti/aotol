package ir.aotol.app.ui.components.preline.input.data

import androidx.compose.ui.graphics.painter.Painter

data class InputContent(
    val iconStart: Painter? = null,
    val avatarUrl: String? = null,
    val avatarPainter: Painter? = null,
    val text: String? = null,
    val iconEnd: Painter? = null,
    val divider: Boolean = false,
    val onClick: (() -> Unit)? = null,
)
