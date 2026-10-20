package ir.ncis.preline.ui.components.checkbox

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.helpers.disabledAlpha
import ir.ncis.preline.ui.theme.Palette
import ir.ncis.preline.ui.theme.PrelineTheme

@Composable
internal fun CompactCheckbox(
    state: ToggleableState,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    val colors = PrelineTheme.colors
    val filled = state != ToggleableState.Off
    val shape = RoundedCornerShape(4.dp)

    Box(
        modifier = Modifier
            .size(16.dp)
            .disabledAlpha(!enabled)
            .clip(shape)
            .background(if (filled) colors.primary else Palette.Transparent)
            .border(1.dp, if (filled) colors.primary else colors.layerBorder, shape)
            .triStateToggleable(state = state, enabled = enabled, role = Role.Checkbox, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (filled) {
            Icon(
                painter = painterResource(if (state == ToggleableState.On) R.drawable.ic_check else R.drawable.ic_minus),
                contentDescription = null,
                tint = colors.primaryForeground,
                modifier = Modifier.size(12.dp)
            )
        }
    }
}
