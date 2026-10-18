package ir.aotol.app.ui.components.preline.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ir.aotol.app.R
import ir.aotol.app.ui.components.preline.button.Button
import ir.aotol.app.ui.components.preline.button.ButtonTheme
import ir.aotol.app.ui.components.preline.card.data.CardButton

@Composable
fun Card(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    imagePainter: Painter? = null,
    title: String? = null,
    headerTitle: String? = null,
    footerTitle: String? = null,
    subtitle: String? = null,
    content: String? = null,
    button1: CardButton? = null,
    button2: CardButton? = null,
) {
    val theme = getTheme()

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(theme.cardBackground),
        border = BorderStroke(1.dp, theme.cardBorder),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = theme.shape
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            headerTitle?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = theme.padding, vertical = 20.dp),
                    style = theme.headerTitleStyle,
                    color = theme.headerTitleColor
                )
            }
            if (imageUrl != null || imagePainter != null) {
                if (imagePainter != null) {
                    Image(
                        painter = imagePainter,
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(theme.padding)
        ) {
            title?.let { titleText ->
                Text(
                    text = titleText,
                    color = theme.titleColor,
                    style = theme.titleStyle,
                )
                subtitle?.let { subtitleText ->
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = subtitleText,
                        color = theme.subtitleColor,
                        style = theme.subtitleStyle,
                    )
                }
            }
            content?.let { contentText ->
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = contentText,
                    color = theme.contentColor,
                    style = theme.contentStyle,
                )
            }
            if (button1 != null || button2 != null) {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    button1?.let {
                        CardButtonView(
                            button = it,
                            style = CardTheme.Style.Default,
                        )
                    }
                    button2?.let {
                        CardButtonView(
                            button = it,
                            style = CardTheme.Style.Simple,
                        )
                    }
                }
            }
        }
        footerTitle?.let { footerText ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(theme.footerBackground)
            ) {
                Text(
                    text = footerText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = theme.padding, vertical = 20.dp),
                    style = theme.footerStyle,
                    color = theme.footerTextColor
                )
            }
        }
    }
}

@Composable
private fun CardButtonView(
    button: CardButton,
    style: CardTheme.Style,
) {
    when (style) {
        CardTheme.Style.Default -> {
            Button(
                placeholder = button.title,
                style = ButtonTheme.Style.Solid,
                color = ButtonTheme.Color.Blue
            ) { button.onClick() }
        }

        CardTheme.Style.Simple  -> {
            Button(
                placeholder = button.title,
                style = ButtonTheme.Style.Link,
                color = ButtonTheme.Color.Blue,
                trailingIcon = painterResource(R.drawable.ic_chevron_forward)
            ) { button.onClick() }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Card(
        headerTitle = "Card header title",
        imagePainter = painterResource(R.drawable.ic_launcher_background),
        title = "Card title",
        subtitle = "CARD SUBTITLE",
        content = "Some quick example text to build on the card title and make up the bulk of the card's content.",
        footerTitle = "Last updated 5 mins ago",
        button1 = CardButton(
            title = "Go somewhere",
            onClick = {}
        ),
        button2 = CardButton(
            title = "Card link",
            onClick = {}
        )
    )
}
