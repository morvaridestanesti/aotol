package ir.ncis.preline.ui.components.timeline.data

import androidx.compose.ui.graphics.painter.Painter

data class TimelineAuthor(
    val name: String,
    val avatar: Painter? = null,
    val onClick: (() -> Unit)? = null,
)
