package ir.ncis.preline.ui.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ir.ncis.preline.ui.components.button.Button

@Composable
fun Card(
    modifier: Modifier = Modifier,
    style: CardTheme.Style = CardTheme.Style.Bordered,
    accentBorder: Boolean = false,
    media: (@Composable () -> Unit)? = null,
    mediaPosition: CardTheme.MediaPosition = CardTheme.MediaPosition.Top,
    header: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val theme = getTheme(style)
    CardContainer(modifier = modifier.then(onClick?.let { Modifier.clickable(role = Role.Button) { it() } } ?: Modifier), theme = theme) {
        if (accentBorder) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(theme.accentBorderWidth)
                    .background(theme.accent)
            )
        }
        when (mediaPosition) {
            CardTheme.MediaPosition.Start      -> {
                Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                    media?.let {
                        Box(
                            modifier = Modifier
                                .weight(0.4f)
                                .fillMaxHeight()
                        ) { it() }
                    }
                    Column(modifier = Modifier.weight(0.6f)) { CardSections(theme, header, footer, content) }
                }
            }

            CardTheme.MediaPosition.Background -> {
                Box {
                    media?.let { Box(modifier = Modifier.matchParentSize()) { it() } }
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(Brush.verticalGradient(listOf(theme.background.copy(alpha = 0.9f), theme.background.copy(alpha = 0.45f))))
                    )
                    Column { CardSections(theme, header, footer, content) }
                }
            }

            CardTheme.MediaPosition.Top        -> CardSections(theme, header, footer, content, mediaTop = media)
            CardTheme.MediaPosition.Bottom     -> CardSections(theme, header, footer, content, mediaBottom = media)
        }
    }
}

@Composable
internal fun CardContainer(modifier: Modifier, theme: CardTheme, content: @Composable ColumnScope.() -> Unit) {
    androidx.compose.material3.Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(theme.background),
        border = BorderStroke(1.dp, theme.border),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = theme.shape,
        content = content
    )
}

@Composable
private fun CardSections(
    theme: CardTheme,
    header: (@Composable () -> Unit)?,
    footer: (@Composable () -> Unit)?,
    content: @Composable ColumnScope.() -> Unit,
    mediaTop: (@Composable () -> Unit)? = null,
    mediaBottom: (@Composable () -> Unit)? = null,
) {
    header?.let { CardHeader(theme, it) }
    mediaTop?.invoke()
    CardBody(theme, content)
    mediaBottom?.invoke()
    footer?.let { CardFooter(theme, it) }
}

@Composable
internal fun CardHeader(theme: CardTheme, content: @Composable () -> Unit) {
    CardSection(theme, content)
    HorizontalDivider(thickness = 1.dp, color = theme.divider)
}

@Composable
internal fun CardBody(theme: CardTheme, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(theme.padding),
        content = content
    )
}

@Composable
internal fun CardFooter(theme: CardTheme, content: @Composable () -> Unit) {
    HorizontalDivider(thickness = 1.dp, color = theme.divider)
    CardSection(theme, content)
}

@Composable
private fun CardSection(theme: CardTheme, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(theme.sectionBackground)
            .padding(horizontal = theme.padding, vertical = theme.sectionVerticalPadding)
    ) { content() }
}
