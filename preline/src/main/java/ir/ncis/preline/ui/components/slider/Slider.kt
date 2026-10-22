package ir.ncis.preline.ui.components.slider

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.helpers.disabledAlpha
import ir.ncis.preline.ui.theme.PrelineTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Slider(
    count: Int,
    modifier: Modifier = Modifier,
    slidesPerView: Int = 1,
    spacing: Dp = 0.dp,
    state: PagerState = rememberPagerState { count },
    arrows: SliderTheme.Arrows = SliderTheme.Arrows.Both,
    pagination: SliderTheme.Indicator = SliderTheme.Indicator.None,
    fraction: SliderTheme.Indicator = SliderTheme.Indicator.None,
    autoPlayMillis: Long? = null,
    content: @Composable (index: Int) -> Unit,
) {
    val theme = getTheme()
    val scope = rememberCoroutineScope()
    val stops = (count - slidesPerView + 1).coerceAtLeast(1)
    val current = state.currentPage.coerceAtMost(stops - 1)

    fun goTo(page: Int) {
        scope.launch { state.animateScrollToPage(page.coerceIn(0, stops - 1)) }
    }

    autoPlayMillis?.let { millis ->
        LaunchedEffect(state, millis, stops) {
            while (true) {
                delay(millis)
                state.animateScrollToPage(if (state.currentPage >= stops - 1) 0 else state.currentPage + 1)
            }
        }
    }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Box {
            HorizontalPager(
                state = state,
                pageSize = SlidesPerView(slidesPerView, spacing),
                pageSpacing = spacing,
                modifier = Modifier.fillMaxWidth()
            ) { content(it) }
            if (arrows == SliderTheme.Arrows.Both || arrows == SliderTheme.Arrows.Previous) {
                Arrow(previous = true, enabled = current > 0, theme = theme, modifier = Modifier.align(Alignment.CenterStart)) { goTo(current - 1) }
            }
            if (arrows == SliderTheme.Arrows.Both || arrows == SliderTheme.Arrows.Next) {
                Arrow(previous = false, enabled = current < stops - 1, theme = theme, modifier = Modifier.align(Alignment.CenterEnd)) { goTo(current + 1) }
            }
            when (pagination) {
                SliderTheme.Indicator.Inside         -> Dots(stops, current, true, false, theme, Modifier.align(Alignment.BottomCenter).padding(12.dp), ::goTo)
                SliderTheme.Indicator.InsideVertical -> Dots(stops, current, true, true, theme, Modifier.align(Alignment.CenterEnd).padding(12.dp), ::goTo)
                else                                 -> Unit
            }
            if (fraction == SliderTheme.Indicator.Inside) {
                Fraction(current, stops, theme.insideFraction, theme.insideFraction, Modifier.align(Alignment.BottomEnd).padding(12.dp))
            }
        }
        if (pagination == SliderTheme.Indicator.Outside || fraction == SliderTheme.Indicator.Outside) {
            Box(Modifier.fillMaxWidth()) {
                if (pagination == SliderTheme.Indicator.Outside) {
                    Dots(stops, current, false, false, theme, Modifier.align(Alignment.Center), ::goTo)
                }
                if (fraction == SliderTheme.Indicator.Outside) {
                    Fraction(current, stops, theme.fraction, theme.fractionTotal, Modifier.align(Alignment.CenterEnd))
                }
            }
        }
    }
}

private class SlidesPerView(private val count: Int, private val spacing: Dp) : PageSize {
    override fun Density.calculateMainAxisPageSize(availableSpace: Int, pageSpacing: Int): Int =
        (availableSpace - spacing.roundToPx() * (count - 1)) / count
}

@Composable
private fun Arrow(previous: Boolean, enabled: Boolean, theme: SliderTheme, modifier: Modifier, onClick: () -> Unit) {
    val mirrored = previous != (LocalLayoutDirection.current == LayoutDirection.Rtl)
    Box(
        modifier = modifier
            .padding(8.dp)
            .size(32.dp)
            .disabledAlpha(!enabled)
            .clip(CircleShape)
            .background(theme.arrowBackground)
            .clickable(enabled = enabled, role = Role.Button, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_chevron_forward),
            contentDescription = stringResource(if (previous) R.string.preline_previous_page else R.string.preline_next_page),
            tint = theme.arrowIcon,
            modifier = Modifier.size(16.dp).scale(scaleX = if (mirrored) -1f else 1f, scaleY = 1f)
        )
    }
}

@Composable
private fun Dots(count: Int, current: Int, inside: Boolean, vertical: Boolean, theme: SliderTheme, modifier: Modifier, onClick: (Int) -> Unit) {
    val dots: @Composable () -> Unit = {
        repeat(count) { index ->
            val color = when {
                index == current -> if (inside) theme.insideActiveDot else theme.activeDot
                else             -> if (inside) theme.insideDot else theme.dot
            }
            Box(
                Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color)
                    .clickable(role = Role.Tab) { onClick(index) }
            )
        }
    }
    if (vertical) {
        Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) { dots() }
    } else {
        Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) { dots() }
    }
}

@Composable
private fun Fraction(current: Int, count: Int, color: Color, totalColor: Color, modifier: Modifier) {
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(color = color, fontSize = PrelineTheme.typography.xl2.fontSize, fontWeight = FontWeight.SemiBold)) {
                append((current + 1).toString())
            }
            withStyle(SpanStyle(color = totalColor)) { append(" / $count") }
        },
        style = PrelineTheme.typography.sm,
        modifier = modifier
    )
}
