package ir.ncis.preline.ui.components.skeleton

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import ir.ncis.preline.R

@Composable
fun Skeleton(
    loading: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    if (!loading) {
        Box(modifier = modifier) { content() }
        return
    }

    val theme = getTheme()
    val progress = shimmerProgress()
    val description = stringResource(R.string.preline_loading)

    Box(
        modifier = modifier
            .clearAndSetSemantics { contentDescription = description }
            .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
            .drawWithContent {
                drawContent()
                drawShimmer(theme, progress.value, BlendMode.SrcIn)
            }
    ) { content() }
}

@Composable
fun Modifier.skeleton(visible: Boolean = true, shape: Shape = CircleShape): Modifier {
    if (!visible) return this
    val theme = getTheme()
    val progress = shimmerProgress()
    return clip(shape).drawWithContent { drawShimmer(theme, progress.value) }
}

@Composable
private fun shimmerProgress(): State<Float> = rememberInfiniteTransition().animateFloat(
    initialValue = 0f,
    targetValue = 1f,
    animationSpec = infiniteRepeatable(tween(1200, easing = LinearEasing), RepeatMode.Restart)
)

private fun DrawScope.drawShimmer(theme: SkeletonTheme, progress: Float, blendMode: BlendMode = BlendMode.SrcOver) {
    val band = size.width / 2
    val start = -band + (size.width + band) * progress
    drawRect(
        brush = Brush.linearGradient(listOf(theme.base, theme.highlight, theme.base), start = Offset(start, 0f), end = Offset(start + band, 0f)),
        blendMode = blendMode
    )
}
