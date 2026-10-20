package ir.ncis.preline.ui.components.avatar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ir.ncis.preline.R
import ir.ncis.preline.ui.theme.PrelineTheme

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    imagePainter: Painter? = null,
    placeholder: String? = null,
    status: AvatarTheme.Status? = null,
    statusIcon: Painter? = null,
    type: AvatarTheme.Type = AvatarTheme.Type.Image,
    color: AvatarTheme.Color = AvatarTheme.Color.Image,
    size: AvatarTheme.Size = AvatarTheme.Size.XS,
    shape: AvatarTheme.Shape = AvatarTheme.Shape.Rounded,
    initialsSeparator: String = ZeroWidthNonJoiner,
) {
    val theme = getTheme(type, color, size, shape)
    Box(modifier = modifier.size(theme.height)) {
        Card(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(theme.background),
            border = BorderStroke(1.dp, theme.stroke),
            elevation = CardDefaults.cardElevation(0.dp),
            shape = theme.shape
        ) { Content(imagePainter, imageUrl, placeholder, color == AvatarTheme.Color.Placeholder, initialsSeparator, theme) }

        val statusBoxSize = theme.statusSize + 4.dp
        val statusModifier = Modifier
            .align(Alignment.TopEnd)
            .then(if (shape == AvatarTheme.Shape.Rounded) Modifier.offset(x = statusBoxSize / 2, y = -statusBoxSize / 2) else Modifier)
            .size(statusBoxSize)
            .background(PrelineTheme.colors.layer, CircleShape)
            .padding(2.dp)
        status?.let {
            Box(modifier = statusModifier.background(getStatusColor(it), CircleShape))
        }
        statusIcon?.let {
            Icon(
                painter = it,
                contentDescription = null,
                modifier = statusModifier,
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
    showPersonIcon: Boolean,
    initialsSeparator: String,
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
                    text = getInitials(placeholder, initialsSeparator),
                    color = theme.foreground,
                    textAlign = TextAlign.Center,
                    style = theme.textStyle,
                )
            }

            showPersonIcon       -> {
                Icon(
                    painter = painterResource(R.drawable.ic_user),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    tint = theme.foreground
                )
            }
        }
    }
}

private const val ZeroWidthNonJoiner = "\u200C"

private fun getInitials(name: String, separator: String): String {
    return name
        .trim()
        .split(Regex("\\s+"))
        .filter { it.isNotEmpty() }
        .take(2)
        .map { it.first().uppercaseChar() }
        .joinToString(separator)
}
