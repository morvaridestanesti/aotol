package ir.ncis.preline.ui.components.pagination

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
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.helpers.invisibleClickable
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.medium

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
                                .invisibleClickable(role = Role.Button) { if (currentPage != 1) onPageChange(1) }
                        ) {
                            Icon(
                                painter = it,
                                contentDescription = stringResource(R.string.preline_first_page),
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
                                .invisibleClickable(role = Role.Button) { if (currentPage != 1) onPageChange(1) }
                        )
                    }
                    if (currentPage > 1) {
                        previousIcon?.let {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .aspectRatio(1f)
                                    .invisibleClickable(role = Role.Button) { onPageChange(currentPage - 1) }
                            ) {
                                Icon(
                                    painter = it,
                                    contentDescription = stringResource(R.string.preline_previous_page),
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
                                    .invisibleClickable(role = Role.Button) { onPageChange(currentPage - 1) }
                            )
                        }
                    }
                }
            }
        }
        if (divider) VerticalDivider(color = PrelineTheme.colors.border)
        if (fullWidth) Spacer(Modifier.weight(1f))
        if (type in arrayOf(PaginationTheme.Type.PagerPilled, PaginationTheme.Type.PagerRounded, PaginationTheme.Type.PagerBorderedPilled, PaginationTheme.Type.PagerBorderedRounded)) {
            Card(
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
                            .invisibleClickable(role = Role.Button) { if (!showMore) onPageChange(i) }
                            .semantics { selected = i == currentPage },
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
                                    .invisibleClickable(role = Role.Button) { onPageChange(currentPage + 1) }
                            )
                        }
                        nextIcon?.let {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .aspectRatio(1f)
                                    .invisibleClickable(role = Role.Button) { onPageChange(currentPage + 1) }
                            ) {
                                Icon(
                                    painter = it,
                                    contentDescription = stringResource(R.string.preline_next_page),
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
                                .invisibleClickable(role = Role.Button) { if (currentPage != totalPageCount) onPageChange(totalPageCount) }
                        )
                    }
                    lastIcon?.let {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f)
                                .invisibleClickable(role = Role.Button) { if (currentPage != totalPageCount) onPageChange(totalPageCount) }
                        ) {
                            Icon(
                                painter = it,
                                contentDescription = stringResource(R.string.preline_last_page),
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
