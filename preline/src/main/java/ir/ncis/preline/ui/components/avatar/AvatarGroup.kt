package ir.ncis.preline.ui.components.avatar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.components.avatar.data.AvatarGroupItem
import ir.ncis.preline.ui.theme.PrelineTheme

@Composable
fun AvatarGroup(
    items: List<AvatarGroupItem>,
    modifier: Modifier = Modifier,
    size: AvatarTheme.Size = AvatarTheme.Size.Default,
    ringColor: Color = PrelineTheme.colors.background,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy((-8).dp)) {
        items.forEach { item ->
            Avatar(
                modifier = Modifier
                    .background(ringColor, CircleShape)
                    .padding(2.dp),
                imagePainter = item.imagePainter,
                imageUrl = item.imageUrl,
                placeholder = item.placeholder,
                type = item.type,
                color = item.color,
                size = size,
                shape = AvatarTheme.Shape.Circular
            )
        }
    }
}
