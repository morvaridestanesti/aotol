package ir.aotol.app.ui.components.preline.styled_icon

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.aotol.app.R

@Composable
fun StyledIcon(
    icon: Painter,
    modifier: Modifier = Modifier,
    type: StyledIconTheme.Type = StyledIconTheme.Type.Solid,
    color: StyledIconTheme.Color = StyledIconTheme.Color.Dark,
    size: StyledIconTheme.Size = StyledIconTheme.Size.Default,
    shape: StyledIconTheme.Shape = StyledIconTheme.Shape.Rounded,
    onClick: (() -> Unit)? = null
) {
    val theme = getTheme(type, color, size, shape)

    Card(
        modifier = modifier
            .size(theme.height)
            .then(onClick?.let { Modifier.clickable { it() } } ?: Modifier),
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
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(theme.contentPadding),
                    tint = theme.foregroundColor
                )
            }
        }
    }
}

@Preview
@Composable
private fun StyledIconPreview() {
    StyledIcon(
        icon = painterResource(R.drawable.ic_check),
        modifier = Modifier,
        type = StyledIconTheme.Type.SoftOutlined,
        color = StyledIconTheme.Color.Green,
        size = StyledIconTheme.Size.Default,
        shape = StyledIconTheme.Shape.Circular
    ) {}
}
