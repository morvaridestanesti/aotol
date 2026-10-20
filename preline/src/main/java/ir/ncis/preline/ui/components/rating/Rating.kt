package ir.ncis.preline.ui.components.rating

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.ncis.preline.R
import ir.ncis.preline.helpers.disabledAlpha

@Composable
fun Rating(
    value: Int,
    modifier: Modifier = Modifier,
    shape: RatingTheme.Shape = RatingTheme.Shape.Star,
    height: Dp = 20.dp,
    isEnabled: Boolean = true,
    onValueChange: (Int) -> Unit,
) {
    val theme = getTheme(shape, height)
    Row(
        modifier = modifier.disabledAlpha(!isEnabled),
        horizontalArrangement = Arrangement.spacedBy(theme.gap)
    ) {
        for (currentValue in 1..5) {
            val isFilled = currentValue <= value
            Icon(
                painter = painterResource(
                    when (shape) {
                        RatingTheme.Shape.Heart -> R.drawable.ic_heart
                        RatingTheme.Shape.Star  -> R.drawable.ic_star
                    }
                ),
                contentDescription = stringResource(R.string.preline_rating_value, currentValue, 5),
                modifier = Modifier
                    .height(theme.height)
                    .aspectRatio(1f)
                    .selectable(
                        selected = currentValue == value,
                        enabled = isEnabled,
                        role = Role.RadioButton,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) { onValueChange(currentValue) },
                tint = if (isFilled) theme.filledColor else theme.emptyColor
            )
        }
    }
}
