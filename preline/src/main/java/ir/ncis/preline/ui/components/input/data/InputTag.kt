package ir.ncis.preline.ui.components.input.data

import androidx.compose.ui.graphics.painter.Painter

data class InputTag(
    val title: String,
    val avatar: Painter? = null,
    val onRemove: ((InputTag) -> Unit)? = null
)
