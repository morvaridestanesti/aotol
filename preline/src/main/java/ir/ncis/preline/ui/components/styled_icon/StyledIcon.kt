package ir.ncis.preline.ui.components.styled_icon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun StyledIcon(
    icon: Painter,
    modifier: Modifier = Modifier,
    type: StyledIconTheme.Type = StyledIconTheme.Type.Solid,
    color: StyledIconTheme.Color = StyledIconTheme.Color.Dark,
    size: StyledIconTheme.Size = StyledIconTheme.Size.Default,
    shape: StyledIconTheme.Shape = StyledIconTheme.Shape.Rounded,
    contentDescription: String? = null,
    onClick: (() -> Unit)? = null
) {
    val theme = getTheme(type, color, size, shape)

    Card(
        modifier = modifier
            .size(theme.height)
            .then(onClick?.let { Modifier.clickable(role = Role.Button) { it() } } ?: Modifier),
        colors = CardDefaults.cardColors(theme.containerColor),
        border = BorderStroke(theme.strokeWidth, theme.strokeColor),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = theme.shape
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(theme.containerPadding),
            colors = CardDefaults.cardColors(theme.contentColor),
            shape = theme.shape
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = icon,
                    contentDescription = contentDescription,
                    modifier = Modifier.size(theme.iconSize),
                    tint = theme.foregroundColor
                )
            }
        }
    }
}
