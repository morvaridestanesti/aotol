package ir.aotol.app.ui.components.preline.avatar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ir.aotol.app.R
import ir.aotol.app.ui.components.preline.PrelineTheme

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    imagePainter: Painter? = null,
    placeholder: String? = null,
    statusIcon: Painter? = null,
    type: AvatarTheme.Type = AvatarTheme.Type.Image,
    color: AvatarTheme.Color = AvatarTheme.Color.Image,
    size: AvatarTheme.Size = AvatarTheme.Size.XS,
    shape: AvatarTheme.Shape = AvatarTheme.Shape.Rounded,
) {
    val theme = getTheme(type, color, size, shape)
    Box(modifier = Modifier.size(theme.height)) {
        val padding = if (shape == AvatarTheme.Shape.Rounded) theme.statusSize / 2 else 0.dp
        Card(
            modifier = modifier
                .size(theme.height)
                .padding(top = padding, end = padding),
            colors = CardDefaults.cardColors(theme.background),
            border = BorderStroke(1.dp, theme.stroke),
            elevation = CardDefaults.cardElevation(0.dp),
            shape = theme.shape
        ) { Content(imagePainter, imageUrl, placeholder, theme) }
        if (statusIcon != null) {
            Icon(
                painter = statusIcon,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(theme.statusSize),
                tint = PrelineTheme.colors.primary
            )
        }
    }
}

@Composable
private fun Content(
    imagePainter: Painter?,
    imageUrl: String?,
    placeholder: String?,
    theme: AvatarTheme,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            imagePainter != null -> {
                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            imageUrl != null     -> {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            placeholder != null  -> {
                Text(
                    text = getInitials(placeholder),
                    color = theme.foreground,
                    textAlign = TextAlign.Center,
                    style = theme.textStyle,
                )
            }
        }
    }
}

private fun getInitials(name: String): String {
    return name
        .trim()
        .split(Regex("\\s+"))
        .filter { it.isNotEmpty() }
        .take(2)
        .map { it.first().uppercaseChar() }
        .joinToString("")
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun AvatarPreview() {
    Avatar(
        placeholder = "Preline",
        size = AvatarTheme.Size.Default,
        modifier = Modifier,
        imageUrl = null,
        imagePainter = null,
        statusIcon = painterResource(R.drawable.ic_status),
        type = AvatarTheme.Type.Outline,
        color = AvatarTheme.Color.Green,
        shape = AvatarTheme.Shape.Circular,
    )
}
