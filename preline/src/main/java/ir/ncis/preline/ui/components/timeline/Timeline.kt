package ir.ncis.preline.ui.components.timeline

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.components.timeline.data.TimelineAuthor
import ir.ncis.preline.ui.components.timeline.data.TimelineItem
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium
import ir.ncis.preline.ui.theme.semiBold

@Composable
fun Timeline(
    items: List<TimelineItem>,
    modifier: Modifier = Modifier,
    timePosition: TimelineTheme.TimePosition = TimelineTheme.TimePosition.Above,
) {
    val theme = getTheme()
    val timeAtStart = timePosition == TimelineTheme.TimePosition.Start && items.any { it.time != null }

    Column(modifier = modifier) {
        items.forEachIndexed { index, item ->
            item.heading?.let {
                Text(
                    text = it.uppercase(),
                    color = theme.caption,
                    style = PrelineTheme.typography.xs.medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            ItemRow(item, index == items.lastIndex, timeAtStart, theme)
        }
    }
}

@Composable
private fun ItemRow(item: TimelineItem, isLast: Boolean, timeAtStart: Boolean, theme: TimelineTheme) {
    val largeMarker = item.marker !is TimelineTheme.Marker.Dot && item.marker !is TimelineTheme.Marker.Ring

    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(8.dp))
            .background(if (item.highlighted) theme.highlight else Palette.Transparent)
            .then(item.onClick?.let { Modifier.clickable(role = Role.Button, onClick = it) } ?: Modifier)
    ) {
        if (timeAtStart) {
            Text(
                text = item.time.orEmpty(),
                color = theme.caption,
                style = PrelineTheme.typography.xs,
                modifier = Modifier.width(64.dp).padding(top = if (largeMarker) 6.dp else 2.dp)
            )
        }
        Column(
            modifier = Modifier.width(28.dp).fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MarkerView(item.marker, theme)
            if (!isLast) {
                Box(
                    Modifier
                        .padding(vertical = 4.dp)
                        .weight(1f)
                        .width(1.dp)
                        .background(theme.line)
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp, end = 8.dp, top = if (largeMarker) 4.dp else 0.dp, bottom = if (isLast) 0.dp else 24.dp)
        ) {
            if (!timeAtStart) item.time?.let { Text(text = it, color = theme.caption, style = PrelineTheme.typography.xs) }
            item.title?.let { Text(text = it, color = theme.title, style = PrelineTheme.typography.sm.semiBold) }
            item.description?.let {
                Text(text = it, color = theme.description, style = PrelineTheme.typography.sm, modifier = Modifier.padding(top = 4.dp))
            }
            item.author?.let { AuthorView(it, theme) }
            item.content?.let { Box(Modifier.padding(top = 8.dp)) { it() } }
        }
    }
}

@Composable
private fun MarkerView(marker: TimelineTheme.Marker, theme: TimelineTheme) {
    val circle = Modifier
        .size(28.dp)
        .clip(CircleShape)
        .background(theme.markerBackground)

    when (marker) {
        TimelineTheme.Marker.Dot        -> Box(Modifier.height(20.dp), contentAlignment = Alignment.Center) {
            Box(Modifier.size(7.dp).background(theme.dot, CircleShape))
        }

        TimelineTheme.Marker.Ring       -> Box(Modifier.height(20.dp), contentAlignment = Alignment.Center) {
            Box(Modifier.size(7.dp).border(1.5.dp, theme.dot, CircleShape))
        }

        is TimelineTheme.Marker.Initial -> Box(circle.border(1.dp, theme.markerBorder, CircleShape), contentAlignment = Alignment.Center) {
            Text(text = marker.text, color = theme.markerForeground, style = PrelineTheme.typography.xs.semiBold)
        }

        is TimelineTheme.Marker.Number  -> Box(circle.border(1.dp, theme.numberBorder, CircleShape), contentAlignment = Alignment.Center) {
            Text(text = marker.value.toString(), color = theme.numberForeground, style = PrelineTheme.typography.xs.semiBold)
        }

        is TimelineTheme.Marker.Icon    -> Box(circle.border(1.dp, theme.markerBorder, CircleShape), contentAlignment = Alignment.Center) {
            Icon(painter = marker.painter, contentDescription = null, tint = theme.markerForeground, modifier = Modifier.size(14.dp))
        }

        is TimelineTheme.Marker.Image   -> Image(painter = marker.painter, contentDescription = null, contentScale = ContentScale.Crop, modifier = circle)
    }
}

@Composable
private fun AuthorView(author: TimelineAuthor, theme: TimelineTheme) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .then(author.onClick?.let { Modifier.clickable(role = Role.Button, onClick = it) } ?: Modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        author.avatar?.let {
            Image(painter = it, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.size(16.dp).clip(CircleShape))
        }
        Text(text = author.name, color = theme.title, style = PrelineTheme.typography.xs)
    }
}
