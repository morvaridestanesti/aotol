package ir.ncis.preline.ui.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ir.ncis.preline.R
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium
import ir.ncis.preline.ui.theme.semiBold

@Composable
fun CardTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = PrelineTheme.colors.foreground,
        style = PrelineTheme.typography.base.semiBold,
    )
}

@Composable
fun CardSubtitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text.uppercase(),
        modifier = modifier.padding(top = 4.dp),
        color = PrelineTheme.colors.mutedForeground1,
        style = PrelineTheme.typography.xs.medium,
    )
}

@Composable
fun CardText(text: String, modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Unspecified) {
    Text(
        text = text,
        modifier = modifier.padding(top = 8.dp),
        textAlign = textAlign,
        color = PrelineTheme.colors.mutedForeground1,
        style = PrelineTheme.typography.sm,
    )
}

@Composable
fun CardCaption(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = PrelineTheme.colors.mutedForeground1,
        style = PrelineTheme.typography.sm,
    )
}

@Composable
fun CardLink(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .padding(top = 12.dp)
            .clickable(role = Role.Button, onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = text, color = PrelineTheme.colors.primary, style = PrelineTheme.typography.sm.semiBold)
        Icon(
            painter = painterResource(R.drawable.ic_chevron_forward),
            contentDescription = null,
            modifier = Modifier.size(14.dp),
            tint = PrelineTheme.colors.primary
        )
    }
}

@Composable
fun CardImage(painter: Painter, modifier: Modifier = Modifier, contentDescription: String? = null) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun CardImage(url: String, modifier: Modifier = Modifier, contentDescription: String? = null) {
    AsyncImage(
        model = url,
        contentDescription = contentDescription,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        contentScale = ContentScale.Crop
    )
}
