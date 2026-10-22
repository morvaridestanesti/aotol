package ir.ncis.preline.ui.components.drawer

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Stable
class DrawerState(initiallyVisible: Boolean = false) {
    var isVisible by mutableStateOf(initiallyVisible)
        private set

    internal val progress = Animatable(if (initiallyVisible) 1f else 0f)

    fun show() {
        isVisible = true
    }

    fun hide() {
        isVisible = false
    }

    fun toggle() {
        isVisible = !isVisible
    }

    internal fun settle(visible: Boolean) {
        isVisible = visible
    }
}

@Composable
fun rememberDrawerState(initiallyVisible: Boolean = false): DrawerState = remember { DrawerState(initiallyVisible) }
