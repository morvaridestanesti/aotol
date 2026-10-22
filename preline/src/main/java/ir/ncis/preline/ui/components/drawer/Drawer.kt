package ir.ncis.preline.ui.components.drawer

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.ui.theme.PrelineTheme
import ir.ncis.preline.ui.theme.semiBold
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun Drawer(
    state: DrawerState,
    modifier: Modifier = Modifier,
    title: String? = null,
    side: DrawerTheme.Side = DrawerTheme.Side.Start,
    size: Dp = 320.dp,
    edgeSwipe: Boolean = false,
    footer: (@Composable RowScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val theme = getTheme()
    val scope = rememberCoroutineScope()
    val horizontal = side == DrawerTheme.Side.Start || side == DrawerTheme.Side.End
    val rtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val towardsEdge = when (side) {
        DrawerTheme.Side.Start  -> if (rtl) 1f else -1f
        DrawerTheme.Side.End    -> if (rtl) -1f else 1f
        DrawerTheme.Side.Top    -> -1f
        DrawerTheme.Side.Bottom -> 1f
    }

    LaunchedEffect(state.isVisible) { state.progress.animateTo(if (state.isVisible) 1f else 0f, tween(250)) }
    BackHandler(enabled = state.isVisible) { state.hide() }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val panelSize = if (horizontal) minOf(size, maxWidth) else minOf(size, maxHeight)
        val panelPx = with(LocalDensity.current) { panelSize.toPx() }
        val dragState = rememberDraggableState { delta ->
            scope.launch { state.progress.snapTo((state.progress.value - delta * towardsEdge / panelPx).coerceIn(0f, 1f)) }
        }
        val drag = Modifier.draggable(
            state = dragState,
            orientation = if (horizontal) Orientation.Horizontal else Orientation.Vertical,
            onDragStopped = { velocity ->
                val visible = if (abs(velocity) > 600f) velocity * towardsEdge < 0 else state.progress.value > 0.5f
                state.settle(visible)
                state.progress.animateTo(if (visible) 1f else 0f, tween(200))
            }
        )
        val progress = state.progress.value
        val alignment = when (side) {
            DrawerTheme.Side.Start  -> Alignment.CenterStart
            DrawerTheme.Side.End    -> Alignment.CenterEnd
            DrawerTheme.Side.Top    -> Alignment.TopCenter
            DrawerTheme.Side.Bottom -> Alignment.BottomCenter
        }

        if (progress > 0f) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(theme.scrim.copy(alpha = theme.scrim.alpha * progress))
                    .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) { state.hide() }
            )
            Column(
                modifier = Modifier
                    .align(alignment)
                    .then(if (horizontal) Modifier.width(panelSize).fillMaxHeight() else Modifier.height(panelSize).fillMaxWidth())
                    .offset {
                        val shift = ((1f - progress) * panelPx * towardsEdge).roundToInt()
                        if (horizontal) IntOffset(if (rtl) -shift else shift, 0) else IntOffset(0, shift)
                    }
                    .background(theme.background)
                    .then(drag)
            ) {
                title?.let { DrawerHeader(it, theme) { state.hide() } }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    content = content
                )
                footer?.let {
                    HorizontalDivider(thickness = 1.dp, color = theme.stroke)
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
                        verticalAlignment = Alignment.CenterVertically,
                        content = it
                    )
                }
            }
        }
        if (edgeSwipe && !state.isVisible) {
            Box(
                Modifier
                    .align(alignment)
                    .then(if (horizontal) Modifier.width(20.dp).fillMaxHeight() else Modifier.height(20.dp).fillMaxWidth())
                    .then(drag)
            )
        }
    }
}

@Composable
private fun DrawerHeader(title: String, theme: DrawerTheme, onClose: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, color = theme.title, style = PrelineTheme.typography.base.semiBold, modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(theme.closeBackground)
                .clickable(role = Role.Button, onClick = onClose),
            contentAlignment = Alignment.Center
        ) {
            Icon(painter = painterResource(R.drawable.ic_clear), contentDescription = stringResource(R.string.preline_close), tint = theme.closeIcon, modifier = Modifier.size(14.dp))
        }
    }
    HorizontalDivider(thickness = 1.dp, color = theme.stroke)
}
