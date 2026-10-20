package ir.ncis.preline.ui.components.timeline.data

import androidx.compose.runtime.Composable
import ir.ncis.preline.ui.components.timeline.TimelineTheme

data class TimelineItem(
    val title: String? = null,
    val description: String? = null,
    val heading: String? = null,
    val time: String? = null,
    val marker: TimelineTheme.Marker = TimelineTheme.Marker.Dot,
    val author: TimelineAuthor? = null,
    val highlighted: Boolean = false,
    val content: (@Composable () -> Unit)? = null,
    val onClick: (() -> Unit)? = null,
)
