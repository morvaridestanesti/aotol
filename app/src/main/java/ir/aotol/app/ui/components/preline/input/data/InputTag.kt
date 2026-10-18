package ir.aotol.app.ui.components.preline.input.data

import androidx.compose.ui.graphics.painter.Painter

data class InputTag(
    val title: String,
    val avatar: Painter? = null,
    val onRemove: ((InputTag) -> Unit)? = null
)