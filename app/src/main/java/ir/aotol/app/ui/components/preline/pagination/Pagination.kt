package ir.aotol.app.ui.components.preline.pagination

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.aotol.app.R
import ir.aotol.app.helpers.invisibleClickable
import ir.aotol.app.ui.components.preline.Palette
import ir.aotol.app.ui.components.preline.PrelineTheme
import ir.aotol.app.ui.components.preline.medium

@Composable
fun Pagination(
    totalPageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    visiblePages: Int = -1,
    firstIcon: Painter? = null,
    firstLabel: String? = null,
    previousIcon: Painter? = null,
    previousLabel: String? = null,
    nextIcon: Painter? = null,
    nextLabel: String? = null,
    lastIcon: Painter? = null,
    lastLabel: String? = null,
    divider: Boolean = false,
    ofLabel: String = "of",
    fullWidth: Boolean = false,
    type: PaginationTheme.Type = PaginationTheme.Type.BaseNone,
    onPageChange: (Int) -> Unit
) {
    val theme = getTheme(type)

    Card(
        modifier = if (fullWidth) modifier.fillMaxWidth() else modifier,
        colors = CardDefaults.cardColors(theme.container.background),
        border = BorderStroke(1.dp, theme.container.border),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = theme.container.shape
    ) {
        PaginationRow(
            modifier = Modifier,
            firstIcon = firstIcon,
            currentPage = currentPage,
            onPageChange = onPageChange,
            firstLabel = firstLabel,
            previousIcon = previousIcon,
            previousLabel = previousLabel,
            totalPageCount = totalPageCount,
            visiblePages = visiblePages,
            nextLabel = nextLabel,
            nextIcon = nextIcon,
            lastLabel = lastLabel,
            lastIcon = lastIcon,
            divider = divider,
            ofLabel = ofLabel,
            fullWidth = fullWidth,
            type = type,
            theme = theme
        )
    }
}

@Composable
private fun PaginationRow(
    modifier: Modifier,
    firstIcon: Painter?,
    currentPage: Int,
    onPageChange: (Int) -> Unit,
    firstLabel: String?,
    previousIcon: Painter?,
    previousLabel: String?,
    totalPageCount: Int,
    visiblePages: Int,
    nextLabel: String?,
    nextIcon: Painter?,
    lastLabel: String?,
    lastIcon: Painter?,
    divider: Boolean,
    ofLabel: String,
    fullWidth: Boolean,
    type: PaginationTheme.Type,
    theme: PaginationTheme,
) {
    Row(
        modifier = modifier
            .height(38.dp)
            .then(if (fullWidth) Modifier.fillMaxWidth() else Modifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (listOf(firstIcon, firstLabel, previousIcon, previousLabel).any { it != null }) {
            Card(
                colors = CardDefaults.cardColors(theme.navGroup.background),
                elevation = CardDefaults.cardElevation(0.dp),
                border = BorderStroke(1.dp, theme.navGroup.border),
                shape = theme.navGroup.shape
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    firstIcon?.let {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f)
                                .invisibleClickable { if (currentPage != 1) onPageChange(1) }
                        ) {
                            Icon(
                                painter = it,
                                contentDescription = null,
                                tint = PrelineTheme.colors.foreground,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp)
                            )
                        }
                    }
                    firstLabel?.let {
                        Text(
                            text = it,
                            color = PrelineTheme.colors.foreground,
                            style = PrelineTheme.typography.base.medium,
                            maxLines = 1,
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .invisibleClickable { if (currentPage != 1) onPageChange(1) }
                        )
                    }
                    if (currentPage > 1) {
                        previousIcon?.let {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .aspectRatio(1f)
                                    .invisibleClickable { onPageChange(currentPage - 1) }
                            ) {
                                Icon(
                                    painter = it,
                                    contentDescription = null,
                                    tint = PrelineTheme.colors.foreground,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(10.dp)
                                )
                            }
                        }
                        previousLabel?.let {
                            Text(
                                text = it,
                                color = PrelineTheme.colors.foreground,
                                style = PrelineTheme.typography.base.medium,
                                maxLines = 1,
                                modifier = Modifier
                                    .padding(horizontal = 12.dp)
                                    .invisibleClickable { onPageChange(currentPage - 1) }
                            )
                        }
                    }
                }
            }
        }
        if (divider) VerticalDivider(color = PrelineTheme.colors.border)
        // A full-width pagination pushes the navigation to the edges and keeps the pages centered.
        if (fullWidth) Spacer(Modifier.weight(1f))
        if (type in arrayOf(PaginationTheme.Type.PagerPilled, PaginationTheme.Type.PagerRounded, PaginationTheme.Type.PagerBorderedPilled, PaginationTheme.Type.PagerBorderedRounded)) {
            Card(
                // Keeps the bordered current page clear of the navigation buttons next to it.
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxHeight()
                    .aspectRatio(1f),
                colors = CardDefaults.cardColors(theme.currentPage.background),
                border = BorderStroke(1.dp, theme.currentPage.border),
                elevation = CardDefaults.cardElevation(0.dp),
                shape = theme.currentPage.shape,
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { ShowPage(currentPage) }
            }
            if (divider) VerticalDivider(color = PrelineTheme.colors.border)
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = ofLabel,
                    color = PrelineTheme.colors.foreground,
                    style = PrelineTheme.typography.base.medium,
                )
            }
            if (divider) VerticalDivider(color = PrelineTheme.colors.border)
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = totalPageCount.toString(),
                    color = PrelineTheme.colors.foreground,
                    style = PrelineTheme.typography.base.medium,
                )
            }
            if (divider) VerticalDivider(color = PrelineTheme.colors.border)
        } else {
            for (i in 1..totalPageCount) {
                val showCard = i in arrayOf(1, totalPageCount) || visiblePages == -1 || i in (currentPage - visiblePages - 1)..(currentPage + visiblePages + 1)
                val showMore = visiblePages != -1 && i !in arrayOf(1, totalPageCount) && i in arrayOf(currentPage - visiblePages - 1, currentPage + visiblePages + 1)
                if (showCard) {
                    Card(
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                            .invisibleClickable { if (!showMore) onPageChange(i) },
                        colors = CardDefaults.cardColors(if (i == currentPage) theme.currentPage.background else Palette.Transparent),
                        border = BorderStroke(1.dp, if (i == currentPage) theme.currentPage.border else Palette.Transparent),
                        elevation = CardDefaults.cardElevation(0.dp),
                        shape = if (i == currentPage) theme.currentPage.shape else RectangleShape,
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            if (showMore) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_more_horizontal),
                                    contentDescription = null,
                                    tint = PrelineTheme.colors.mutedForeground,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(10.dp)
                                )
                            } else {
                                ShowPage(i)
                            }
                        }
                    }
                    if (divider) VerticalDivider(color = PrelineTheme.colors.border)
                }
            }
        }
        if (fullWidth) Spacer(Modifier.weight(1f))
        if (listOf(nextLabel, nextIcon, lastLabel, lastIcon).any { it != null }) {
            Card(
                colors = CardDefaults.cardColors(theme.navGroup.background),
                elevation = CardDefaults.cardElevation(0.dp),
                border = BorderStroke(1.dp, theme.navGroup.border),
                shape = theme.navGroup.shape
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (currentPage < totalPageCount) {
                        nextLabel?.let {
                            Text(
                                text = it,
                                color = PrelineTheme.colors.foreground,
                                style = PrelineTheme.typography.base.medium,
                                maxLines = 1,
                                modifier = Modifier
                                    .padding(horizontal = 12.dp)
                                    .invisibleClickable { onPageChange(currentPage + 1) }
                            )
                        }
                        nextIcon?.let {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .aspectRatio(1f)
                                    .invisibleClickable { onPageChange(currentPage + 1) }
                            ) {
                                Icon(
                                    painter = it,
                                    contentDescription = null,
                                    tint = PrelineTheme.colors.foreground,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(10.dp)
                                )
                            }
                        }
                    }
                    lastLabel?.let {
                        Text(
                            text = it,
                            color = PrelineTheme.colors.foreground,
                            style = PrelineTheme.typography.base.medium,
                            maxLines = 1,
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .invisibleClickable { if (currentPage != totalPageCount) onPageChange(totalPageCount) }
                        )
                    }
                    lastIcon?.let {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f)
                                .invisibleClickable { if (currentPage != totalPageCount) onPageChange(totalPageCount) }
                        ) {
                            Icon(
                                painter = it,
                                contentDescription = null,
                                tint = PrelineTheme.colors.foreground,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ShowPage(i: Int) {
    Text(
        text = i.toString(),
        color = PrelineTheme.colors.foreground,
        style = PrelineTheme.typography.base.medium,
    )
}

@Preview(showBackground = true, widthDp = 600, heightDp = 100)
@Composable
private fun Preview() {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            val currentPage = remember { mutableIntStateOf(2) }
            Pagination(
                firstIcon = painterResource(R.drawable.ic_chevron_back),
                firstLabel = "First",
                previousIcon = painterResource(R.drawable.ic_double_arrow_left),
                previousLabel = "Previous",
                nextIcon = painterResource(R.drawable.ic_double_arrow_right),
                nextLabel = "Next",
                lastIcon = painterResource(R.drawable.ic_chevron_forward),
                lastLabel = "Last",
                totalPageCount = 5,
                currentPage = currentPage.intValue,
                visiblePages = 1,
                divider = false,
                type = PaginationTheme.Type.PagerBorderedPilled
            ) { currentPage.intValue = it }
        }
    }
}